package com.pluralsight.capstone;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class Reports {
    // this string stores the file path
    private static final String filePath = "transactions.csv";

    // this method returns an arraylist of transactions filtered by deposits
    public ArrayList<Transaction> getDeposits() throws IOException {

        return (ArrayList<Transaction>) getLedger().stream()
                .filter(t -> t.getAmount() > 0)
                .collect(Collectors.toList());

    }

    // this method returns an arraylist of transactions filtered by payments
    public ArrayList<Transaction> getPayments() throws IOException {

        return (ArrayList<Transaction>) getLedger().stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.toList());
    }

    public ArrayList<Transaction> search(
                                                LocalDate afterDate,
                                                LocalDate beforeDate,
                                                String description,
                                                String vendor,
                                                double maxAmount,
                                                double minAmount) throws IOException {

        ArrayList<Transaction> results = (ArrayList<Transaction>) getLedger().stream()
                .filter(t ->  description.isEmpty() || t.getDescription().toLowerCase().contains(description.toLowerCase()))
                .filter(t ->  vendor.isEmpty() || t.getVendor().toLowerCase().contains(vendor.toLowerCase()))
                .filter(t ->  maxAmount >= Math.abs(t.getAmount()) && minAmount <= Math.abs(t.getAmount()) )
                .filter(t -> t.getLocalDate().isAfter(afterDate) || t.getLocalDate().isEqual(afterDate) )
                .filter(t -> t.getLocalDate().isBefore(beforeDate) || t.getLocalDate().isEqual(beforeDate))
                .collect(Collectors.toList());

        return sortByRecent(results);
    }

    // this method returns an arraylist of transactions sorted by date and time
    public ArrayList<Transaction> sortByRecent (ArrayList<Transaction> ledger) {

        // this uses a lamba expression to sort the ledger
        // the parameters are two transactions t1 and t2
        // the expression uses LocalDateTime compareTo to compare t2 to t1
        // this will go through each transaction in the ledger and return a sorted list
        ledger.sort( (t1, t2) -> {
            return t2.getLocalDateTime().compareTo(t1.getLocalDateTime());
        });

        return ledger;
    }

    public ArrayList<Transaction> getLedger() throws IOException {
        ArrayList<Transaction> ledger = new ArrayList<Transaction>();

        // create a new bufferedReader to read from the file
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

        // declare a string to hold the current line
        String currentLine;

        // this assigns the content of current line to a string and continues to read each line until its null
        while ( (currentLine = bufferedReader.readLine()) != null ) {
            // split the current line into tokens
            String[] tokens = currentLine.split("[|]");

            // this assigns the tokens to their respective variables
            String date = tokens[0];
            String time = tokens[1];
            String description = tokens[2];
            String vendor = tokens[3];
            double amount = Double.parseDouble(tokens[4]);

            // declare a new read only transaction
            Transaction transaction = new Transaction(date, time, description, vendor, amount);

            // add the transaction to the ledger
            ledger.add(transaction);
        }

        return sortByRecent(ledger);


    }

}
