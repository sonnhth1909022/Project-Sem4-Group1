package com.projectsem4.backend.service;

import com.projectsem4.backend.entity.Cast;
import com.projectsem4.backend.entity.Category;
import com.projectsem4.backend.entity.Director;
import com.projectsem4.backend.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    Integer getMoviesCount();

    List<Movie> getAllMovies();
    List<Movie> getAllMoviesByDeleteState(boolean isDeleted);
    List<Movie> getAllMoviesByDeleteStatePremium(boolean isDeleted);
    Page<Movie> getMoviesByDeleteState(boolean isDeleted , Pageable pageable);

    Movie saveMovieForStateChange(Movie movie);

    Movie getMovieById(int id);
    void deleteMovieById(int id);

    List<Movie> getAllMoviesByTrending();
    List<Movie> getAllMoviesByHot();
    List<Movie> getAllMoviesByAction();
    List<Movie> getAllMoviesByRomance();
    List<Movie> getAllMoviesByTelevision();
    List<Movie> getMoviesPremium();

    List<Movie> getMovieByCategory(Category category);
    List<Movie> getMovieByCategoryPremium(Category category);
    List<Movie> getMovieByCast(Cast cast);
    List<Movie> getMovieByDirector(Director director);
    List<Movie> getMovieByName(String name);

    Page<Movie> getMoviesByName(String name , Pageable pageable);
    Page<Movie> getMoviesByCast(Cast cast , Pageable pageable);
    Page<Movie> getMoviesByCategory(Category category , Pageable pageable);
    Page<Movie> getMoviesByDirector(Director director , Pageable pageable);


    Optional<Movie> findMovieById(int id);
}
