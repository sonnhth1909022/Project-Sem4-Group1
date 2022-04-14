package com.projectsem4.backend.repository;

import com.projectsem4.backend.entity.ERole;
import com.projectsem4.backend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
