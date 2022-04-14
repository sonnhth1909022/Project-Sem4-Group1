package com.projectsem4.backend.service;

import com.projectsem4.backend.entity.*;
import com.projectsem4.backend.repository.MovieRepo;
import com.projectsem4.backend.repository.UserRepository;
import com.projectsem4.backend.ulti.RESTResponse;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> listUser() {
        List<User> list =  userRepository.findAll();
        List<User> listRes = new ArrayList<>();
        for (int i = 0 ; i < list.size() ; i++)
        {
            User user = list.get(i);
            if (!user.getAccountType().getName().equals(EAccount.ACCOUNT_ADMIN))
            {
                listRes.add(user);
            }
        }
        return listRes;
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getUserByUsername(String username) {
        List<User> list = userRepository.findByUsernameContaining(username);
        List<User> listRes = new ArrayList<>();
        for (int i = 0 ; i < list.size() ; i++)
        {
            User user = list.get(i);
            if(!user.getAccountType().getName().equals(EAccount.ACCOUNT_ADMIN))
            {
                listRes.add(user);
            }
        }
        return listRes;
    }

    @Override
    public Integer getUserCount() {
        List<User> list =  userRepository.findAll();
        List<User> listRes = new ArrayList<>();
        for (int i = 0 ; i < list.size() ; i++)
        {
            User user = list.get(i);
            if(!user.getAccountType().getName().equals(EAccount.ACCOUNT_ADMIN))
            {
                listRes.add(user);
            }
        }
        return listRes.size();
    }

    @Override
    public Integer getUserVipCount() {
        List<User> list =  userRepository.findAll();
        List<User> listRes = new ArrayList<>();
        for (int i = 0 ; i < list.size() ; i++)
        {
            User user = list.get(i);
            if(user.getAccountType().getName().equals(EAccount.ACCOUNT_VIP))
            {
                listRes.add(user);
            }
        }
        return listRes.size();
    }

    @Override
    public List<User> getUserVip() {
        List<User> list =  userRepository.findAll();
        List<User> listRes = new ArrayList<>();
        for (int i = 0 ; i < list.size() ; i++)
        {
            User user = list.get(i);
            if (user.getAccountType().getName().equals(EAccount.ACCOUNT_VIP))
            {
                listRes.add(user);
            }
        }
        return listRes;
    }

    @Override
    public List<User> getUserNormal() {
        List<User> list =  userRepository.findAll();
        List<User> listRes = new ArrayList<>();
        for (int i = 0 ; i < list.size() ; i++)
        {
            User user = list.get(i);
            if (user.getAccountType().getName().equals(EAccount.ACCOUNT_NORMAL))
            {
                listRes.add(user);
            }
        }
        return listRes;
    }

    @Override
    public Optional<User> findUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

//    @Override
//    public List<User> getAllUsersByDeleteState(boolean isDeleted) {
//        Session session = entityManager.unwrap(Session.class);
//        Filter filter = session.enableFilter("deletedItemFilter");
//        filter.setParameter("isDeleted", isDeleted);
//        List<User> users = userRepository.findAllCustomer();
//        session.disableFilter("deletedItemFilter");
//        return users;
//    }

}
