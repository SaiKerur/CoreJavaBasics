package com.scaler24thjune.threads_and_processes.callables.completable_future;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        ProgressTrackingCallable task = new ProgressTrackingCallable();
        CustomFutureTask<Integer> futureTask = new CustomFutureTask<>(task);
        futureTask.setFuture(executor.submit(() -> {
            try {
                return futureTask.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }));

        while (!futureTask.isDone()) {
            System.out.println("Progress: " + (futureTask.getProgress() * 100) + "%");
            TimeUnit.MILLISECONDS.sleep(500);
            if (futureTask.getProgress() > 0.5) {
                futureTask.pause();
                System.out.println("Task paused.");
                TimeUnit.SECONDS.sleep(2);
                futureTask.resume();
                System.out.println("Task resumed.");
            }
        }

        System.out.println("Result: " + futureTask.get());
        executor.shutdown();
    }
}
