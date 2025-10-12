package com.pluralsight;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class tester {
    public static void main(String[] args) throws IOException {
        Ledger ledger = new Ledger();

        ArrayList<Transaction> transactions = ledger.getLedger();

        transactions = Reports.getByVendor(transactions, "Starbucks");

        LocalDate startDate = LocalDate.of(2022, 12, 1);
        LocalDate endDate = LocalDate.of(2025, 10, 12);

        for (Transaction t : transactions ) {
            System.out.print(t.getDate() + "|");
            System.out.print(t.getTime() + "|");
            System.out.print(t.getDescription() + "|");
            System.out.print(t.getVendor() + "|");
            System.out.print(t.getAmount() + "|\n");

        }

    }
}
