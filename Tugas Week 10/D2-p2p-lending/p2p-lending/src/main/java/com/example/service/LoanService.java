package com.example.service;

import com.example.domain.entity.Loan;
import com.example.domain.state.CancelledState;
import com.example.repository.interfaces.LoanRepository;

public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan createLoan(String id, String borrowerId, double amount) {
        Loan loan = new Loan(id, borrowerId, amount);
        return loanRepository.save(loan);
    }

    public Loan getLoan(String id) {
        return getExistingLoan(id);
    }

    public void advanceState(String id) {
        Loan loan = getExistingLoan(id);
        loan.handle();
        loanRepository.save(loan);
    }

    public void makePayment(String id, double amount) {
        Loan loan = getExistingLoan(id);
        loan.pay(amount);
        loanRepository.save(loan);
    }

    public void applyPenalty(String id) {
        Loan loan = getExistingLoan(id);
        loan.applyPenalty();
        loanRepository.save(loan);
    }

    public void cancelLoan(String id) {
        Loan loan = getExistingLoan(id);
        loan.setState(new CancelledState());
        loanRepository.save(loan);
    }

    private Loan getExistingLoan(String id) {
        return loanRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Loan not found with id: " + id
                        ));
    }
}