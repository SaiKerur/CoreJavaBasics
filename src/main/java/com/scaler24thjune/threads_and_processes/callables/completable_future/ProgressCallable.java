package com.scaler24thjune.threads_and_processes.callables.completable_future;

import java.util.concurrent.Callable;

public interface ProgressCallable<V> extends Callable<V> {
    double getProgress();
}
