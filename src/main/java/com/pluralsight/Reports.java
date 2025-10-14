package com.pluralsight;

import java.time.LocalDate;
import java.util.ArrayList;

public class Reports {

    // this method returns an arraylist of transactions filtered by deposits
    public static ArrayList<Transaction> getDeposits(ArrayList<Transaction> ledger) {
        // create new arraylist with only deposits
        ArrayList<Transaction> deposits = new ArrayList<>();

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
        ArrayList<Transaction> payments = new ArrayList<>();

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
    public static ArrayList<Transaction> getByDate(ArrayList<Transaction> ledger, LocalDate beforeDate, LocalDate afterDate) {
        ArrayList<Transaction> sortedLedger = new ArrayList<>();

        // for each transaction checks if the is between the startDate or endDate, inclusive
        for (Transaction t: ledger){
            // if the date isAfter or isEqual to the start date...
            if ( (t.getLocalDate()).isAfter(afterDate) || (t.getLocalDate()).isEqual(afterDate) ) {
                // if the date isBefore or isEqual to the end date...
                if ( (t.getLocalDate()).isBefore(beforeDate) || (t.getLocalDate().isEqual(beforeDate)) ) {
                    // add it to the sorted ledger
                    sortedLedger.add(t);
                }
            }
        }

        return sortedLedger;

    }

    public static ArrayList<Transaction> getByVendor(ArrayList<Transaction> ledger, String vendor) {
        ArrayList<Transaction> sortedLedger = new ArrayList<>();

        for (Transaction t: ledger) {
            if (t.getVendor().equalsIgnoreCase(vendor) )
                sortedLedger.add(t);
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
