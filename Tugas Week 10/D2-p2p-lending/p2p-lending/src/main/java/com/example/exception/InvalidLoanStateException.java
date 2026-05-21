package com.example.exception;

/**
 * Dilempar saat terjadi transisi state yang tidak valid.
 * Contoh: mencoba mencairkan pinjaman yang belum didanai.
 *
 * Digunakan oleh: concrete state classes (ipi's team) dan LoanService.
 */
public class InvalidLoanStateException extends RuntimeException {

    public InvalidLoanStateException(String message) {
        super(message);
    }

    public InvalidLoanStateException(String message, Throwable cause) {
        super(message, cause);
    }
}