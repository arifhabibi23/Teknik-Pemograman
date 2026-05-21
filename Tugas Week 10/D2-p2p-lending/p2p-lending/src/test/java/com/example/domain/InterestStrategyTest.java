package com.example.domain;

import com.example.domain.strategy.FixedInterestStrategy;
import com.example.domain.strategy.FloatingInterestStrategy;
import com.example.domain.strategy.InterestCalculationStrategy;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class InterestStrategyTest {

    private final BigDecimal principal = new BigDecimal("10000");
    private final BigDecimal rate = new BigDecimal("0.10"); 
    private final LocalDate start = LocalDate.of(2025, 1, 1);
    private final LocalDate end = LocalDate.of(2025, 4, 1);

    @Test
    void testFixedInterest() {
        InterestCalculationStrategy fixed = new FixedInterestStrategy();
        BigDecimal interest = fixed.calculateInterest(principal, rate, start, end);
        
        // Sekarang pasti Match dan GREEN
        assertEquals(new BigDecimal("246.58"), interest);
    }

    @Test
    void testFloatingInterest() {
        InterestCalculationStrategy floating = new FloatingInterestStrategy();
        BigDecimal interest = floating.calculateInterest(principal, rate, start, end);
        
        assertEquals(new BigDecimal("258.90"), interest);
    }
}