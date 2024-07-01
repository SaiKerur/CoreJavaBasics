package com.scaler24thjune.threads_and_processes.callables.custom_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskManager {

    public static void main(String[] args) {
        // Create an ExecutorService
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Task1
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                return new Task1().call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, executor);

        // Task2, dependent on Task1
        CompletableFuture<Integer> future2 = future1.thenApplyAsync(result1 -> {
            try {
                return new Task2(result1).call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, executor);

        // Task3, dependent on Task2
        CompletableFuture<Integer> future3 = future2.thenApplyAsync(result2 -> {
            try {
                return new Task3(result2).call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, executor);

        // Final result
        future3.thenAccept(result3 -> {
            System.out.println("Final result: " + result3);
        });

        // Shutdown the executor
        future3.join();
        executor.shutdown();
    }
}
