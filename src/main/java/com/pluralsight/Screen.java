package com.pluralsight;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;



public class Screen extends Reports {

    private static Scanner scanner = new Scanner(System.in);

    public void homeScreen() throws InterruptedException, IOException {

        String header = """
                    
                    +==============+
                    |     Home     |
                    +==============+
                    
                """;

        String options = """
                D) Add Sale
                P) Add Expense
                L) Transactions page
                X) Exit
                Enter a command:\s""";

        System.out.print(header + options);

        String command = scanner.nextLine().substring(0,1).toLowerCase();

        switch (command) {
            case "d":
                Transaction.deposit();
                homeScreen();
            case "p":
                Transaction.payment();
                homeScreen();
            case "l":
                ledgerScreen();
            case "x":
                System.exit(0);
            default:
                System.out.println("Invalid Selection!");
                Thread.sleep(1000);
                homeScreen();
        }

    }

    public void ledgerScreen() throws IOException, InterruptedException {
        String header = """
                
                    +==============+
                    |    Ledger    |
                    +==============+
                
                """;

        String options = """
                A) All - Display all entries
                D) Restaurant Sales - Display only sales
                P) Restaurant Expenses - Display only expenses
                R) Reports - Run custom reports
                H) Home - Return to the homepage
                Enter a command:\s""";

        System.out.print(header + options);

        String command = scanner.nextLine().substring(0,1).toLowerCase();


        switch (command) {
            case "a":
                show(getLedger());
                break;
            case "d":
                //show only deposits
                show(getDeposits());
                break;
            case "p":
                // show only payments
                show(getPayments());
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

    public void reportsScreen() throws InterruptedException {
        String header = """
                
                    +===============+
                    |    Reports    |
                    +===============+
                
                """;

        String options = """
                1) Month to Date
                2) Previous Month
                3) Year to Date
                4) Previous Year
                5) Search by Vendor
                6) Custom Search
                0) Back
                Enter a command:\s""";

        System.out.print(header + options);

        try {
            int command = Integer.parseInt(scanner.nextLine());

            /*
                Declare local date variables which will be used to filter
                transactions chronologically, initialize today as today
             */
            LocalDate today = LocalDate.now();
            LocalDate beforeDate = today;
            LocalDate afterDate = LocalDate.MIN;


            String vendor = "";
            String description = "";
            double minAmount = 0;
            double maxAmount = Double.MAX_VALUE;


            switch (command) {
                case 1:
                    afterDate = today.withDayOfMonth(1);
                    beforeDate = today;
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

                    break;
                case 3:
                    /*
                        this assigns a start date LocalDate with the previous year (current year -1),
                        the current month and the current date
                     */
                    //beforeDate = today;

                    /*
                        this assigns an end date LocalDate with the previous year (current year - 1),
                        the last month and the last day of the year
                     */
                    afterDate = LocalDate.of(today.getYear(), 1
                            , 1);
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
                    break;
                case 5:
                    System.out.print("Enter the vendor name: ");
                    vendor = scanner.nextLine();
                    break;
                case 6:

                    // this prompts a user for optional input and trims the string value before storing it in a variable
                    System.out.println("Enter a start date (MM-dd-yyyy leave blank if n/a)");
                    String beforeDateInput = scanner.nextLine().trim();
                    beforeDate = beforeDateInput.isEmpty() ? LocalDate.now() : LocalDate.parse(beforeDateInput);

                    System.out.println("Enter a end date (MM-dd-yyyy leave blank if n/a)");
                    String afterDateInput = scanner.nextLine().trim();
                    afterDate = afterDateInput.isEmpty() ? LocalDate.MIN : LocalDate.parse(afterDateInput);

                    System.out.println("Enter a description (leave blank if n/a)");
                    description = scanner.nextLine().trim();

                    System.out.println("Enter a vendor (leave blank if n/a");
                    vendor = scanner.nextLine().trim();

                    System.out.println("Enter a minimum amount (leave blank if n/a)");
                    String minAmountInput = scanner.nextLine();
                    minAmount = minAmountInput.isEmpty() ? 0 : Double.parseDouble(minAmountInput);

                    System.out.println("Enter a maximum amount (leave blank if n/a)");
                    String maxAmountInput = scanner.nextLine();
                    maxAmount = maxAmountInput.isEmpty() ? Double.MAX_VALUE : Double.parseDouble(maxAmountInput);
                    break;
                case 0:
                    ledgerScreen();
            }
            show(search(afterDate, beforeDate, description, vendor, maxAmount, minAmount));
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

        if (ledger.isEmpty())
            System.out.println("No transactions match your search.");
        else
            System.out.println(ledger.size() + " entries with matching criteria.");
        ledger.clear();
        Thread.sleep(1000);
    }

}
