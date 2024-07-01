package com.scaler24thjune.threads_and_processes.online_order;

public class RollbackActions {

    public static void rollbackPayment() {
        System.out.println("Payment rolled back.");
        // Custom rollback logic for payment
    }

    public static void rollbackOrderUpdate() {
        System.out.println("Order update rolled back.");
        // Custom rollback logic for order update
    }
}
