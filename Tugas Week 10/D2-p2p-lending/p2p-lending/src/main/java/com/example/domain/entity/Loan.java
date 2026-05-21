package com.example.domain.entity;

import com.example.domain.state.LoanState;
import com.example.domain.state.SubmittedState;

public class Loan {
    private String id;
    private String borrowerId;
    private double amount;
    private double balance;
    private double interestRate;
    private int termMonths;
    private LoanState state;

    // Constructor 3 parameter (lama)
    public Loan(String id, String borrowerId, double amount) {
        this.id = id;
        this.borrowerId = borrowerId;
        this.amount = amount;
        this.balance = amount;
        this.interestRate = 0;
        this.termMonths = 0;
        this.state = new SubmittedState();
    }

    // Constructor 5 parameter (dipakai di test)
    public Loan(String id, String borrowerId, double amount, double interestRate, int termMonths) {
        this.id = id;
        this.borrowerId = borrowerId;
        this.amount = amount;
        this.balance = amount;
        this.interestRate = interestRate;
        this.termMonths = termMonths;
        this.state = new SubmittedState();
    }

    public void handle() {
        state.handle(this);
    }

    /** Alias untuk handle() — dipakai di LoanStateTransitionTest */
    public void proceed() {
        state.handle(this);
    }

    public void pay(double amount) {
        state.pay(this, amount);
    }

    public void applyPenalty() {
        state.applyPenalty(this);
    }

    public void setState(LoanState state) {
        this.state = state;
    }

    public LoanState getState() {
        return state;
    }

    /** Alias untuk getState() — dipakai di LoanStateTransitionTest */
    public LoanState getCurrentState() {
        return state;
    }

    public String getId()            { return id; }
    public String getBorrowerId()    { return borrowerId; }
    public double getAmount()        { return amount; }
    public double getBalance()       { return balance; }
    public double getInterestRate()  { return interestRate; }
    public int getTermMonths()       { return termMonths; }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void cancel() {
        state.cancel(this);
    }
}