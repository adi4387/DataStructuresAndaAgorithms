package learning.multithreading;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueExample {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
        Producer producer = new Producer(queue);
        Thread t1 = new Thread(producer, "Producer");
        Consumer consumer = new Consumer(queue);
        Thread t2 = new Thread(consumer, "Consumer");
        t1.start();
        t2.start();
    }
}


class Producer implements Runnable {

    private final BlockingQueue<String> queue;

    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            var task = scanner.next();
            if(task.equals(0)) {
                break;
            }
            if (queue.offer(task)) {
                System.out.println(Thread.currentThread().getName() + " " + task);
            }
        }
    }
}

class Consumer implements Runnable {

    private final BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true) {
            try {
                System.out.println(Thread.currentThread().getName() + queue.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
