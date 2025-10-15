package com.pluralsight;

import java.time.LocalDate;
import java.util.ArrayList;

public class Reports {

    // this method returns an arraylist of transactions filtered by deposits
    public static ArrayList<Transaction> getDeposits(ArrayList<Transaction> ledger) {
        // create new arraylist with only deposits
        ArrayList<Transaction> deposits = new ArrayList<Transaction>();

        // for each transaction in the ledger, if the amount is greater than zero, it's a deposit, add it to the list.
        for (Transaction t: ledger) {
            if (t.getAmount() > 0) {
                deposits.add(t);
            }
        }
        // return the deposits list
        return deposits;
    }

    // this method returns an arraylist of transactions filtered by payments
    public static ArrayList<Transaction> getPayments(ArrayList<Transaction> ledger) {
        // create a new array list with only payments
        ArrayList<Transaction> payments = new ArrayList<Transaction>();

        // for each transaction in the ledger, if the amount is less than zero, it's a payment, add it to the list
        for (Transaction t: ledger){
            if (t.getAmount() < 0) {
                payments.add(t);
            }
        }
        // return the payments list
        return payments;
    }

    // this method returns an arraylist of transactions filtered by date range
    public static ArrayList<Transaction> filterByDateRange(ArrayList<Transaction> ledger, LocalDate startDate, LocalDate endDate) {
        ArrayList<Transaction> sortedLedger = new ArrayList<Transaction>();

        // for each transaction checks if the is between the startDate or endDate, inclusive
        for (Transaction t: ledger){
            // if the date isAfter or isEqual to the start date...
            if ( (t.getLocalDate()).isAfter(startDate) || (t.getLocalDate()).isEqual(startDate) ) {
                // if the date isBefore or isEqual to the end date...
                if ( (t.getLocalDate()).isBefore(endDate) || (t.getLocalDate().isEqual(endDate)) ) {
                    // add it to the sorted ledger
                    sortedLedger.add(t);
                }
            }
        }

        return sortedLedger;

    }

    // this method returns an arraylist of transactions filtered by vendor
    public static ArrayList<Transaction> filterByVendor(ArrayList<Transaction> ledger, String vendor) {
        ArrayList<Transaction> sortedLedger = new ArrayList<Transaction>();

        for (Transaction t: ledger) {
            if (t.getVendor().equals(vendor) )
                sortedLedger.add(t);
        }

        return sortedLedger;
    }

    // this method returns an arraylist of transactions filtered by description
    public static ArrayList<Transaction> filterByDescription(ArrayList<Transaction> ledger, String description) {

        ArrayList<Transaction> sortedLedger = new ArrayList<Transaction>();

        for (Transaction t: ledger) {
            if (t.getDescription().equals(description) )
                sortedLedger.add(t);
        }

        return sortedLedger;

    }

    // this method returns an arraylist of transactions filtered by amount
    public static ArrayList<Transaction> filterByAmount(ArrayList<Transaction> ledger, double minAmount, double maxAmount) {
        // declare a new arraylist to store a sorted ledger
        ArrayList<Transaction> sortedLedger = new ArrayList<Transaction>();

        // this loop goes through each transaction and add it to the sortedLedger if the amount is in range of min and max values
        for (Transaction t: ledger){

            if (t.getAmount() >= minAmount && t.getAmount() <= maxAmount ) {
                sortedLedger.add(t);
            }

        }

        return sortedLedger;

    }

    // this method returns an arraylist of transactions sorted by date and time
    public static ArrayList<Transaction> sortByRecent (ArrayList<Transaction> ledger) {

        // this uses a lamba expression to sort the ledger
        // the parameters are two transactions t1 and t2
        // the expression uses LocalDateTime compareTo to compare t2 to t1
        // this will go through each transaction in the ledger and return a sorted list
        ledger.sort( (t1, t2) -> {
            return t2.getLocalDateTime().compareTo(t1.getLocalDateTime());
        });

        return ledger;
    }

}
