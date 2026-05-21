package com.example.service;

import com.example.domain.entity.Payment;
import com.example.domain.strategy.InterestCalculationStrategy;
import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentService {
    private final InterestCalculationStrategy interestStrategy;

    // Constructor ini WAJIB ada biar Test-nya nggak merah
    public PaymentService(InterestCalculationStrategy interestStrategy) {
        this.interestStrategy = interestStrategy;
    }

    public BigDecimal calculateTotalPayment(Payment payment, BigDecimal rate, LocalDate start, LocalDate end) {
        BigDecimal interest = interestStrategy.calculateInterest(payment.getAmount(), rate, start, end);
        return payment.getAmount().add(interest);
    }

    public void markAsPaid(Payment payment) {
        payment.setStatus("PAID");
        payment.setPaidDate(LocalDate.now());
    }
}