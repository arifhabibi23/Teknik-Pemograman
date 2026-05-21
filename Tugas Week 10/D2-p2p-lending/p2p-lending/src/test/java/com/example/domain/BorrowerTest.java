package com.example.domain;

import com.example.domain.entity.Borrower;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Borrower Domain Tests - Validasi Limit Pinjaman")
class BorrowerTest {

    @Test
    @DisplayName("Borrower baru memiliki available limit sama dengan credit limit")
    void newBorrowerAvailableLimitEqualsCreditLimit() {
        Borrower borrower = new Borrower("Andi", "andi@test.com", new BigDecimal("50000000"));
        assertEquals(new BigDecimal("50000000"), borrower.getAvailableLimit());
    }

    @Test
    @DisplayName("canBorrow true jika amount tidak melebihi available limit")
    void canBorrowReturnsTrueWhenWithinLimit() {
        Borrower borrower = new Borrower("Andi", "andi@test.com", new BigDecimal("50000000"));
        assertTrue(borrower.canBorrow(new BigDecimal("30000000")));
    }

    @Test
    @DisplayName("canBorrow false jika amount melebihi available limit")
    void canBorrowReturnsFalseWhenExceedsLimit() {
        Borrower borrower = new Borrower("Andi", "andi@test.com", new BigDecimal("50000000"));
        assertFalse(borrower.canBorrow(new BigDecimal("60000000")));
    }

    @Test
    @DisplayName("useLimit mengurangi available limit")
    void useLimitDeductsAvailableLimit() {
        Borrower borrower = new Borrower("Andi", "andi@test.com", new BigDecimal("50000000"));
        borrower.useLimit(new BigDecimal("20000000"));
        assertEquals(new BigDecimal("30000000"), borrower.getAvailableLimit());
    }

    @Test
    @DisplayName("useLimit melebihi limit harus lempar exception")
    void useLimitExceedingAvailableShouldThrow() {
        Borrower borrower = new Borrower("Andi", "andi@test.com", new BigDecimal("10000000"));
        assertThrows(IllegalStateException.class,
            () -> borrower.useLimit(new BigDecimal("20000000")));
    }

    @Test
    @DisplayName("releaseLimit mengembalikan available limit")
    void releaseLimitRestoresAvailableLimit() {
        Borrower borrower = new Borrower("Andi", "andi@test.com", new BigDecimal("50000000"));
        borrower.useLimit(new BigDecimal("20000000"));
        borrower.releaseLimit(new BigDecimal("20000000"));
        assertEquals(new BigDecimal("50000000"), borrower.getAvailableLimit());
    }
}