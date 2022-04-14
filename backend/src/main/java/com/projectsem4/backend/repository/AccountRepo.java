package com.projectsem4.backend.repository;

import com.projectsem4.backend.entity.AccountType;
import com.projectsem4.backend.entity.EAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<AccountType, Integer> {
    Optional<AccountType> findByName(EAccount name);
}