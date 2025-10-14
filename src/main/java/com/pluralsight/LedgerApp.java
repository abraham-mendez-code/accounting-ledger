package com.pluralsight;

import java.util.Scanner;

public class LedgerApp {

    private static Scanner scanner = new Scanner(System.in);

    public static void home() throws InterruptedException {

        String options = """
                
                The following options are available
                    D) Add Deposit
                    P) Make Payment (Debit)
                    L) Ledger
                    X) Exit
                Enter a command: """;

        System.out.print(options);

        String command = scanner.nextLine().substring(0,1).toLowerCase();

        switch (command) {
            case "d":
                deposit();
                break;
            case "p":
                // payment();
                break;
            case "l":
                //
                break;
            case "x":
                System.exit(0);
            default:
                System.out.println("Invalid Selection!");
                Thread.sleep(1000);
                home();
        }

    }

    public static void deposit() throws InterruptedException {

        System.out.print("Enter a description(e.g Invoice 66 paid): ");
        String description = scanner.nextLine().trim();

        System.out.print("Enter a vendor(e.g Sheev): ");
        String vendor = scanner.nextLine().trim();

        double amount;

        while (true) {
            System.out.print("Enter an amount: ");
            try {
                amount = Double.parseDouble(scanner.nextLine());

                if (amount > 0) {
                    break;
                } else {
                    System.out.printf("$%.2f is not a valid deposit\n", amount);
                    Thread.sleep(1000);
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Enter a valid number");
                Thread.sleep(1000);
            }
        }

        Transaction deposit = new Transaction(description, vendor, amount);

        System.out.printf("Deposit for %s made from %s for $%.2f\n", description, vendor, amount);

    }

}
