package com.scaler24thjune.threads_and_processes.fork_join_framework;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinExample {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        FibonacciTask task = new FibonacciTask(10);
        Integer result = pool.invoke(task);
        System.out.println("Fibonacci number: " + result);
    }

    static class FibonacciTask extends RecursiveTask<Integer> {
        private static final int THRESHOLD = 10;
        private int n;

        FibonacciTask(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= THRESHOLD) {
                return fibonacci(n);
            } else {
                FibonacciTask f1 = new FibonacciTask(n - 1);
                f1.fork();
                FibonacciTask f2 = new FibonacciTask(n - 2);
                return f2.compute() + f1.join();
            }
        }

        private int fibonacci(int n) {
            if (n <= 1) return n;
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}

