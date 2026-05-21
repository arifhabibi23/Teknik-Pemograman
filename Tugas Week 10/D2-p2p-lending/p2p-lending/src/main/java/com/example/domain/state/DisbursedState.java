package com.example.domain.state;

import com.example.domain.entity.Loan;

public class DisbursedState implements LoanState {

    @Override
    public void handle(Loan context) {
        System.out.println("Pinjaman sudah aktif dan dalam masa cicilan.");
    }

    @Override
    public void pay(Loan context, double amount) {
        System.out.println("Pembayaran diterima sebesar: " + amount);

        double newBalance = context.getBalance() - amount;

        // Hindari saldo minus
        if (newBalance < 0) {
            newBalance = 0;
        }

        context.setBalance(newBalance);
    }

    @Override
    public void applyPenalty(Loan context) {
        System.out.println("Menerapkan denda keterlambatan pada pinjaman.");

        // Contoh denda 10%
        double penalty = context.getBalance() * 0.10;

        context.setBalance(
                context.getBalance() + penalty
        );
    }

    @Override
    public void cancel(Loan context) {
        System.out.println("Membatalkan pinjaman...");
        context.setState(new CancelledState());
    }
}