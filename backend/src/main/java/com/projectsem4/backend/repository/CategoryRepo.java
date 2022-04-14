package com.projectsem4.backend.repository;

import com.projectsem4.backend.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Integer>{
    boolean existsByName(String name);
    Category findByName(String name);
    @Query("select c from Category c where c.deleted = false")
    List<Category> findAllByDeletedIsFalse();
    Page<Category> findByNameContaining(String name , Pageable pageable);
}
