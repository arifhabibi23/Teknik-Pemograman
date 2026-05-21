package com.example.domain.state;

import com.example.domain.entity.Loan;


public class SubmittedState implements LoanState {
    @Override
    public void handle(Loan context) {
        System.out.println("Memvalidasi dokumen pinjaman...");
        context.setState(new ValidatedState());
    }

    @Override
    public void pay(Loan context, double amount) {
        System.out.println("Gagal: Pinjaman belum divalidasi.");
    }

    @Override
    public void applyPenalty(Loan context) {
        System.out.println("Tidak ada penalti untuk pinjaman yang baru diajukan.");
    }

    @Override
    public void cancel(Loan context) {
        System.out.println("Membatalkan pinjaman...");
        context.setState(new CancelledState());
    }
}
