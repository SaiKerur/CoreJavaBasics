package com.scaler24thjune.threads_and_processes.advanced_synchronization;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);

        for (int i = 1; i <= 3; i++) {
            new Thread(new Worker(latch, i)).start();
        }

        try {
            latch.await(); // Main thread waits until the latch count reaches zero
            System.out.println("All workers have finished their tasks. Main thread proceeding.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Worker implements Runnable {
        private CountDownLatch latch;
        private int workerId;

        Worker(CountDownLatch latch, int workerId) {
            this.latch = latch;
            this.workerId = workerId;
        }

        @Override
        public void run() {
            System.out.println("Worker " + workerId + " is doing some work.");
            try {
                Thread.sleep(2000); // Simulate some work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Worker " + workerId + " has finished.");
            latch.countDown(); // Decrease the latch count
        }
    }
}

