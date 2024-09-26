package learning.multithreading;

public class PrintEvenOdd {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter(10);
        Thread t1 = new Thread(new Odd(counter), "odd");
        Thread t2 = new Thread(new Even(counter), "even");
        t1.start();
        t2.start();
    }
}

class Counter {

    int count = 1;
    int maxCount;

    public Counter(int maxCount) {
        this.maxCount = maxCount;
    }

    public void printEven() {
        synchronized (this) {
            while(count < maxCount) {
                while(count % 2 != 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "-" + count++);
                notifyAll();
            }
        }
    }

    public void printOdd() {
        synchronized (this) {
            while(count < maxCount) {
                while(count % 2 == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "-" + count++);
                notifyAll();
            }
        }
    }
}

class Even implements Runnable {

    private final Counter counter;

    public Even(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        counter.printEven();
    }
}

class Odd implements Runnable {

    private final Counter counter;

    public Odd(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        counter.printOdd();
    }
}
