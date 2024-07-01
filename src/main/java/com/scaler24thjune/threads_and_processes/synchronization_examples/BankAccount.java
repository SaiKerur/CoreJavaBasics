package com.scaler24thjune.threads_and_processes.synchronization_examples;

public class BankAccount {
    private int balance;

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    public synchronized void deposit(int amount) {
        balance += amount;
    }

    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Insufficient balance");
        }
    }

    public synchronized int getBalance() {
        return balance;
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);

        Runnable depositTask = () -> {
            for (int i = 0; i < 100; i++) {
                account.deposit(10);
            }
        };

        Runnable withdrawTask = () -> {
            for (int i = 0; i < 100; i++) {
                account.withdraw(10);
            }
        };

        Thread t1 = new Thread(depositTask);
        Thread t2 = new Thread(withdrawTask);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final balance: " + account.getBalance());
    }
}
