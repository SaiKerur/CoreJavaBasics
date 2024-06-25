package com.scaler24thjune.threads_and_processes.word_processor_simulation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WordProcessorSimulation {

    private static final ExecutorService executor = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        // Start UI thread
        executor.submit(() -> handleUI());

        // Start text input and formatting thread
        executor.submit(() -> handleTextInput());

        // Start spell check and grammar check thread
        executor.submit(() -> handleSpellCheck());

        // Start autosave and document management thread
        executor.submit(() -> handleAutoSave());

        // Start collaboration synchronization thread
        executor.submit(() -> handleCollaborationSync());

        // Shutdown executor gracefully after tasks are completed
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }

    private static void handleUI() {
        while (true) {
            // Simulate UI rendering
            System.out.println("Rendering UI...");
            try {
                Thread.sleep(100); // Simulate time delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private static void handleTextInput() {
        while (true) {
            // Simulate text input and formatting
            System.out.println("Processing text input...");
            try {
                Thread.sleep(200); // Simulate time delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private static void handleSpellCheck() {
        while (true) {
            // Simulate spell check and grammar check
            System.out.println("Checking spelling and grammar...");
            try {
                Thread.sleep(300); // Simulate time delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private static void handleAutoSave() {
        while (true) {
            // Simulate autosaving the document
            System.out.println("Autosaving document...");
            try {
                Thread.sleep(5000); // Simulate time delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private static void handleCollaborationSync() {
        while (true) {
            // Simulate collaboration and synchronization
            System.out.println("Synchronizing collaboration...");
            try {
                Thread.sleep(1000); // Simulate time delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

