package com.example.repository.interfaces;

import com.example.domain.entity.Borrower;
import java.util.Optional;

public interface BorrowerRepository {
    void save(Borrower borrower);
    Optional<Borrower> findById(String borrowerId);
    Optional<Borrower> findByEmail(String email);
}