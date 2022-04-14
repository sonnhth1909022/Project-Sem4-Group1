package com.projectsem4.backend.service;

import com.projectsem4.backend.entity.TransactionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TransactionHistoryService {
    TransactionHistory saveTransactionHistory(TransactionHistory transactionHistory);
    TransactionHistory getTransactionById(int id);
    List<TransactionHistory> getListTransactionHistoryByCustomerId(int customerId);
    List<TransactionHistory> getListTransaction(boolean isDeleted);
    Page<TransactionHistory> getListTransactionHistoryPage(boolean isDeleted , Pageable pageable);
    Optional<TransactionHistory> findLatestSubscriptionByCreatedDate(int customerId);
    int totalSum();
    int totalTransactionNotDeleted();
    void deleteTransactionById(int id);
    Page<TransactionHistory> getTransactionByUsername(String name , Pageable pageable);
}
