package com.projectsem4.backend.service;

import com.projectsem4.backend.entity.Director;
import com.projectsem4.backend.entity.Movie;
import com.projectsem4.backend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> listUser();
    User getUserById(int id);
    List<User> getUserByUsername(String username);
//    List<User> getAllUsersByDeleteState(boolean isDeleted);
    Integer getUserCount();
    Integer getUserVipCount();
    List<User> getUserVip();
    List<User> getUserNormal();

    Optional<User> findUserById(int id);
    User saveUser(User user);
}
