package edu.course.lab02;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class BankAccountTest {

    @Test
    void testInitialBalance() {
        BankAccount testBalance = new BankAccount(100);

        assertEquals(100, testBalance.getBalance());
    }

    @Test
    void testWithdrawBankAccount() {
        BankAccount testBalance = new BankAccount(100);

        testBalance.withdraw(50);

        assertEquals(50, testBalance.getBalance());
    }

    @Test
    void returnErrorAmountGreaterBalance() {
        BankAccount testBalance = new BankAccount(100);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            testBalance.withdraw(110);
        });
    }

    @Test
    void testVerifyCorrectAddToBalance(){
        BankAccount testBalance = new BankAccount(100);

        testBalance.deposit(30);

        assertEquals(130, testBalance.getBalance());
    }

    @Test
    void returnErrorWhenPostIllegalDepositValue() {
        BankAccount testBalance = new BankAccount(100);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            testBalance.deposit(-10);
        });
    }

    @Test
    void returnErrorWhenPostIllegalWithdrawValue() {
        BankAccount testBalance = new BankAccount(100);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            testBalance.withdraw(-10);
        });
    }
}
