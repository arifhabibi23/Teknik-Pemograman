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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public interface InterestCalculationStrategy {
    BigDecimal calculateInterest(BigDecimal principal, BigDecimal rate, LocalDate start, LocalDate end);
}