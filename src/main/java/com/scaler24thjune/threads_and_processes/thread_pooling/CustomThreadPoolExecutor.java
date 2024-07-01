package com.scaler24thjune.threads_and_processes.thread_pooling;

import java.util.concurrent.*;

public class CustomThreadPoolExecutor extends ThreadPoolExecutor {

    public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        System.out.println("Before executing task in thread: " + t.getName());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        System.out.println("After executing task. Completed by: " + Thread.currentThread().getName());
    }

    @Override
    protected void terminated() {
        super.terminated();
        System.out.println("ThreadPool terminated.");
    }

    public static void main(String[] args) {
        ThreadFactory customThreadFactory = new ThreadFactory() {
            private int counter = 0;

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("CustomThread-" + counter++);
                thread.setPriority(Thread.NORM_PRIORITY + 1);
                thread.setDaemon(false);
                return thread;
            }
        };

        CustomThreadPoolExecutor executor = new CustomThreadPoolExecutor(3, 3, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), customThreadFactory);

        for (int i = 0; i < 5; i++) {
            executor.submit(new Task());
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println("Executing task in " + threadName);
            try {
                Thread.sleep(1000); // Simulate work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

