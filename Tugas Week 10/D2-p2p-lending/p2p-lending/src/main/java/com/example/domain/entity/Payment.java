/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Payment {
    private String paymentId;
    private BigDecimal amount;
    private LocalDate paidDate;
    private String status;

    // Constructor yang diminta oleh PaymentServiceTest
    public Payment(String id, BigDecimal amount) {
        this.paymentId = id;
        this.amount = amount;
        this.status = "UNPAID";
    }

    // Getter & Setter yang dibutuhkan Service
    public BigDecimal getAmount() { return amount; }
    
    public String getStatus() { return status; }
    
    public void setStatus(String status) { this.status = status; }

    public void setPaidDate(LocalDate paidDate) { this.paidDate = paidDate; }
}