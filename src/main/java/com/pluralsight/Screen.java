package com.pluralsight;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;



public class Screen extends Reports {

    private static Scanner scanner = new Scanner(System.in); // declare the scanner here so I don't have to do it in each method

    // declare and initialize formatters for the Local date and time here for visibility
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    // this method displays the homescreen for this application and accepts user inputs for traversal or completing tasks
    public void homeScreen() throws InterruptedException, IOException {

        String header = """

                 ('-. .-.             _   .-')       ('-. \s
                ( OO )  /            ( '.( OO )_   _(  OO)\s
                ,--. ,--. .-'),-----. ,--.   ,--.)(,------.
                |  | |  |( OO'  .-.  '|   `.'   |  |  .---'
                |   .|  |/   |  | |  ||         |  |  |   \s
                |       |\\_) |  |\\|  ||  |'.'|  | (|  '--.\s
                |  .-.  |  \\ |  | |  ||  |   |  |  |  .--'\s
                |  | |  |   `'  '-'  '|  |   |  |  |  `---.
                `--' `--'     `-----' `--'   `--'  `------'
                                
                """;

        String options = """
                Choose from the following options
                D) Add Sale
                P) Add Expense
                L) Transactions page
                X) Exit
                Enter a command:\s""";

        // this prints out the menu header and options
        System.out.print(header + options);

        // this reads the next line using scanner and gets the character between in index 0 and 1 (the first char) and stores it as a string
        String command = scanner.nextLine();
        command = command.isEmpty() ? "" : command.substring(0,1).toLowerCase();

        // this switch reads the input command and a executes task or switches menus
        switch (command) {
            case "d":
                Transaction.deposit(); // make a new deposit using the Transaction class
                scanner.nextLine(); // wait for userinput to continue
                break;
            case "p":
                Transaction.payment();
                scanner.nextLine();
                break;
            case "l":
                ledgerScreen();
                break;
            case "x":
                System.exit(0);
            default:
                // prints out an error message if the user enters an unfamiliar command and displays the homescreen after a delay
                System.out.println("Invalid Selection!");
                Thread.sleep(2000);
                break;
        }
        homeScreen(); // show the homescreen again
    }

    // this method displays the ledger screen
    public void ledgerScreen() throws IOException, InterruptedException {
        String header = """

                             ('-.  _ .-') _                 ('-.  _  .-') \s
                           _(  OO)( (  OO) )              _(  OO)( \\( -O )\s
                 ,--.     (,------.\\     .'_   ,----.    (,------.,------.\s
                 |  |.-')  |  .---',`'--..._) '  .-./-')  |  .---'|   /`. '
                 |  | OO ) |  |    |  |  \\  ' |  |_( O- ) |  |    |  /  | |
                 |  |`-' |(|  '--. |  |   ' | |  | .--, \\(|  '--. |  |_.' |
                (|  '---.' |  .--' |  |   / :(|  | '. (_/ |  .--' |  .  '.'
                 |      |  |  `---.|  '--'  / |  '--'  |  |  `---.|  |\\  \\\s
                 `------'  `------'`-------'   `------'   `------'`--' '--'
                
                """;

        String options = """
                Choose from the following options
                A) All Transactions
                D) Restaurant Sales
                P) Restaurant Expenses
                R) Run a Report
                H) Home
                Enter a command:\s""";

        System.out.print(header + options);

        String command = scanner.nextLine();

        command = command.isEmpty() ? "" : command.substring(0,1).toLowerCase();

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
                // prints out an error message if the user enters an unfamiliar command and displays the homescreen after a delay
                System.out.println("Invalid Selection!");
                Thread.sleep(2000);
        }
        ledgerScreen();
    }

    // this method display the reports screen
    public void reportsScreen() throws InterruptedException {
        String header = """

                 _  .-')     ('-.     _ (`-.              _  .-')   .-') _     .-')  \s
                ( \\( -O )  _(  OO)   ( (OO  )            ( \\( -O ) (  OO) )   ( OO ).\s
                 ,------. (,------. _.`     \\ .-'),-----. ,------. /     '._ (_)---\\_)
                 |   /`. ' |  .---'(__...--''( OO'  .-.  '|   /`. '|'--...__)/    _ |\s
                 |  /  | | |  |     |  /  | |/   |  | |  ||  /  | |'--.  .--'\\  :` `.\s
                 |  |_.' |(|  '--.  |  |_.' |\\_) |  |\\|  ||  |_.' |   |  |    '..`''.)
                 |  .  '.' |  .--'  |  .___.'  \\ |  | |  ||  .  '.'   |  |   .-._)   \\
                 |  |\\  \\  |  `---. |  |        `'  '-'  '|  |\\  \\    |  |   \\       /
                 `--' '--' `------' `--'          `-----' `--' '--'   `--'    `-----'\s
                                
                """;

        String options = """
                Choose from the following options
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


            // declare and initialize variables for custom searches
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
                    System.out.println("From which date? (e.g MM-dd-yyyy) leave blank if n/a");
                    String beforeDateInput = scanner.nextLine().trim();
                    beforeDate = beforeDateInput.isEmpty() ? LocalDate.now() : LocalDate.parse(beforeDateInput, dateFormatter); // check if the input is empty before parsing, if empty assign default value

                    System.out.println("To which date? (MM-dd-yyyy) leave blank if n/a");
                    String afterDateInput = scanner.nextLine().trim();
                    afterDate = afterDateInput.isEmpty() ? LocalDate.MIN : LocalDate.parse(afterDateInput, dateFormatter);

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

            // this shows the search results
            show(search(afterDate, beforeDate, description, vendor, maxAmount, minAmount));
        }
        catch (NumberFormatException | IOException | InterruptedException e) {
            System.out.println("Invalid input detected. Enter a number 0-6");
            Thread.sleep(2000);
            reportsScreen();
        }
        reportsScreen();
    }


    public static void show(ArrayList<Transaction> ledger) throws IOException, InterruptedException {

        // this loop access each element in the ledger and prints its fields
        for (Transaction t: ledger) {
            String date = t.getDate();
            String time = t.getTime();
            String description = t.getDescription();
            String vendor = t.getVendor();
            double amount = t.getAmount();

            System.out.printf("%s|%s|%s|%s|%.2f\n", date, time, description, vendor, amount);
        }

        // if the ledger is empty print out a message otherwise print out the number of entries found
        if (ledger.isEmpty())
            System.out.println("No transactions match your search.");
        else
            System.out.println(ledger.size() + " entries with matching criteria.");
        // clear the ledger to prevent appending on consecutive runs.
        ledger.clear();
        // wait for user input to continue after display
        scanner.nextLine();

    }

}
