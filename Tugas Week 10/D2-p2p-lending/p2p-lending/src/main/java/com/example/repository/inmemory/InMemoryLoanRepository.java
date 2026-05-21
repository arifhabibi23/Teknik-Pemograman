package com.example.repository.inmemory;

import com.example.domain.entity.Loan;
import com.example.repository.interfaces.LoanRepository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementasi in-memory LoanRepository menggunakan LinkedHashMap.
 * Digunakan selama pengembangan dan untuk keperluan pengujian.
 */
public class InMemoryLoanRepository implements LoanRepository {

    // LinkedHashMap menjaga urutan insert — berguna saat debug & testing
    private final Map<String, Loan> store = new LinkedHashMap<>();

    @Override
    public Loan save(Loan loan) {
        store.put(loan.getId(), loan);
        return loan;
    }

    @Override
    public Optional<Loan> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Loan> findAll() {
        return Collections.unmodifiableList(new ArrayList<>(store.values()));
    }

    @Override
    public List<Loan> findByBorrowerId(String borrowerId) {
        return store.values().stream()
                .filter(loan -> loan.getBorrowerId().equals(borrowerId))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void deleteById(String id) {
        store.remove(id);
    }

    @Override
    public boolean existsById(String id) {
        return store.containsKey(id);
    }

    // Helper methods — berguna untuk verifikasi di test
    public int size()  { return store.size(); }
    public void clear(){ store.clear(); }
}