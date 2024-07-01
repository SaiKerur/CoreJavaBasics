package com.scaler24thjune.threads_and_processes.callables.completable_future;

import java.util.concurrent.TimeUnit;

public class ProgressTrackingCallable implements ProgressCallable<Integer> {
    private volatile double progress = 0.0;

    @Override
    public Integer call() throws InterruptedException {
        for (int i = 0; i <= 100; i++) {
            TimeUnit.MILLISECONDS.sleep(100);
            progress = i / 100.0;
        }
        return 100;
    }

    @Override
    public double getProgress() {
        return progress;
    }
}

