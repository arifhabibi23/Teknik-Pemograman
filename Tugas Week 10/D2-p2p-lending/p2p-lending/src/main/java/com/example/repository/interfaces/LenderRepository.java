package com.example.repository.interfaces;

import com.example.domain.entity.Lender;
import java.util.Optional;

public interface LenderRepository {
    void save(Lender lender);
    Optional<Lender> findById(String lenderId);
}