package com.scaler24thjune.threads_and_processes.thread_pooling;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10); // Create a thread pool with 10 threads

        for (int i = 0; i < 20; i++) {
            executor.submit(new Task(i));
        }

        executor.shutdown(); // Initiates an orderly shutdown
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES); // Waits for termination for up to 1 minute
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Task implements Runnable {
        private int taskId;

        Task(int id) {
            this.taskId = id;
        }

        @Override
        public void run() {
            System.out.println("Executing Task " + taskId + " by " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000); // Simulate some work with sleep
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Completed Task " + taskId + " by " + Thread.currentThread().getName());
        }
    }
}
