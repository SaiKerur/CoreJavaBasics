package com.scaler24thjune.threads_and_processes.online_order;

import java.util.concurrent.Callable;

public class CheckInventoryTask implements Callable<Boolean> {
    @Override
    public Boolean call() throws Exception {
        // Simulate checking inventory
        System.out.println("Inventory checked.");
        return true; // Return true if inventory is available
    }
}