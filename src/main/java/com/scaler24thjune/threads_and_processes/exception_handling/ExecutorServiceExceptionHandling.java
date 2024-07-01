package com.scaler24thjune.threads_and_processes.exception_handling;

import java.util.concurrent.*;

public class ExecutorServiceExceptionHandling {

    public static void main(String[] args) {
        // Step 1: Create an ExecutorService
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Step 2: Submit tasks and capture futures
        Future<Integer> future1 = executorService.submit(() -> {
            System.out.println("Task 1 is running");
            if (true) throw new RuntimeException("Exception in Task 1");
            return 1;
        });

        Future<Integer> future2 = executorService.submit(() -> {
            System.out.println("Task 2 is running");
            return 2;
        });

        Future<Integer> future3 = executorService.submit(() -> {
            System.out.println("Task 3 is running");
            if (true) throw new RuntimeException("Exception in Task 3");
            return 3;
        });

        // Step 3: Handle exceptions and ensure application stability
        handleFutureResult(future1);
        handleFutureResult(future2);
        handleFutureResult(future3);

        // Step 4: Shut down the ExecutorService gracefully
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }

    // Method to handle the result or exception from a Future
    private static void handleFutureResult(Future<Integer> future) {
        try {
            Integer result = future.get();
            System.out.println("Task completed with result: " + result);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
            System.out.println("Task was interrupted");
        } catch (ExecutionException e) {
            System.out.println("Task threw an exception: " + e.getCause());
        }
    }
}
