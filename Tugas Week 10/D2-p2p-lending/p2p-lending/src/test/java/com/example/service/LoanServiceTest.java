package com.example.service;

import com.example.domain.entity.Loan;
import com.example.domain.state.*;
import com.example.repository.inmemory.InMemoryLoanRepository;
import com.example.repository.interfaces.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanServiceTest {

    private LoanService loanService;

    @BeforeEach
    void setUp() {
        LoanRepository repository = new InMemoryLoanRepository();
        loanService = new LoanService(repository);
    }

    // --- createLoan ---

    @Test
    void testCreateLoanReturnsLoanWithCorrectId() {
        Loan loan = loanService.createLoan("L001", "B001", 5000.0);

        assertEquals("L001", loan.getId());
    }

    @Test
    void testCreateLoanReturnsLoanWithCorrectBorrowerId() {
        Loan loan = loanService.createLoan("L001", "B001", 5000.0);

        assertEquals("B001", loan.getBorrowerId());
    }

    @Test
    void testCreateLoanReturnsLoanWithCorrectAmount() {
        Loan loan = loanService.createLoan("L001", "B001", 5000.0);

        assertEquals(5000.0, loan.getAmount());
    }

    @Test
    void testCreateLoanInitialStateIsSubmitted() {
        Loan loan = loanService.createLoan("L001", "B001", 5000.0);

        assertInstanceOf(SubmittedState.class, loan.getState());
    }

    // --- getLoan ---

    @Test
    void testGetLoanReturnsCorrectLoan() {
        loanService.createLoan("L002", "B002", 3000.0);

        Loan loan = loanService.getLoan("L002");

        assertNotNull(loan);
        assertEquals("L002", loan.getId());
    }

    @Test
    void testGetLoanThrowsForUnknownId() {
        assertThrows(
                IllegalArgumentException.class,
                () -> loanService.getLoan("UNKNOWN")
        );
    }

    // --- advanceState ---

    @Test
    void testAdvanceStateFromSubmittedToValidated() {
        loanService.createLoan("L003", "B003", 2000.0);

        loanService.advanceState("L003");

        assertInstanceOf(
                ValidatedState.class,
                loanService.getLoan("L003").getState()
        );
    }

    @Test
    void testAdvanceStateFromValidatedToFunding() {
        loanService.createLoan("L003", "B003", 2000.0);

        loanService.advanceState("L003");
        loanService.advanceState("L003");

        assertInstanceOf(
                FundingState.class,
                loanService.getLoan("L003").getState()
        );
    }

    @Test
    void testAdvanceStateFromFundingToDisbursed() {
        loanService.createLoan("L003", "B003", 2000.0);

        loanService.advanceState("L003");
        loanService.advanceState("L003");
        loanService.advanceState("L003");

        assertInstanceOf(
                DisbursedState.class,
                loanService.getLoan("L003").getState()
        );
    }

    @Test
    void testAdvanceStateThrowsForUnknownId() {
        assertThrows(
                IllegalArgumentException.class,
                () -> loanService.advanceState("GHOST")
        );
    }

    // --- makePayment ---

    @Test
    void testMakePaymentOnDisbursedReducesBalance() {
        loanService.createLoan("L004", "B004", 1000.0);

        loanService.advanceState("L004");
        loanService.advanceState("L004");
        loanService.advanceState("L004");

        loanService.makePayment("L004", 300.0);

        assertEquals(
                700.0,
                loanService.getLoan("L004").getBalance()
        );
    }

    @Test
    void testMakePaymentThrowsForUnknownId() {
        assertThrows(
                IllegalArgumentException.class,
                () -> loanService.makePayment("GHOST", 100.0)
        );
    }

    @Test
    void testMakePaymentOnCancelledDoesNotChangeBalance() {
        loanService.createLoan("L004", "B004", 1000.0);

        loanService.cancelLoan("L004");

        loanService.makePayment("L004", 300.0);

        assertEquals(
                1000.0,
                loanService.getLoan("L004").getBalance()
        );
    }

    // --- applyPenalty ---

    @Test
    void testApplyPenaltyOnDisbursedIncreasesBalance() {
        loanService.createLoan("L005", "B005", 1000.0);

        loanService.advanceState("L005");
        loanService.advanceState("L005");
        loanService.advanceState("L005");

        double before = loanService.getLoan("L005").getBalance();

        loanService.applyPenalty("L005");

        assertTrue(
                loanService.getLoan("L005").getBalance() > before
        );
    }

    @Test
    void testApplyPenaltyThrowsForUnknownId() {
        assertThrows(
                IllegalArgumentException.class,
                () -> loanService.applyPenalty("GHOST")
        );
    }

    @Test
    void testApplyPenaltyOnCancelledDoesNotChangeBalance() {
        loanService.createLoan("L005", "B005", 1000.0);

        loanService.cancelLoan("L005");

        loanService.applyPenalty("L005");

        assertEquals(
                1000.0,
                loanService.getLoan("L005").getBalance()
        );
    }

    // --- cancelLoan ---

    @Test
    void testCancelLoanSetsStateToCancelled() {
        loanService.createLoan("L006", "B006", 1000.0);

        loanService.cancelLoan("L006");

        assertInstanceOf(
                CancelledState.class,
                loanService.getLoan("L006").getState()
        );
    }

    @Test
    void testCancelLoanThrowsForUnknownId() {
        assertThrows(
                IllegalArgumentException.class,
                () -> loanService.cancelLoan("GHOST")
        );
    }

    @Test
    void testCancelAfterValidatedStillCancels() {
        loanService.createLoan("L006", "B006", 1000.0);

        loanService.advanceState("L006");

        loanService.cancelLoan("L006");

        assertInstanceOf(
                CancelledState.class,
                loanService.getLoan("L006").getState()
        );
    }
}