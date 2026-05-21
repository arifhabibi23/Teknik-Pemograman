package com.example.repository.inmemory;

import com.example.domain.entity.Lender;
import com.example.repository.interfaces.LenderRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryLenderRepository implements LenderRepository {

    private final Map<String, Lender> storage = new HashMap<>();

    @Override
    public void save(Lender lender) {
        storage.put(lender.getLenderId(), lender);
    }

    @Override
    public Optional<Lender> findById(String lenderId) {
        return Optional.ofNullable(storage.get(lenderId));
    }
}