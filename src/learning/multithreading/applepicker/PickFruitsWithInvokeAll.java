package learning.multithreading.applepicker;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Arrays.asList;

public class PickFruitsWithInvokeAll {

    public static void main(String[] args) {
        AppleTree[] garden = AppleTree.newAppleTreeGarden(6);
        Callable<Integer> applePicker1 = createApplePicker(garden, 0, 2, "Alex");
        Callable<Integer> applePicker2 = createApplePicker(garden, 2, 4, "Sophia");
        Callable<Integer> applePicker3 = createApplePicker(garden, 4, 6, "John");

        List<Future<Integer>> futures = ForkJoinPool.commonPool().invokeAll(asList(applePicker1, applePicker2, applePicker3));

        int sum = futures.stream()
                .map(f -> {
                    try {
                        return f.get();
                    }catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                        .reduce(0, (a, b) -> a + b);
        System.out.printf("%s fruits are picked\n",sum);

//        int[] array = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
//        AtomicInteger result = new AtomicInteger(-1);
//        System.out.println(ForkJoinPool.commonPool().invoke(new ArraySumTask(array, 0, array.length - 1)));
//        var task = new BinarySearchTask(array, 11, 0, array.length - 1, result);
//        try {
//            ForkJoinPool.commonPool().invoke(task);
//        } catch (CancellationException e) {
//            System.out.println("Element found at: " + result);
//        }
    }

    static class ArraySumTask extends RecursiveTask<Integer> {
        int[] array;
        int startIndex;

        int endIndex;

        static int THRESHOLD = 2;

        public ArraySumTask(int[] array, int startIndex, int endIndex) {
            this.array = array;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        protected Integer compute() {
            if(endIndex - startIndex <= THRESHOLD) {
                return doCompute(startIndex, endIndex);
            }

            int mid = startIndex + (endIndex - startIndex) / 2;
            var leftSum = new ArraySumTask(array, startIndex, mid);
            var rightSum = new ArraySumTask(array, mid + 1, endIndex);
            rightSum.fork();
            return leftSum.compute() + rightSum.join();
        }

        private Integer doCompute(int startIndex, int endIndex) {
            int result = 0;
            for(int i = startIndex; i <= endIndex; i++) {
                result += array[i];
            }
            return result;
        }
    }

    static class BinarySearchTask extends RecursiveTask<Integer> {
        int[] array;
        int key;
        private final int startIndex;
        private final int lastIndex;
        private  final AtomicInteger result;

        public BinarySearchTask(int[] array, int key, int startIndex, int lastIndex, AtomicInteger result) {
            this.array = array;
            this.key = key;
            this.startIndex = startIndex;
            this.lastIndex = lastIndex;
            this.result = result;
        }

        @Override
        protected Integer compute() {
            if(startIndex > lastIndex) {
                return -1;
            }
            int mid = startIndex + (lastIndex - startIndex) / 2;
            if(key == array[mid]) {
                return mid;
            } else if (key < array[mid]) {
                var leftSum = new BinarySearchTask(array, key, startIndex, mid, result);
                leftSum.fork();
                Integer leftResult = leftSum.join();
                if (leftResult != -1) {
                    result.set(leftResult);
                    this.cancel(true);
                }
                return leftSum.join();
            } else {
                var rightSum = new BinarySearchTask(array, key, mid + 1, lastIndex, result);
                rightSum.fork();
                Integer rightResult = rightSum.join();
                if (rightResult != -1) {
                    result.set(rightResult);
                    this.cancel(true);
                }
                return rightResult;
            }
        }
    }

    public static Callable<Integer> createApplePicker(AppleTree[] garden, int fromIndexInclusive, int toIndexExclusive, String name) {
        return () -> {
            int result = 0;
            for(int i = fromIndexInclusive; i < toIndexExclusive; i++) {
                result += garden[i].pickApples(name);
            }
            return result;
        };
    }
}
