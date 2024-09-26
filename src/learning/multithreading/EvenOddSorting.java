package learning.multithreading;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class EvenOddSorting {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        List<Integer> list1 = List.of(1,2,3);
//        System.out.println(list1.stream().collect(Collectors.summingInt(value -> value)));

        List<Integer> list = List.of(1,19,21,4,20,65,80);
        List<Integer> evenList = list.stream().filter(i -> i%2 == 0).toList();
        List<Integer> oddList = list.stream().filter(i -> i%2 != 0).toList();

        Callable<List<Integer>> sortEven = new SortingTask(evenList);
        Callable<List<Integer>> sortOdd = new SortingTask(oddList);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<List<Integer>> sortedEven = executor.submit(sortEven);
        Future<List<Integer>> sortedOdd = executor.submit(sortOdd);
        sortedOdd.get().addAll(sortedEven.get());
        System.out.println(sortedOdd.get());

    }
}

class SortingTask implements Callable<List<Integer>> {

    private final List<Integer> list;
    SortingTask(List<Integer> list) {
        this.list = list;
    }

    @Override
    public List<Integer> call() {
        return list.stream().sorted().collect(Collectors.toList());
    }
}
