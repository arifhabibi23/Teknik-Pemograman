package com.example.domain;

import com.example.domain.entity.Loan;
import com.example.domain.state.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanTest {

    private Loan loan;

    @BeforeEach
    void setUp() {
        loan = new Loan("L001", "B001", 1000.0);
    }

    // --- Initial State ---

    @Test
    void testInitialStateIsSubmitted() {
        assertInstanceOf(SubmittedState.class, loan.getState());
    }

    @Test
    void testInitialBalanceEqualsAmount() {
        assertEquals(1000.0, loan.getBalance());
    }

    @Test
    void testInitialId() {
        assertEquals("L001", loan.getId());
    }

    @Test
    void testInitialBorrowerId() {
        assertEquals("B001", loan.getBorrowerId());
    }

    @Test
    void testInitialAmount() {
        assertEquals(1000.0, loan.getAmount());
    }

    // --- State Transitions via handle() ---

    @Test
    void testHandleFromSubmittedMovesToValidated() {
        loan.handle(); // Submitted -> Validated
        assertInstanceOf(ValidatedState.class, loan.getState());
    }

    @Test
    void testHandleFromValidatedMovesToFunding() {
        loan.handle(); // Submitted -> Validated
        loan.handle(); // Validated -> Funding
        assertInstanceOf(FundingState.class, loan.getState());
    }

    @Test
    void testHandleFromFundingMovesToDisbursed() {
        loan.handle(); // Submitted -> Validated
        loan.handle(); // Validated -> Funding
        loan.handle(); // Funding -> Disbursed
        assertInstanceOf(DisbursedState.class, loan.getState());
    }

    @Test
    void testHandleFromDisbursedStaysDisbursed() {
        loan.handle();
        loan.handle();
        loan.handle();
        loan.handle(); // Still Disbursed
        assertInstanceOf(DisbursedState.class, loan.getState());
    }

    @Test
    void testHandleFromCancelledStaysCancelled() {
        loan.setState(new CancelledState());
        loan.handle();
        assertInstanceOf(CancelledState.class, loan.getState());
    }

    // --- Cancel State ---

    @Test
    void testSetStateToCancelled() {
        loan.setState(new CancelledState());
        assertInstanceOf(CancelledState.class, loan.getState());
    }

    // --- Pay ---

    @Test
    void testPayOnDisbursedReducesBalance() {
        loan.handle(); // Validated
        loan.handle(); // Funding
        loan.handle(); // Disbursed
        loan.pay(200.0);
        assertEquals(800.0, loan.getBalance());
    }

    @Test
    void testPayOnCancelledDoesNotChangeBalance() {
        loan.setState(new CancelledState());
        loan.pay(200.0);
        assertEquals(1000.0, loan.getBalance());
    }

    @Test
    void testPayOnSubmittedDoesNotChangeBalance() {
        loan.pay(200.0);
        assertEquals(1000.0, loan.getBalance());
    }

    // --- Apply Penalty ---

    @Test
    void testApplyPenaltyOnDisbursedIncreasesBalance() {
        loan.handle();
        loan.handle();
        loan.handle(); // Disbursed

        double balanceBefore = loan.getBalance();

        loan.applyPenalty();

        assertTrue(loan.getBalance() > balanceBefore);
    }

    @Test
    void testApplyPenaltyOnCancelledDoesNotChangeBalance() {
        loan.setState(new CancelledState());
        loan.applyPenalty();
        assertEquals(1000.0, loan.getBalance());
    }

    @Test
    void testApplyPenaltyOnSubmittedDoesNotChangeBalance() {
        loan.applyPenalty();
        assertEquals(1000.0, loan.getBalance());
    }

    // --- setState ---

    @Test
    void testSetStateDirectly() {
        loan.setState(new ValidatedState());
        assertInstanceOf(ValidatedState.class, loan.getState());
    }

    @Test
    void testSetBalanceDirectly() {
        loan.setBalance(500.0);
        assertEquals(500.0, loan.getBalance());
    }
}