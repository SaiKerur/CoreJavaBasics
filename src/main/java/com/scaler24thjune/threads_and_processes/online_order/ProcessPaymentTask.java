package com.scaler24thjune.threads_and_processes.online_order;

import java.util.concurrent.Callable;

public class ProcessPaymentTask implements Callable<Boolean> {
    private final Boolean inventoryChecked;

    public ProcessPaymentTask(Boolean inventoryChecked) {
        this.inventoryChecked = inventoryChecked;
    }

    @Override
    public Boolean call() throws Exception {
        if (!inventoryChecked) {
            throw new Exception("Inventory not available");
        }
        // Simulate payment processing
        System.out.println("Payment processed.");
        return true;
    }
}
