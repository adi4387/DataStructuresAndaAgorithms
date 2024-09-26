package learning.multithreading.dominos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class DominosMaker {

    public static void main(String[] args) {
//        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
//        List<ScheduledFuture<?>> futures = new ArrayList<>();
//        for(int i = 0; i < 10; i++) {
//            final int temp = i;
//            futures.add(executorService.schedule(() -> System.out.println(temp), 1, TimeUnit.SECONDS));
//        }
//        executorService.scheduleAtFixedRate(() -> System.out.println("Fixed"), 0, 1,TimeUnit.SECONDS);
//        executorService.scheduleWithFixedDelay(() -> System.out.println("Delayed"), 0, 1,TimeUnit.SECONDS);
//
//        futures.stream().forEach(scheduledFuture -> {
//            try {
//                scheduledFuture.get();
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
//        });

        ExecutorService executorService1 = Executors.newFixedThreadPool(1);
        ExecutorService executorService2 = Executors.newCachedThreadPool();
        CompletableFuture<String> slicingTomatoes = CompletableFuture.supplyAsync(() -> {
            System.out.println("Resturant Slicing tomatoes");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "sliced tomatoes";
        }, executorService1).handle((s, e) -> {
            System.out.println(Thread.currentThread().getName() + " " + s);
            return s;
        });
        CompletableFuture<String> cuttingOnions = CompletableFuture.supplyAsync(() -> {
            System.out.println("Resturant Cutting onions");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "cut onions";
        }, executorService2).handle((s, e) -> {
            System.out.println(Thread.currentThread().getName() + " " + s);
            return s;
        });
        CompletableFuture<String> prep = cuttingOnions.thenCombine(slicingTomatoes, String::concat);

        CompletableFuture<String> prepPizza = prep.thenApply(toppings -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Resturant adding toppings");
            return "toppings";
        }).handle((s, e) -> {
            System.out.println(Thread.currentThread().getName() + " " + s);
            return s;
        });
        CompletableFuture<String> bakingPizza = prepPizza.thenApply(rawPizza -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Resturant baking pizza");
            return "Pizza";
        }).handle((s, e) -> {
            System.out.println(Thread.currentThread().getName() + " " + s);
            return s;
        });
        bakingPizza.thenAccept(System.out::println);
        executorService1.shutdown();
        executorService2.shutdown();
    }
}
