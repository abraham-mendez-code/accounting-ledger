package com.pluralsight;

import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import static com.pluralsight.Ledger.getLedger;

public class LedgerApp {

    private static ArrayList<Transaction> ledger;

    private static Scanner scanner = new Scanner(System.in);

    public static void homeScreen() throws InterruptedException, IOException {

        String options = """
                
                The following options are available
                    D) Add Deposit
                    P) Make Payment (Debit)
                    L) Ledger
                    X) Exit
                Enter a command:\s""";

        System.out.print(options);

        String command = scanner.nextLine().substring(0,1).toLowerCase();

        switch (command) {
            case "d":
                deposit();
                break;
            case "p":
                payment();
                break;
            case "l":
                ledgerScreen();
                break;
            case "x":
                System.exit(0);
            default:
                System.out.println("Invalid Selection!");
                Thread.sleep(1000);
                homeScreen();
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
                amount = Math.abs(Double.parseDouble(scanner.nextLine()));

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

    public static void payment() throws InterruptedException {

        System.out.print("Enter a description(e.g The Big New Yorker Pizza): ");
        String description = scanner.nextLine().trim();

        System.out.print("Enter a vendor(e.g Pizza Hut): ");
        String vendor = scanner.nextLine().trim();

        double amount;

        while (true) {
            System.out.print("Enter an amount(e.g 13.99): ");
            try {
                amount = -1 * Math.abs(Double.parseDouble(scanner.nextLine()));

                if (amount == 0) {
                    System.out.println("Amount cannot be zero!");
                } else {
                    break;
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

    public static void ledgerScreen() throws IOException, InterruptedException {
        String options = """
                
                The following options are available
                    A) All - Display all entries
                    D) Deposits - Display only deposits
                    P) Payments - Display only payments
                    R) Reports - Run custom reports
                    H) Home - Return to the homepage
                Enter a command:\s""";

        System.out.print(options);

        String command = scanner.nextLine().substring(0,1).toLowerCase();

        ledger = getLedger();

        switch (command) {
            case "a":
                show(ledger);
                break;
            case "d":
                //show only deposits
                show(Reports.getDeposits(ledger));
                break;
            case "p":
                // show only payments
                show(Reports.getPayments(ledger));
                break;
            case "r":
                // go to a reports screen
                reports();
                break;
            case "h":
                homeScreen();
                break;
            default:
                // handle bad command input
        }

        Thread.sleep(1000);
        ledgerScreen();

    }

    public static void reports() throws InterruptedException {

        String options = """
                
                The following options are available
                    1) Month to Date
                    2) Previous Month
                    3) Year to Date
                    4) Previous Year
                    5) Search by Vendor
                    0) Back
                Enter a command:\s""";

        System.out.print(options);

        try {
            int command = Integer.parseInt(scanner.nextLine());

            switch (command) {
                case 1:
                    LocalDate today = LocalDate.now();
                    LocalDate firstOfMonth = today.withDayOfMonth(1);
                    show(Reports.getByDate(ledger, firstOfMonth, today));
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    ledgerScreen();
            }
        }
        catch (NumberFormatException | IOException | InterruptedException e) {
            System.out.println("Enter a number");
            Thread.sleep(1000);
            reports();
        }
    }

    public static void show(ArrayList<Transaction> ledger) throws IOException {


        for (Transaction t: ledger) {
            String date = t.getDate();
            String time = t.getTime();
            String description = t.getDescription();
            String vendor = t.getVendor();
            double amount = t.getAmount();

            System.out.printf("%s|%s|%s|%s|%.2f\n", date, time, description, vendor, amount);
        }

    }

}
