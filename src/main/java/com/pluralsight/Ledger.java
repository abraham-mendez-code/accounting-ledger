package com.pluralsight;

import java.io.*;
import java.util.ArrayList;

public class Ledger {

    // Declare class attributes
    private static ArrayList<Transaction> ledger = new ArrayList<Transaction>();
    private double currentBalance;

    // this string stores the file path
    private static final String filePath = "transactions.csv";


    public double getCurrentBalance() {
        return currentBalance;
    }

    public static ArrayList<Transaction> getLedger() throws IOException {

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

        ledger = Reports.sortByRecent(ledger);

        return ledger;
    }

}
