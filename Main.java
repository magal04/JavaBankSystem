
//Serves as the entry point for the application. It initializes the database and authentication system, displays the main menu, and routes user actions 
package JavaBankSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Database database = Database.getInstance();
        Authentication auth = new Authentication(database);

        // Main menu loop
        System.out.println("Welcome to the Java Bank");
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                // Login process
                System.out.print("Enter your account number: ");
                String accountNumber = scanner.nextLine();
                System.out.print("Enter PIN: ");
                String pin = scanner.nextLine();
                User user = auth.login(accountNumber, pin);
                if (user != null) {
                    System.out.println("Login successful! Welcome " + user.getHolderName());
                    Menu menu = new Menu(user, scanner, database);
                    menu.displayMainMenu();
                } else {
                    System.out.println("Invalid account number or PIN. Please try again.\n");
                }
            } else if (option == 2) {
                System.out.println("Thank you for using the Java Bank! Goodbye!");
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}
