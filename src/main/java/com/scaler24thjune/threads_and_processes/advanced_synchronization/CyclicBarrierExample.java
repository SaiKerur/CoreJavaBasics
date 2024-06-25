package com.scaler24thjune.threads_and_processes.advanced_synchronization;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        int parties = 3;
        CyclicBarrier barrier = new CyclicBarrier(parties, () -> System.out.println("All parties have arrived. Barrier is broken."));

        for (int i = 1; i <= parties; i++) {
            new Thread(new Task(barrier, i)).start();
        }
    }

    static class Task implements Runnable {
        private CyclicBarrier barrier;
        private int taskId;

        Task(CyclicBarrier barrier, int taskId) {
            this.barrier = barrier;
            this.taskId = taskId;
        }

        @Override
        public void run() {
            System.out.println("Task " + taskId + " is waiting at the barrier.");
            try {
                Thread.sleep(1000 * taskId); // Simulate different arrival times
                barrier.await(); // Wait for other parties
                System.out.println("Task " + taskId + " has crossed the barrier.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
