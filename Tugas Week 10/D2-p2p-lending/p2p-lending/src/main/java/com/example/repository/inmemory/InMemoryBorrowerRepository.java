package com.example.repository.inmemory;

import com.example.domain.entity.Borrower;
import com.example.repository.interfaces.BorrowerRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryBorrowerRepository implements BorrowerRepository {

    private final Map<String, Borrower> storage = new HashMap<>();

    @Override
    public void save(Borrower borrower) {
        storage.put(borrower.getBorrowerId(), borrower);
    }

    @Override
    public Optional<Borrower> findById(String borrowerId) {
        return Optional.ofNullable(storage.get(borrowerId));
    }

    @Override
    public Optional<Borrower> findByEmail(String email) {
        return storage.values().stream()
            .filter(b -> b.getEmail().equals(email))
            .findFirst();
    }
}