package com.pluralsight;

import java.io.IOException;
import java.time.LocalDate;
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
                Transaction.deposit();
                break;
            case "p":
                Transaction.payment();
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
                reportsScreen();
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

    public static void reportsScreen() throws InterruptedException {

        String options = """
                
                The following options are available
                    1) Month to Date
                    2) Previous Month
                    3) Year to Date
                    4) Previous Year
                    5) Search by Vendor
                    6) Custom Search
                    0) Back
                Enter a command:\s""";

        System.out.print(options);

        try {
            int command = Integer.parseInt(scanner.nextLine());

            /*
                Declare local date variables which will be used to filter
                transactions chronologically, initialize today as today
             */
            LocalDate today = LocalDate.now();
            LocalDate afterDate;
            LocalDate beforeDate;


            String vendor;
            String description;
            double minAmount;
            double maxAmount;


            switch (command) {
                case 1:
                    afterDate = today.withDayOfMonth(1);
                    beforeDate = today;
                    show(Reports.filterByDateRange(ledger, afterDate, beforeDate));
                    Thread.sleep(1000);
                    break;
                case 2:
                    /*
                        this assigns a start date LocalDate with the current year,
                        the previous month (current month - 1), and the 1st day of last month
                     */
                    afterDate = LocalDate.of(today.getYear(),
                            today.getMonthValue() - 1, 1);

                    /*
                        this assigns an end date LocalDate with the current year,
                        the previous month (current month - 1) and the last day of last month
                     */
                    beforeDate = LocalDate.of(today.getYear(), today.getMonthValue() - 1
                            , (today.withMonth(today.getMonthValue() - 1)).lengthOfMonth());

                    // this shows a filtered ledger with transactions between the start and end date
                    show(Reports.filterByDateRange(ledger, afterDate, beforeDate));
                    Thread.sleep(1000);
                    break;
                case 3:
                    /*
                        this assigns a start date LocalDate with the previous year (current year -1),
                        the current month and the current date
                     */
                    afterDate = today;

                    /*
                        this assigns an end date LocalDate with the previous year (current year - 1),
                        the last month and the last day of the year
                     */
                    beforeDate = LocalDate.of(today.getYear(), 12
                            , 31);

                    // this shows a filtered ledger with transactions between the start and end date
                    show(Reports.filterByDateRange(ledger, afterDate, beforeDate));
                    break;
                case 4:
                    /*
                        this assigns a start date LocalDate with the previous year (current year -1),
                        the first month and the first day of the year
                     */
                    afterDate = LocalDate.of(today.getYear() - 1,
                            1, 1);

                    /*
                        this assigns an end date LocalDate with the previous year (current year - 1),
                        the last month and the last day of the year
                     */
                    beforeDate = LocalDate.of(today.getYear() - 1, 12
                            , 31);

                    // this shows a filtered ledger with transactions between the start and end date
                    show(Reports.filterByDateRange(ledger, afterDate, beforeDate));
                    break;
                case 5:
                    System.out.print("Enter the vendor name: ");
                    vendor = scanner.nextLine();
                    show(Reports.filterByVendor(ledger, vendor));
                    break;
                case 6:
                    /* This section is a WIP while I update Reports to use lambdas
                    System.out.println("Enter a start date (MM-dd-yyyy leave blank if n/a)");
                    beforeDate = LocalDate.parse(scanner.nextLine());

                    System.out.println("Enter a end date (MM-dd-yyyy leave blank if n/a)");
                    afterDate = LocalDate.parse(scanner.nextLine());

                    System.out.println("Enter a description (leave blank if n/a)");
                    description = scanner.nextLine().trim();

                    System.out.println("Enter a vendor (leave blank if n/a");
                    vendor = scanner.nextLine().trim();

                    System.out.println("Enter a minimum amount (leave blank if n/a)");
                    minAmount = Double.parseDouble(scanner.nextLine());

                    System.out.println("Enter a maximum amount (leave blank if n/a)");
                    maxAmount = Double.parseDouble(scanner.nextLine());


                    ArrayList<Transaction> customSearch = new ArrayList<>();

                    if (!description.isEmpty()) {
                        //customSearch.addAll(Reports.filterByDescription());
                    }

                */
                    break;
                case 0:
                    ledgerScreen();
            }
        }
        catch (NumberFormatException | IOException | InterruptedException e) {
            System.out.println("Enter a number");
            Thread.sleep(1000);
            reportsScreen();
        }
        reportsScreen();
    }

    public static void show(ArrayList<Transaction> ledger) throws IOException, InterruptedException {


        for (Transaction t: ledger) {
            String date = t.getDate();
            String time = t.getTime();
            String description = t.getDescription();
            String vendor = t.getVendor();
            double amount = t.getAmount();

            System.out.printf("%s|%s|%s|%s|%.2f\n", date, time, description, vendor, amount);
        }

            Thread.sleep(1000);
    }

}
