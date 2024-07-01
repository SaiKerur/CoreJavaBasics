package com.scaler24thjune.threads_and_processes.callables.completable_future;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class CustomFutureTask<V> implements Future<V> {
    private final ProgressCallable<V> callable;
    private final Lock lock = new ReentrantLock();
    private final Condition paused = lock.newCondition();
    private boolean isPaused = false;
    private boolean isCancelled = false;
    private V result;
    private Future<?> future;

    public CustomFutureTask(ProgressCallable<V> callable) {
        this.callable = callable;
    }

    public void setFuture(Future<?> future) {
        this.future = future;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        lock.lock();
        try {
            isCancelled = true;
            return future.cancel(mayInterruptIfRunning);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isCancelled() {
        lock.lock();
        try {
            return isCancelled;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isDone() {
        lock.lock();
        try {
            return future.isDone();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        return (V) future.get();
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
         return (V) future.get(timeout, unit);
    }

    public void pause() {
        lock.lock();
        try {
            isPaused = true;
        } finally {
            lock.unlock();
        }
    }

    public void resume() {
        lock.lock();
        try {
            isPaused = false;
            paused.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public double getProgress() {
        return callable.getProgress();
    }

    public V call() throws Exception {
        lock.lock();
        try {
            while (isPaused) {
                paused.await();
            }
            if (isCancelled) {
                throw new CancellationException();
            }
        } finally {
            lock.unlock();
        }
        result = callable.call();
        return result;
    }
}
