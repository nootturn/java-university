package edu.course.lab02;

public class BankAccount {

    private int balance;

    public BankAccount(int initBalance) {
        if (initBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.balance = initBalance;
    }

    public void deposit(int amount) {
        if (amount < 0 ) {
            throw new IllegalArgumentException("Deposit should not be negative");
        }
        this.balance += amount;
    }

    public void withdraw(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        if (amount > this.balance) {
            throw new IllegalArgumentException("Amount must not exceed the balance");
        }

        this.balance -= amount;
    }

    public int getBalance() {
        return this.balance;
    }


}
