package com.example.domain.state;

import com.example.domain.entity.Loan;

public class ValidatedState implements LoanState {

    @Override
    public void handle(Loan context) {
        System.out.println("Pinjaman telah divalidasi. Sekarang beralih ke proses pendanaan (Funding)...");
        context.setState(new FundingState());
        // Transisi otomatis ke FundingState
    }

    @Override
    public void pay(Loan context, double amount) {
        System.out.println("Gagal: Tidak dapat melakukan pembayaran. Pinjaman masih dalam tahap validasi/persiapan pendanaan.");
    }

    @Override
    public void applyPenalty(Loan context) {
        System.out.println("Gagal: Penalti tidak dapat diterapkan pada pinjaman yang belum dicairkan.");
    }

    @Override
    public void cancel(Loan context) {
        System.out.println("Membatalkan pinjaman...");
        context.setState(new CancelledState());
    }
}