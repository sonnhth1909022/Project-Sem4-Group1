package com.projectsem4.backend.repository;

import com.projectsem4.backend.entity.AccountType;
import com.projectsem4.backend.entity.EAccount;
import com.projectsem4.backend.entity.EMovie;
import com.projectsem4.backend.entity.MovieType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieTypeRepo extends JpaRepository<MovieType, Integer> {
    Optional<MovieType> findByName(EMovie name);
}
