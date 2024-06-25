package com.scaler24thjune.threads_and_processes.wait_notify;

public class WaitNotifyExample {
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 1: Waiting for notification");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 1: Received notification");
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 2: Sending notification");
                lock.notify();
                System.out.println("Thread 2: Notification sent");
            }
        });

        thread1.start();
        Thread.sleep(1000); // Ensure thread1 waits before thread2 sends notification
        thread2.start();
    }
}

