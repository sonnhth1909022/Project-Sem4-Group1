package com.projectsem4.backend.service;

import com.projectsem4.backend.entity.CustomerMovieFavorite;
import com.projectsem4.backend.repository.CustomerMovieFavRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerMovieFavServiceImpl implements CustomerMovieFavService{

    @Autowired
    private CustomerMovieFavRepo customerMovieFavRepo;

    @Override
    public CustomerMovieFavorite addFavorite(CustomerMovieFavorite customerMovieFavorite) {
        return customerMovieFavRepo.save(customerMovieFavorite);
    }

    @Override
    public List<CustomerMovieFavorite> getAllFavorites(int customerId) {
        return customerMovieFavRepo.findAllByCustomerId(customerId);
    }

    @Override
    public void deleteFavoriteByCustomerIdAndMovieId(int customerId, int movieId) {
        this.customerMovieFavRepo.deleteByCustomerIdAndMovieId(customerId, movieId);
    }

    @Override
    public Optional<CustomerMovieFavorite> findFavoriteByCustomerIdAndMovieId(int customerId, int movieId) {
        return customerMovieFavRepo.findByCustomerIdAndMovieId(customerId, movieId);
    }
}
