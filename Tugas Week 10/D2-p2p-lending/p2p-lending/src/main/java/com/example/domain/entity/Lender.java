package com.example.domain.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class Lender {

    private final String lenderId;
    private final String name;
    private final String email;
    private BigDecimal balance;

    public Lender(String name, String email, BigDecimal initialBalance) {
        require(name != null && !name.isBlank(),
                "Nama lender tidak boleh kosong");
        require(email != null && email.contains("@"),
                "Email lender tidak valid");
        require(initialBalance != null && initialBalance.compareTo(BigDecimal.ZERO) >= 0,
                "Balance tidak boleh negatif");

        this.lenderId = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.balance = initialBalance;
    }

    public boolean hasSufficientBalance(BigDecimal amount) {
        return balance.compareTo(amount) >= 0;
    }

    public void deductBalance(BigDecimal amount) {
        require(hasSufficientBalance(amount), "Saldo lender tidak mencukupi");
        this.balance = this.balance.subtract(amount);
    }

    public void addBalance(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public String getLenderId()    { return lenderId; }
    public String getName()        { return name; }
    public String getEmail()       { return email; }
    public BigDecimal getBalance() { return balance; }

    private static void require(boolean condition, String message) {
        if (!condition) throw new IllegalArgumentException(message);
    }
}