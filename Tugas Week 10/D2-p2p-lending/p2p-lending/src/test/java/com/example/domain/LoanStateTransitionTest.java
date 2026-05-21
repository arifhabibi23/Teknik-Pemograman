package com.example.domain;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.domain.entity.Loan;
import com.example.domain.state.CancelledState;
import com.example.domain.state.DisbursedState;
import com.example.domain.state.FundingState;
import com.example.domain.state.SubmittedState;
import com.example.domain.state.ValidatedState;

class LoanStateTransitionTest {
    private Loan loan;

    @BeforeEach
    void setUp() {
        // Setiap test dimulai dengan Loan baru (State: Submitted)
        loan = new Loan("L001", "B001", 10000.0, 0.1, 12);
    }

    @Test
    void testInitialStateIsSubmitted() {
        assertTrue(loan.getCurrentState() instanceof SubmittedState, 
            "Initial state harusnya SubmittedState");
    }

    @Test
    void testTransitionFromSubmittedToValidated() {
        loan.proceed(); // Menjalankan handle() di SubmittedState
        assertTrue(loan.getCurrentState() instanceof ValidatedState, 
            "Setelah Submitted diproses, harus pindah ke ValidatedState");
    }

    @Test
    void testTransitionToFundingAfterValidation() {
        loan.proceed(); // Submitted -> Validated
        loan.proceed(); // Validated -> Funding
        assertTrue(loan.getCurrentState() instanceof FundingState, 
            "Harus pindah ke FundingState setelah validasi selesai");
    }

    @Test
    void testDisbursementProcess() {
        // Melompat ke state tertentu untuk efisiensi testing
        loan.setState(new FundingState());
        
        loan.proceed(); // Funding -> Disbursed
        assertTrue(loan.getCurrentState() instanceof DisbursedState);
    }

    @Test
    void testTransitionToCancelled() {
        // Skenario pembatalan pinjaman
        loan.setState(new ValidatedState());
        
        loan.cancel(); // Memanggil cancel() di ValidatedState
        
        assertTrue(loan.getCurrentState() instanceof CancelledState);
        
        // Pastikan di state Cancelled, proceed tidak mengubah state lagi
        loan.proceed();
        assertTrue(loan.getCurrentState() instanceof CancelledState, 
            "CancelledState adalah terminal state, tidak boleh pindah lagi");
    }
}



