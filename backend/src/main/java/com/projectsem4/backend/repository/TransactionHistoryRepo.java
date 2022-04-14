package com.projectsem4.backend.repository;

import com.projectsem4.backend.entity.TransactionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TransactionHistoryRepo extends JpaRepository<TransactionHistory, Integer> {
    @Query(value = "select * from transaction_history where customer_id = :customerId order by created_at desc limit 1", nativeQuery = true)
    Optional<TransactionHistory> findFirstByOrderByCreatedAtDescAndCustomerId(int customerId);
    List<TransactionHistory> findAllByCustomerId(int customerId);

    Page<TransactionHistory> findAllByOrderByCreatedAtDesc(Pageable pageable);

    @Query( value = "select * from transaction_history  where customer_id = :id " , nativeQuery = true)
    Page<TransactionHistory> getAllTransaction(int id , Pageable pageable);
}
