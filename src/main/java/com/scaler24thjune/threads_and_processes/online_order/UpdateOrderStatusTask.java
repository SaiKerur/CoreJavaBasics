package com.scaler24thjune.threads_and_processes.online_order;

import java.util.concurrent.Callable;

public class UpdateOrderStatusTask implements Callable<Boolean> {
    private final Boolean paymentProcessed;

    public UpdateOrderStatusTask(Boolean paymentProcessed) {
        this.paymentProcessed = paymentProcessed;
    }

    @Override
    public Boolean call() throws Exception {
        if (!paymentProcessed) {
            throw new Exception("Payment not processed");
        }
        // Simulate updating order status
        System.out.println("Order status updated.");
        return true;
    }
}
