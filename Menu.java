
package JavaBankSystem;

import java.util.Scanner;

import FinalProjectCopy.User;

public class Menu {
    private User user;
    private Scanner scanner;
    private Database database;

    public Menu(User user, Scanner scanner, Database database) {
        this.user = user;
        this.scanner = scanner;
        this.database = database;
    }

    // Display the main menu
    public void displayMainMenu() {
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.println("4. Transfer Funds");
            System.out.println("5. Logout");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    withdrawMoney();
                    break;
                case 3:
                    depositMoney();
                    break;
                case 4:
                    transferFunds();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void checkBalance() {
        System.out.println("Checking Balance:");
        System.out.println("Checking: $" + user.getCheckingBalance());
        System.out.println("Savings: $" + user.getSavingsBalance());
    }

    private void withdrawMoney() {
        System.out.print("Enter account type (Checking/Savings): ");
        String accountType = scanner.nextLine();
        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (user.withdraw(amount, accountType)) {
            database.updateUser(user);
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    private void depositMoney() {
        System.out.print("Enter account type (Checking/Savings): ");
        String accountType = scanner.nextLine();
        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if ("Checking".equalsIgnoreCase(accountType)) {
            user.setCheckingBalance(user.getCheckingBalance() + amount);
        } else if ("Savings".equalsIgnoreCase(accountType)) {
            user.setSavingsBalance(user.getSavingsBalance() + amount);
        }

        database.updateUser(user);
        System.out.println("Deposit successful.");
    }

    private void transferFunds() {
        System.out.print("Enter target account type (Checking/Savings): ");
        String targetAccountType = scanner.nextLine();
        System.out.print("Enter transfer amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (user.transferFunds(amount, targetAccountType)) {
            database.updateUser(user);
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Insufficient funds for transfer.");
        }
    }
}
