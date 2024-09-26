package learning.multithreading;

import java.util.concurrent.locks.ReentrantLock;

public class Deadlock {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Address address = new Address(lock);
        Employee employee = new Employee(lock);

        Thread t1 = new Thread(new EmployeeTask(employee, address), "employee");
        Thread t2 = new Thread(new EmployeeTask(employee, address),"address");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}

class EmployeeTask implements Runnable {

    private Employee employee;
    private Address address;

    public EmployeeTask(Employee employee, Address address) {
        this.employee = employee;
        this.address = address;
    }

    @Override
    public void run() {
        if(Thread.currentThread().getName().equals("employee")) {
            System.out.println(Thread.currentThread().getName() + " is going to update employee");
            try {
                employee.update(address);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " is going to update address");
            try {
                address.update(employee);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

class Employee {

    private final ReentrantLock lock;

    public Employee(ReentrantLock lock) {
        this.lock = lock;
    }

    public synchronized void update(Address address) throws InterruptedException {
//        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " has set address from employee");
            address.print();
        } finally {
//            lock.unlock();
        }
    }

    public synchronized void print() {
        System.out.println(Thread.currentThread().getName() + " has print called inside employee");
    }
}

class Address {

    private final ReentrantLock lock;
    public Address(ReentrantLock lock) {
        this.lock = lock;
    }

    public synchronized void update(Employee employee) throws InterruptedException {
//        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " has set employee from address");
            employee.print();
        } finally {
//            lock.unlock();
        }
    }

    public synchronized void print() {
        System.out.println(Thread.currentThread().getName() + " has print called inside address");
    }
}


