package com.example.domain.state;

import com.example.domain.entity.Loan;


public interface LoanState {
    void handle(Loan context);
    void pay(Loan context, double amount);
    void applyPenalty(Loan context);
    void cancel(Loan context);
}