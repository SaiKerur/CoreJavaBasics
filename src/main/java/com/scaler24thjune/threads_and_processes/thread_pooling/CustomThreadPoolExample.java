package com.scaler24thjune.threads_and_processes.thread_pooling;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomThreadPoolExample {

    public static void main(String[] args) {
        // Create a custom ThreadFactory
        ThreadFactory customThreadFactory = new ThreadFactory() {
            private int counter = 0;

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("CustomThread-" + counter++);
                thread.setPriority(Thread.NORM_PRIORITY + 1); // Set a higher priority
                thread.setDaemon(false); // Set as a non-daemon thread
                return thread;
            }
        };

        // Create a ThreadPoolExecutor using the custom ThreadFactory
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3, customThreadFactory);

        // Submit some tasks to the executor
        for (int i = 0; i < 5; i++) {
            executor.submit(new Task());
        }

        // Shutdown the executor
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }

    // A simple task for demonstration
    static class Task implements Runnable {
        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println("Executing task in " + threadName);
            try {
                Thread.sleep(1000); // Simulate work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
