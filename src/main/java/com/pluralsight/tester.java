package com.pluralsight;

import java.io.IOException;

public class tester {
    public static void main(String[] args) throws IOException {
        Ledger ledger = new Ledger();

        for (Transaction transaction : Reports.sortByRecent(ledger.getLedger())) {
            System.out.print(transaction.getDate() + "|");
            System.out.print(transaction.getTime() + "|");
            System.out.print(transaction.getDescription() + "|");
            System.out.print(transaction.getVendor() + "|");
            System.out.print(transaction.getAmount() + "|\n");

        }


    }
}
