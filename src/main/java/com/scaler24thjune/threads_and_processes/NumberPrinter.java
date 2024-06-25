package com.scaler24thjune.threads_and_processes;

public class NumberPrinter implements Runnable {
    private int start;
    private int end;

    // Constructor to initialize the range of numbers to print
    public NumberPrinter(int start, int end) {
        this.start = start;
        this.end = end;
    }

    // The run method contains the logic for printing numbers
    @Override
    public void run() {
        for (int i = start; i <= end; i++) {
            System.out.println(Thread.currentThread().getName() + " - " + i);
            try {
                Thread.sleep(100); // Sleep for a short time to simulate work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore the interrupted status
            }
        }
    }

    public static void main(String[] args) {
        // Create two instances of NumberPrinter with different ranges
        NumberPrinter printer1 = new NumberPrinter(1, 10);
        NumberPrinter printer2 = new NumberPrinter(11, 20);

        // Create and start two threads
        Thread thread1 = new Thread(printer1, "Printer-1");
        Thread thread2 = new Thread(printer2, "Printer-2");

        thread1.start();
        thread2.start();

        // Optionally, wait for the threads to finish
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All threads have finished execution.");
    }
}
