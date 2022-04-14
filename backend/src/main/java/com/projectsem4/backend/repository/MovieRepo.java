package com.projectsem4.backend.repository;

import com.projectsem4.backend.entity.Cast;
import com.projectsem4.backend.entity.Category;
import com.projectsem4.backend.entity.Director;
import com.projectsem4.backend.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepo extends JpaRepository<Movie, Integer> {
    @Query("select m from Movie m where m.deleted = false")
    List<Movie> findAllByDeletedIsFalse();

    @Query("select m from Movie m where m.view >= 0 and m.movieType.name = 'MOVIE_NORMAL' ")
    List<Movie> getAllMoviesHot();

    @Query("select m from Movie m where m.view >= 0 and m.movieType.name = 'MOVIE_NORMAL' ")
    List<Movie> getAllMoviesTrending();

    List<Movie> findByCategories(Category category);
    List<Movie> findByDirectors(Director director);
    List<Movie> findByCasts(Cast cast);
    List<Movie> findByNameContaining(String name);

    Page<Movie> findByNameContaining(String name , Pageable pageable);
    Page<Movie> findByCastsOrderById(Cast cast , Pageable pageable);
    Page<Movie> findByCategoriesOrderById(Category category , Pageable pageable);
    Page<Movie> findByDirectorsOrderById(Director director , Pageable pageable);
}
