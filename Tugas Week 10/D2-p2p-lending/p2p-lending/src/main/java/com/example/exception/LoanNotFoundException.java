package com.example.exception;
/**
 * Dilempar saat loan dengan ID tertentu tidak ditemukan di repository.
 * LoanService melempar exception ini di setiap operasi yang membutuhkan loan.
 */
public class LoanNotFoundException extends RuntimeException {

    public LoanNotFoundException(String message) {
        super(message);
    }

    public LoanNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}