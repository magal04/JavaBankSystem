
// Represents a bank account holder. It stores user details such as account number, balances, and provides methods for account operations like withdrawals, deposits, and transfers.

package JavaBankSystem;

public class User {
    private String accountNumber;
    private String holderName;
    private String pin;
    private double checkingBalance;
    private double savingsBalance;

    public User(String accountNumber, String holderName, String pin, double checkingBalance, double savingsBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.pin = pin;
        this.checkingBalance = checkingBalance;
        this.savingsBalance = savingsBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public String getPin() {
        return pin;
    }

    public double getCheckingBalance() {
        return checkingBalance;
    }

    public double getSavingsBalance() {
        return savingsBalance;
    }

    public void setCheckingBalance(double checkingBalance) {
        this.checkingBalance = checkingBalance;
    }

    public void setSavingsBalance(double savingsBalance) {
        this.savingsBalance = savingsBalance;
    }

    public boolean validatePin(String pin) {
        return this.pin.equals(pin);
    }

    // Withdraw funds from the specified account
    public boolean withdraw(double amount, String accountType) {
        if ("Checking".equals(accountType)) {
            if (checkingBalance >= amount) {
                checkingBalance -= amount;
                return true;
            }
        } else if ("Savings".equals(accountType)) {
            if (savingsBalance >= amount) {
                savingsBalance -= amount;
                return true;
            }
        }
        return false;
    }

    // Transfer funds between accounts
    public boolean transferFunds(double amount, String targetAccountType) {
        if ("saving".equalsIgnoreCase(targetAccountType) && checkingBalance >= amount) {
            savingsBalance += amount;
            checkingBalance -= amount;
            return true;
        } else if ("checking".equalsIgnoreCase(targetAccountType) && savingsBalance >= amount) {
            checkingBalance += amount;
            savingsBalance -= amount;
            return true;
        }
        return false;
    }
}
