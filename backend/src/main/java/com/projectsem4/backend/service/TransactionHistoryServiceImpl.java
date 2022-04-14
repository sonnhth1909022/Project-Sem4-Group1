package com.projectsem4.backend.service;

import com.projectsem4.backend.entity.TransactionHistory;
import com.projectsem4.backend.entity.User;
import com.projectsem4.backend.repository.TransactionHistoryRepo;
import com.projectsem4.backend.repository.UserRepository;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService{

    @Autowired
    private TransactionHistoryRepo transactionHistoryRepo;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Override
    public TransactionHistory saveTransactionHistory(TransactionHistory transactionHistory) {
        return transactionHistoryRepo.save(transactionHistory);
    }

    @Override
    public TransactionHistory getTransactionById(int id) {
        return transactionHistoryRepo.getById(id);
    }

    @Override
    public List<TransactionHistory> getListTransactionHistoryByCustomerId(int customerId) {
        return transactionHistoryRepo.findAllByCustomerId(customerId);
    }

    @Override
    public List<TransactionHistory> getListTransaction(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedItemFilter");
        filter.setParameter("isDeleted", isDeleted);
        List<TransactionHistory> transactions =  transactionHistoryRepo.findAll();
        session.disableFilter("deletedItemFilter");
        return transactions;
    }

    @Override
    public Page<TransactionHistory> getListTransactionHistoryPage(boolean isDeleted , Pageable pageable) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedItemFilter");
        filter.setParameter("isDeleted", isDeleted);
        Page<TransactionHistory> transactions =  transactionHistoryRepo.findAllByOrderByCreatedAtDesc(pageable);
        session.disableFilter("deletedItemFilter");
        return transactions;
    }

    @Override
    public Optional<TransactionHistory> findLatestSubscriptionByCreatedDate(int customerId) {
        return transactionHistoryRepo.findFirstByOrderByCreatedAtDescAndCustomerId(customerId);
    }

    @Override
    public int totalSum() {
        List<TransactionHistory> lists = transactionHistoryRepo.findAll();
        int sum = 0;
        for(int i = 0 ; i < lists.size() ; i++)
        {
            TransactionHistory transactionHistory = lists.get(i);
            sum += transactionHistory.getAmount();
        }
        return sum;
    }

    @Override
    public int totalTransactionNotDeleted() {
        List<TransactionHistory> lists = transactionHistoryRepo.findAll();
        int sum = 0;
        for(int i = 0 ; i < lists.size() ; i++)
        {
            TransactionHistory transactionHistory = lists.get(i);
            if(transactionHistory.isDeleted() == false)
            {
                sum ++;
            }
        }
        return sum;
    }

    @Override
    public void deleteTransactionById(int id) {
        transactionHistoryRepo.deleteById(id);
    }

    @Override
    public Page<TransactionHistory> getTransactionByUsername(String name, Pageable pageable) {
        Optional<User> user = userRepository.findByUsername(name);
        if(user.isPresent()) {
            int id = user.get().getId();
            Page<TransactionHistory> pages = transactionHistoryRepo.getAllTransaction(id , pageable);
            return pages;
        }
        else
        {
            return null;
        }
    }


}
