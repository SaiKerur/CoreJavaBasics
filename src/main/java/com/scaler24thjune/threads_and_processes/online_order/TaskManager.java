package com.scaler24thjune.threads_and_processes.online_order;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskManager {

    public static void main(String[] args) {
        // Create an ExecutorService
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Task 1: Check Inventory
        CompletableFuture<Boolean> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                return new CheckInventoryTask().call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, executor);

        // Task 2: Process Payment (depends on Task 1)
        CompletableFuture<Boolean> future2 = future1.thenApplyAsync(inventoryChecked -> {
            try {
                return new ProcessPaymentTask(inventoryChecked).call();
            } catch (Exception e) {
                RollbackActions.rollbackPayment();
                throw new RuntimeException(e);
            }
        }, executor);

        // Task 3: Update Order Status (depends on Task 2)
        CompletableFuture<Boolean> future3 = future2.thenApplyAsync(paymentProcessed -> {
            try {
                return new UpdateOrderStatusTask(paymentProcessed).call();
            } catch (Exception e) {
                RollbackActions.rollbackOrderUpdate();
                throw new RuntimeException(e);
            }
        }, executor);

        // Handle final result or any exception
        future3.handle((result3, ex) -> {
            if (ex != null) {
                System.out.println("An error occurred: " + ex.getMessage());
                // Perform any additional compensatory actions
            } else {
                System.out.println("All tasks completed successfully.");
            }
            return null;
        }).join();

        // Shutdown the executor
        executor.shutdown();
    }
}

