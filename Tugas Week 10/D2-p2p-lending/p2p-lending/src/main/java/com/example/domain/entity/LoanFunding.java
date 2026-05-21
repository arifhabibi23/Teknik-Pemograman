package com.example.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class LoanFunding {
    private final String fundingId;
    private final Loan loan;
    private final Lender lender;
    private final BigDecimal amount;
    private final LocalDateTime fundingDate;


    //Constructor
    public LoanFunding(Loan loan, Lender lender, BigDecimal amount) {

    // Validasi Constructor
        if (loan == null) {
            throw new IllegalArgumentException("Loan tidak boleh null");
        }

        if (lender == null) {
            throw new IllegalArgumentException("Lender tidak boleh  null");
        
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount harus lebih besar dari 0");
        }

        //Validasi Saldo Lender
        if (!lender.hasSufficientBalance(amount)) {
            throw new IllegalArgumentException("Saldo lender tidak mencukupi");
        }

        this.fundingId = UUID.randomUUID().toString();
        this.loan = loan;
        this.lender = lender;
        this.amount = amount;
        this.fundingDate = LocalDateTime.now();
        lender.deductBalance(amount);
    }

    //Getter
    public String getFundingId() {
        return fundingId;
    }

    public Loan getLoan() {
        return loan;
    }

    public Lender getLender() {
        return lender;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getFundingDate() {
        return fundingDate;
    }
}