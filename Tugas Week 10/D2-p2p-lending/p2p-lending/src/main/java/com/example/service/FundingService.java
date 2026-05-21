package com.example.service;

import com.example.domain.entity.Loan;
import com.example.domain.entity.Lender;
import com.example.domain.entity.LoanFunding;
import com.example.domain.observer.LoanFundingSubject;
import com.example.repository.interfaces.LoanRepository;

import java.math.BigDecimal;

public class FundingService {
    private final LoanRepository loanRepository;
    private final LoanFundingSubject subject;

    public FundingService( LoanRepository loanRepository, 
                           LoanFundingSubject subject) {
        this.loanRepository = loanRepository;
        this.subject = subject;
    }

    public LoanFunding fundLoan(String loanId,
           Lender lender, BigDecimal amount) {
           Loan loan = loanRepository.findById(loanId).orElseThrow(
           () -> new IllegalArgumentException("Loan tidak ditemukan"));

           if (amount.doubleValue() > loan.getBalance()) {
               throw new IllegalArgumentException(
                       "Funding amount melebihi sisa loan");
           }
    
    LoanFunding funding = new LoanFunding(loan, lender, amount);
    double newBalance = loan.getBalance() - amount.doubleValue();
    loan.setBalance(newBalance);
    loanRepository.save(loan);

    subject.notifyObservers(
            "Loan ID " + loan.getId()
                    + " telah didanai dengan jumlah " 
                    + amount.toPlainString()
    );
    return funding;
    }
}