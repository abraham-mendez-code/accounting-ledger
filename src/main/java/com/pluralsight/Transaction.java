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
    private LocalDateTime localDateTime;

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

    // this constructor creates a new transaction to write
    public Transaction(String description, String vendor, double amount) {
        // set the localDate and localTime to now for this transaction instance, localTime goes first for accuracy.
        this.localTime = LocalTime.now();
        this.localDate = LocalDate.now();

        this.localDateTime = LocalDateTime.of(localDate,localTime);

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

    // this overloaded constructor creates a new transaction for use of class methods
    public Transaction(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;

        // this parses the date and time strings for LocalDate and LocalTime values them assigns to attributes
        this.localDate = LocalDate.parse(date);
        this.localTime = LocalTime.parse(time);

        // assigns a combines LocalDateTime
        this.localDateTime = LocalDateTime.of(this.localDate,this.localTime);

        this.description = description;
        this.vendor = vendor;
        this.amount = amount;

    }

    // these are the getters for the class attributes, read only
    public LocalDate getLocalDate() {
        localDate = LocalDate.parse(this.date, dateFormatter);
        return localDate;
    }

    // get the localTime attribute for this transaction
    public LocalTime getLocalTime() {
        localTime = LocalTime.parse(this.time, timeFormatter);
        return localTime;
    }

    // get the localDate for this transaction as a String
    public String getDate() {
        return date;
    }

    // get the localTime for this transaction as a String
    public String getTime() {
        return time;
    }

    // get the localDateTime attribute
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    // get the description attribute for this transaction
    public String getDescription() {
        return description;
    }

    // get the vendor for this transaction
    public String getVendor() {
        return vendor;
    }

    // get the amount for this transaction
    public double getAmount() {
        return amount;
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
