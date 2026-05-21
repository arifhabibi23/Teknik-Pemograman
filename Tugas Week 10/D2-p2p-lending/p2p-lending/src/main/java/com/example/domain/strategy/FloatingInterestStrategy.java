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

public class FloatingInterestStrategy extends FixedInterestStrategy {
    @Override
    public BigDecimal calculateInterest(BigDecimal principal, BigDecimal rate, LocalDate start, LocalDate end) {
        // Floating biasanya ada tambahan margin, misal 0.5% (0.005) sesuai testmu
        BigDecimal floatingRate = rate.add(new BigDecimal("0.005"));
        return super.calculateInterest(principal, floatingRate, start, end);
    }
}
