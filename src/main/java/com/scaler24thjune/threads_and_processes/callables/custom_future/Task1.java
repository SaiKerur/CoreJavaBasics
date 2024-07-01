package com.scaler24thjune.threads_and_processes.callables.custom_future;

import java.util.concurrent.Callable;

public class Task1 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        // Simulate a long-running task
        Thread.sleep(1000);
        System.out.println("Task1 completed.");
        return 10;
    }
}