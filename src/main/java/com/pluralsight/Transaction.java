package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Transaction {

    // Declare and initialize LocalDate and Local Time seperately, I do this to make formating other times easier
    private LocalDate localDate = LocalDate.now();
    private LocalTime localTime = LocalTime.now();

    // Declare the other parts of a transaction
    private String description;
    private String vendor;
    private double amount;

    // this is a Transaction constructor which accepts all the class attributes upon initialization
    public Transaction(LocalDate localDate, LocalTime localTime, String description, String vendor, double amount) {
        this.localDate = localDate;
        this.localTime = localTime;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    // these are the getters for the class attributes, setters are not included.
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

}
