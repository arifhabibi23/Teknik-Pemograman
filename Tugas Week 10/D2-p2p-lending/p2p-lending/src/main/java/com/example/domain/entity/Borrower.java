package com.example.domain.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class Borrower {

    private final String borrowerId;
    private final String name;
    private final String email;
    private final BigDecimal creditLimit;
    private BigDecimal usedCreditLimit;

    public Borrower(String name, String email, BigDecimal creditLimit) {
        require(name != null && !name.isBlank(),
                "Nama borrower tidak boleh kosong");
        require(email != null && email.contains("@"),
                "Email borrower tidak valid");
        require(creditLimit != null && creditLimit.compareTo(BigDecimal.ZERO) > 0,
                "Credit limit harus lebih dari 0");

        this.borrowerId = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.creditLimit = creditLimit;
        this.usedCreditLimit = BigDecimal.ZERO;
    }

    public boolean canBorrow(BigDecimal amount) {
        return getAvailableLimit().compareTo(amount) >= 0;
    }

    public void useLimit(BigDecimal amount) {
        if (!canBorrow(amount)) {
            throw new IllegalStateException(
                "Limit pinjaman tidak mencukupi. Tersedia: " + getAvailableLimit());
        }
        this.usedCreditLimit = this.usedCreditLimit.add(amount);
    }

    public void releaseLimit(BigDecimal amount) {
        this.usedCreditLimit = this.usedCreditLimit.subtract(amount);
        if (this.usedCreditLimit.compareTo(BigDecimal.ZERO) < 0)
            this.usedCreditLimit = BigDecimal.ZERO;
    }

    public BigDecimal getAvailableLimit() {
        return creditLimit.subtract(usedCreditLimit);
    }

    public String getBorrowerId()          { return borrowerId; }
    public String getName()                { return name; }
    public String getEmail()               { return email; }
    public BigDecimal getCreditLimit()     { return creditLimit; }
    public BigDecimal getUsedCreditLimit() { return usedCreditLimit; }

    private static void require(boolean condition, String message) {
        if (!condition) throw new IllegalArgumentException(message);
    }
}