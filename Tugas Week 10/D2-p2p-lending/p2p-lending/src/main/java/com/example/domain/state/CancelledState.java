package com.example.domain.state;

import com.example.domain.entity.Loan;

public class CancelledState implements LoanState {
    @Override
    public void handle(Loan context) {
        // Terminal state — tidak melakukan transisi apapun
        System.out.println("Pinjaman telah dibatalkan. Tidak ada aksi lebih lanjut.");
    }

    @Override
    public void pay(Loan context, double amount) {
        System.out.println("Gagal: Tidak bisa membayar pinjaman yang sudah batal.");
    }

    @Override
    public void applyPenalty(Loan context) {
        System.out.println("Tidak bisa menerapkan denda pada pinjaman batal.");
    }

    @Override
    public void cancel(Loan context) {
        System.out.println("Pinjaman sudah dalam status batal.");
    }
}