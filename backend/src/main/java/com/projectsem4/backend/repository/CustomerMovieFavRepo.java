package com.projectsem4.backend.repository;

import com.projectsem4.backend.entity.CustomerMovieFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerMovieFavRepo extends JpaRepository<CustomerMovieFavorite, Integer> {
    List<CustomerMovieFavorite> findAllByCustomerId(int customerId);
    void deleteByCustomerIdAndMovieId(int customerId, int movieId);
    Optional<CustomerMovieFavorite> findByCustomerIdAndMovieId(int customerId, int movieId);
}
