package com.example.service;

import com.example.domain.entity.Payment;
import com.example.domain.strategy.FixedInterestStrategy;
import com.example.domain.strategy.InterestCalculationStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Payment Service Unit Tests")
public class PaymentServiceTest {

    @Test
    @DisplayName("Harus berhasil menghitung total pembayaran (Pokok + Bunga)")
    void testCalculateTotalPayment() {
        // 1. Setup Strategy & Service
        // Kita pakai FixedInterestStrategy sebagai mock/implementasi uji
        InterestCalculationStrategy strategy = new FixedInterestStrategy();
        PaymentService service = new PaymentService(strategy);
        
        // 2. Siapkan Data Uji
        // Pinjam 10.000, Bunga 10% (0.10), Durasi 90 Hari
        Payment payment = new Payment("PAY-001", new BigDecimal("10000"));
        BigDecimal rate = new BigDecimal("0.10");
        LocalDate start = LocalDate.of(2025, 1, 1);
        LocalDate end = LocalDate.of(2025, 4, 1); 

        // 3. Action (Hitung)
        BigDecimal total = service.calculateTotalPayment(payment, rate, start, end);

        // 4. Assert (Ekspektasi: 10000 + 246.58 = 10246.58)
        // Gunakan compareTo karena BigDecimal sering beda di jumlah angka nol di belakang
        BigDecimal expected = new BigDecimal("10246.58");
        assertTrue(expected.compareTo(total) == 0, 
            "Total pembayaran salah! Ekspektasi: " + expected + ", tapi hasilnya: " + total);
    }

    @Test
    @DisplayName("Harus berhasil mengubah status menjadi PAID")
    void testMarkAsPaid() {
        // Setup (Bebas pakai strategy apa aja karena gak dipanggil di method ini)
        InterestCalculationStrategy strategy = new FixedInterestStrategy();
        PaymentService service = new PaymentService(strategy);
        Payment payment = new Payment("PAY-001", new BigDecimal("10000"));

        // Action
        service.markAsPaid(payment);

        // Assert
        assertEquals("PAID", payment.getStatus(), "Status harus berubah jadi PAID");
    }
}
