package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

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
    public static ArrayList<Transaction> getTransactionsByDate(ArrayList<Transaction> ledger, LocalDate startDate, LocalDate endDate) {
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

    // this method returns an arraylist of transactions sorted by date and time
    public static ArrayList<Transaction> sortByRecent (ArrayList<Transaction> ledger) {

        // this loop will only end if date and time of each element and each sequential element
        for (int i = 0; i < ledger.size() - 1; i++) {
            /*
                this compares the date of the current transaction to the date of the next transaction using ChronoLocalDate.compareTo()
                compareTo returns -1  if the first comparator is less then the second and 1 if the first comparator is greater than the second
                the most recent date will be greater than the others
             */

            // create a variable to store the combined localdatetime of the current transaction
            LocalDateTime localDateTimeOne = ledger.get(i).getLocalDateTime();

            // create a variable to store the combined localdatetime of the next transaction
            LocalDateTime localDateTimeTwo = LocalDateTime.of(ledger.get(i + 1).getLocalDate(), ledger.get(i + 1).getLocalTime());

            /*
                this compares the date of the current transaction to the date of the next transaction using ChronoLocalDate.compareTo()
                compareTo returns -1  if the first comparator is less then the second and 1 if the first comparator is greater than the second
                the most recent date will be greater than the others
             */

            if (localDateTimeOne.compareTo(localDateTimeTwo) < 0) {
                // swap the transactions to put the most recent first
                Collections.swap(ledger, i, i + 1); // swap the current element with the next one
                i = 0; // reset the loop
            } else {
                i++; // this element will only increase if the list is properly sorted
            }

        }

        return ledger;
    }

}
