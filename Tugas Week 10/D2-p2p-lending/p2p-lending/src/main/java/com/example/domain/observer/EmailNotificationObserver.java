package com.example.domain.observer;

public class EmailNotificationObserver implements LoanFundingObserver {
    @Override
    public void update(String message) {
        System.out.println("Email Notification : " + message);
    }
}