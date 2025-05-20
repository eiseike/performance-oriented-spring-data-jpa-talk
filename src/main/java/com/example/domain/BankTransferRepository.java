package com.example.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface BankTransferRepository extends ListCrudRepository<BankTransfer, String> {
    default BankTransfer findByIdOrThrow(String id) {
        return findById(id).orElseThrow();
    }

    @Query("SELECT bt FROM BankTransfer bt join fetch bt.receiver join fetch bt.sender WHERE bt.sender.id = :senderId")
    List<BankTransfer> findBySenderId(String senderId);
}
