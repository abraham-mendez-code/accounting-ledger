package com.pluralsight;

import javax.swing.text.DateFormatter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    // Declare and initialize LocalDate and Local Time seperately, I do this to make formating other times easier
    private LocalDate localDate;
    private LocalTime localTime;

    // declare and initialize formatters for the Local date and time
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    // apply the formatter and store the result as a string
    private String date;
    private String time;

    // Declare the other parts of a transaction
    private String description;
    private String vendor;
    private double amount;

    // Declare and initialize String variable for transactions.csv directory
    private static final String filePath = "transactions.csv";

    // this is a Transaction constructor which initializes all class attributes
    public Transaction(String description, String vendor, double amount) {
        // set the localDate and localTime to now for this transaction instance
        this.localDate = LocalDate.now();
        this.localTime = LocalTime.now();

        // apply the formatters and store it as String values for this instance
        this.date = localDate.format(dateFormatter);
        this.time = localTime.format(timeFormatter);

        // set the description, vendor and amount to the passed values
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;

        // this adds the transaction to the csv file once it's created
        writeTransaction();

    }

    // these are the getters for the class attributes
    public LocalDate getLocalDate() {
        return localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    // These are the setters for the class attributes may not be needed, included for posterity

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    private void writeTransaction() {

        try {
            // this creates a new bufferedWriter, when declaring a new FileWriter pass true in order to append to the end of the file instead of overwriting
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true));

            // convert the transaction info into a string
            String transaction = String.format("%s|%s|%s|%s|%.2f", this.date, this.time, this.description, this.vendor, this.amount);

            // write the transaction and go to a new line
            bufferedWriter.write(transaction);
            bufferedWriter.newLine();

            // close the buffer
            bufferedWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
