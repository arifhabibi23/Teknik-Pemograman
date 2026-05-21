package com.example.service;

import com.example.domain.entity.Lender;
import com.example.domain.entity.Loan;
import com.example.domain.entity.LoanFunding;
import com.example.domain.observer.EmailNotificationObserver;
import com.example.domain.observer.LoanFundingSubject;
import com.example.repository.inmemory.InMemoryLoanRepository;
import com.example.repository.interfaces.LoanRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Funding Service Unit Tests")
class FundingServiceTest {

    private FundingService fundingService;
    private LoanRepository loanRepository;
    private LoanFundingSubject subject;

    @BeforeEach
    void setUp() {
        loanRepository = new InMemoryLoanRepository();

        subject = new LoanFundingSubject();

        EmailNotificationObserver emailObserver =
                new EmailNotificationObserver();

        subject.addObserver(emailObserver);

        fundingService =
                new FundingService(loanRepository, subject);
    }

    @Test
    @DisplayName("Harus berhasil melakukan funding loan")
    void testFundLoanSuccess() {

        Loan loan =
                new Loan(
                        "LN001",
                        "BR001",
                        1000000
                );

        loanRepository.save(loan);

        Lender lender =
                new Lender(
                        "Abi",
                        "abi@gmail.com",
                        new BigDecimal("5000000")
                );

        LoanFunding funding =
                fundingService.fundLoan(
                        "LN001",
                        lender,
                        new BigDecimal("300000")
                );

        assertNotNull(funding);

        assertEquals(
                700000.0,
                loan.getBalance()
        );

        assertEquals(
                new BigDecimal("4700000"),
                lender.getBalance()
        );
    }

    @Test
    @DisplayName("Harus gagal jika loan tidak ditemukan")
    void testFundLoanLoanNotFound() {

        Lender lender =
                new Lender(
                        "Abi",
                        "abi@gmail.com",
                        new BigDecimal("5000000")
                );

        assertThrows(
                IllegalArgumentException.class,
                () -> fundingService.fundLoan(
                        "INVALID",
                        lender,
                        new BigDecimal("300000")
                )
        );
    }

    @Test
    @DisplayName("Harus gagal jika funding melebihi balance loan")
    void testFundingExceedsLoanBalance() {

        Loan loan =
                new Loan(
                        "LN002",
                        "BR002",
                        100000
                );

        loanRepository.save(loan);

        Lender lender =
                new Lender(
                        "Abi",
                        "abi@gmail.com",
                        new BigDecimal("5000000")
                );

        assertThrows(
                IllegalArgumentException.class,
                () -> fundingService.fundLoan(
                        "LN002",
                        lender,
                        new BigDecimal("200000")
                )
        );
    }

    @Test
    @DisplayName("Harus gagal jika saldo lender tidak mencukupi")
    void testInsufficientLenderBalance() {

        Loan loan =
                new Loan(
                        "LN003",
                        "BR003",
                        1000000
                );

        loanRepository.save(loan);

        Lender lender =
                new Lender(
                        "Abi",
                        "abi@gmail.com",
                        new BigDecimal("100000")
                );

        assertThrows(
                IllegalArgumentException.class,
                () -> fundingService.fundLoan(
                        "LN003",
                        lender,
                        new BigDecimal("300000")
                )
        );
    }
}