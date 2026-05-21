package com.example.repository.interfaces;
import com.example.domain.entity.Loan;

import java.util.List;
import java.util.Optional;

/**
 * Kontrak repository untuk Loan.
 * Implementasi konkret: InMemoryLoanRepository (haafiz)
 * Implementasi DB bisa ditambah nanti tanpa mengubah interface ini.
 */
public interface LoanRepository {

    /** Simpan atau perbarui loan (upsert berdasarkan ID). */
    Loan save(Loan loan);

    /** Cari loan berdasarkan ID; kembalikan empty jika tidak ditemukan. */
    Optional<Loan> findById(String id);

    /** Kembalikan semua loan yang ada. */
    List<Loan> findAll();

    /** Kembalikan semua loan milik seorang borrower. */
    List<Loan> findByBorrowerId(String borrowerId);

    /** Hapus loan berdasarkan ID. */
    void deleteById(String id);

    /** Cek apakah loan dengan ID tertentu ada. */
    boolean existsById(String id);
}
