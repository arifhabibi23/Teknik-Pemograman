package com.example;

import com.example.domain.observer.EmailNotificationObserver;
import com.example.domain.observer.LoanFundingSubject;

public class Main {
    public static void main(String[] args) {
        LoanFundingSubject subject = new LoanFundingSubject();
        EmailNotificationObserver emailObserver = new EmailNotificationObserver();
        subject.addObserver(emailObserver);
        subject.notifyObservers("Loan berhasil didanai");
    }
}