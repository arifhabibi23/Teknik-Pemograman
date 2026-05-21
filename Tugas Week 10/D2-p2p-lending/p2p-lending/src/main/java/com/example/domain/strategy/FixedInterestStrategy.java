/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.domain.strategy;

/**
 *
 * @author firon
 */
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FixedInterestStrategy implements InterestCalculationStrategy {
    @Override
    public BigDecimal calculateInterest(BigDecimal principal, BigDecimal rate, LocalDate start, LocalDate end) {
        long days = ChronoUnit.DAYS.between(start, end);
        return principal.multiply(rate)
                        .multiply(new BigDecimal(days))
                        .divide(new BigDecimal("365"), 2, RoundingMode.HALF_UP);
    }
}