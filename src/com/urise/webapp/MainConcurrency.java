package com.urise.webapp;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10000;
    private static int counter;
    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
            }
        };
        thread0.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
            }
        }).start();

        System.out.println(thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();
        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread.start();
            threads.add(thread);
        }

        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

//        Thread.sleep(500);
        System.out.println(counter);

        // HW 11
        final Object lock1 = new Object();
        final Object lock2 = new Object();

        deadLock(lock1, lock2);
        deadLock(lock2, lock1);
    }

    private synchronized void inc() {
//        synchronized (this) {
//        synchronized (MainConcurrency.class) {
        counter++;
//                wait();
//                readFile
//                ...
//        }
    }

    private static void deadLock(Object obj1, Object obj2) {
        new Thread(() -> {
            synchronized (obj1) {
                System.out.println("Lock first object.");
                synchronized (obj2) {
                    System.out.println("Wait for lock second object.");
                }
            }

            synchronized (obj2) {
                System.out.println("Lock second object.");
                synchronized (obj1) {
                    System.out.println("Wait for lock first object.");
                }
            }
        }).start();
    }
}
