package com.projectsem4.backend.service;

import com.projectsem4.backend.entity.AccountType;
import com.projectsem4.backend.entity.EAccount;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<AccountType> getAllAccountTypes();
    Optional<AccountType> findAccountTypeByName(EAccount name);
}
