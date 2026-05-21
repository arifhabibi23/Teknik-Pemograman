package com.example.domain.state;

import com.example.domain.entity.Loan;


public class FundingState implements LoanState {
    @Override
    public void handle(Loan context) {
        System.out.println("Pendanaan terpenuhi. Menuju proses pencairan (Disbursement)...");
        context.setState(new DisbursedState());
    }

    @Override
    public void pay(Loan context, double amount) {
        System.out.println("Gagal: Dana belum dicairkan ke peminjam.");
    }

    @Override
    public void applyPenalty(Loan context) {
        // Biasanya belum ada penalti di tahap ini
    }
    
    @Override
    public void cancel(Loan context) {
        System.out.println("Membatalkan pinjaman...");
        context.setState(new CancelledState());
    }
}