package com.projectsem4.backend.repository;

import com.projectsem4.backend.entity.Cast;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CastRepository extends JpaRepository<Cast, Integer> {
    @Query("select c from Cast c where c.deleted = false")
    List<Cast> findAllByDeletedIsFalse();
    Cast findByName(String cast);
    Page<Cast> findByNameContaining(String name , Pageable pageable);
}
