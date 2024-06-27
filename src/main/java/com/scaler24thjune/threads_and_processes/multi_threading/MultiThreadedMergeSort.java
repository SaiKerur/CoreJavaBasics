package com.scaler24thjune.threads_and_processes.multi_threading;

import java.util.Arrays;
import java.util.concurrent.*;

public class MultiThreadedMergeSort {
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();
    private static final ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
    private static final int PARALLEL_THRESHOLD = 4; // Threshold to switch to sequential sort

    public static void main(String[] args) {
        int[] array = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("Original Array: " + Arrays.toString(array));

        mergeSort(array);

        System.out.println("Sorted Array: " + Arrays.toString(array));

        executor.shutdown();
    }

    public static void mergeSort(int[] array) {
        if (array.length <= 1) {
            return;
        }

        if (array.length <= PARALLEL_THRESHOLD) {
            sequentialMergeSort(array);
            return;
        }

        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);

        // Sort the halves in separate threads
        Future<?> leftFuture = executor.submit(() -> {
            System.out.println("Sorting left part: " + Arrays.toString(left));
            mergeSort(left);
        });
        Future<?> rightFuture = executor.submit(() -> {
            System.out.println("Sorting right part: " + Arrays.toString(right));
            mergeSort(right);
        });

        try {
            // Wait for both halves to be sorted
            leftFuture.get();
            rightFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Merge the sorted halves
        merge(array, left, right);
    }

    private static void sequentialMergeSort(int[] array) {
        if (array.length <= 1) {
            return;
        }
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);

        sequentialMergeSort(left);
        sequentialMergeSort(right);

        merge(array, left, right);
    }

    private static void merge(int[] result, int[] left, int[] right) {
        int i = 0; // Index for left array
        int j = 0; // Index for right array
        int k = 0; // Index for result array

        System.out.println("Merging left: " + Arrays.toString(left) + " and right: " + Arrays.toString(right));

        // Merge the left and right arrays
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        // Copy the remaining elements of left, if any
        while (i < left.length) {
            result[k++] = left[i++];
        }

        // Copy the remaining elements of right, if any
        while (j < right.length) {
            result[k++] = right[j++];
        }

        System.out.println("Merged result: " + Arrays.toString(result));
    }
}
