package com.scaler24thjune.threads_and_processes.advanced_synchronization;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3); // Allow up to 3 threads to access the resource simultaneously

        for (int i = 1; i <= 5; i++) {
            new Thread(new Task(semaphore, i)).start();
        }
    }

    static class Task implements Runnable {
        private Semaphore semaphore;
        private int taskId;

        Task(Semaphore semaphore, int taskId) {
            this.semaphore = semaphore;
            this.taskId = taskId;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("Task " + taskId + " has acquired the semaphore.");
                Thread.sleep(2000); // Simulate some work
                System.out.println("Task " + taskId + " is releasing the semaphore.");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

