package com.projectsem4.backend.service;

import com.projectsem4.backend.entity.CustomerMovieFavorite;

import java.util.List;
import java.util.Optional;

public interface CustomerMovieFavService {
    CustomerMovieFavorite addFavorite(CustomerMovieFavorite customerMovieFavorite);
    List<CustomerMovieFavorite> getAllFavorites(int customerId);
    void deleteFavoriteByCustomerIdAndMovieId(int customerId, int movieId);
    Optional<CustomerMovieFavorite> findFavoriteByCustomerIdAndMovieId(int customerId, int movieId);
}
