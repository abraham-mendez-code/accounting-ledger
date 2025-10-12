package com.pluralsight;

import java.util.ArrayList;

public class Reports {

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



}
