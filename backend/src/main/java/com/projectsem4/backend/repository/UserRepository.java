package com.projectsem4.backend.repository;

import com.projectsem4.backend.entity.Movie;
import com.projectsem4.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Integer> {
    Optional<User> findByUsername(String username);

    User findByUsernameOrderByCreatedAtDesc(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    List<User> findByUsernameContaining(String username);

    @Query("select u from User u where u.accountType.name = 'ACCOUNT_CUSTOMER' or u.accountType.name = 'ACCOUNT_MONTHLY' or u.accountType.name = 'ACCOUNT_YEARLY'")
    List<User> findAllCustomer();
}
