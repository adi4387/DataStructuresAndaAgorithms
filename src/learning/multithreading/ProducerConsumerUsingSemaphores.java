package learning.multithreading;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerUsingSemaphores {

    public static void main(String[] args) {
        Semaphore full = new Semaphore(0);
        Semaphore empty = new Semaphore(5);
        Queue<Integer> queue = new ArrayDeque<>(10);
        Lock lock = new ReentrantLock();
        int noOfProducers = 10;
        List<Thread> producers = new ArrayList<>();
        for(int i = 0; i < noOfProducers; i++) {
            producers.add(new Thread(new ProducerS(full, empty, queue, lock), "producer" + i));
        }
        int noOfConsumers = 10;
        List<Thread> consumers = new ArrayList<>();
        for(int i = 0; i < noOfConsumers; i++) {
            consumers.add(new Thread(new ConsumerS(full, empty, queue, lock), "consumer" + i));
        }

        for(int i = 0; i < noOfProducers; i++) {
            producers.get(i).start();
        }

        for(int i = 0; i < noOfConsumers; i++) {
            consumers.get(i).start();
        }
    }

}

class ProducerS implements Runnable {

    private final Semaphore full;

    private final Semaphore empty;

    private final Queue<Integer> queue;

    private final Lock lock;

    private int item;

    public ProducerS(Semaphore full, Semaphore empty, Queue<Integer> queue, Lock lock) {
        this.full = full;
        this.empty = empty;
        this.queue = queue;
        this.lock = lock;
        this.item = 0;
    }

    @Override
    public void run() {
        while(true) {
            try {
                empty.acquire();
                Thread.sleep(1000);
                lock.lock();
                item++;
                queue.offer(item);
                System.out.println(Thread.currentThread().getName() + ": produced " + item);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
                full.release();
            }

        }
    }
}

class ConsumerS implements Runnable {

    private final Semaphore full;

    private final Semaphore empty;

    private final Queue<Integer> queue;
    private final Lock lock;

    public ConsumerS(Semaphore full, Semaphore empty, Queue<Integer> queue, Lock lock) {
        this.full = full;
        this.empty = empty;
        this.queue = queue;
        this.lock = lock;
    }

    @Override
    public void run() {
        while(true) {
            try {
                full.acquire();
                Thread.sleep(1000);
                lock.lock();
                int item = queue.poll();
                System.out.println(Thread.currentThread().getName() + ": consumed " + item);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
                empty.release();
            }
        }
    }
}
