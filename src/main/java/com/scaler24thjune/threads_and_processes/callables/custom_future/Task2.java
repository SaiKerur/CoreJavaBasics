package com.scaler24thjune.threads_and_processes.callables.custom_future;

import java.util.concurrent.Callable;

public class Task2 implements Callable<Integer> {
    private final int input;

    public Task2(int input) {
        this.input = input;
    }

    @Override
    public Integer call() throws Exception {
        // Simulate a long-running task using the input
        Thread.sleep(1000);
        System.out.println("Task2 completed with input: " + input);
        return input * 2;
    }
}