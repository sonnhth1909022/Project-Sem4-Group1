package com.projectsem4.backend.service;

import com.projectsem4.backend.entity.AccountType;
import com.projectsem4.backend.entity.EAccount;
import com.projectsem4.backend.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Override
    public List<AccountType> getAllAccountTypes() {
        List<AccountType> lists = accountRepo.findAll();
        List<AccountType> listResponse = new ArrayList<>();

        for (int i = 0 ; i < lists.size(); i++)
        {
            AccountType account = lists.get(i);
            if(!account.getName().equals(EAccount.ACCOUNT_ADMIN))
            {
                listResponse.add(account);
            }
        }
        return listResponse;
    }

    @Override
    public Optional<AccountType> findAccountTypeByName(EAccount name) {
        return accountRepo.findByName(name);
    }
}
