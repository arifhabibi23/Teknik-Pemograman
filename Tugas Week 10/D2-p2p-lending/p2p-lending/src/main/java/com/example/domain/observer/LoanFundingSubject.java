package com.example.domain.observer;

import java.util.ArrayList;
import java.util.List;

public class LoanFundingSubject {
    private final List<LoanFundingObserver> observers = new ArrayList<>();

    public void addObserver(LoanFundingObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(LoanFundingObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (LoanFundingObserver observer : observers) {
            observer.update(message);
        }
    }
}