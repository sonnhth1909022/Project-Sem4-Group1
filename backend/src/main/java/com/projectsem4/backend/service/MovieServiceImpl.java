package com.projectsem4.backend.service;

import com.projectsem4.backend.dto.movie.MovieDto;
import com.projectsem4.backend.entity.*;
import com.projectsem4.backend.repository.CastRepository;
import com.projectsem4.backend.repository.CategoryRepo;
import com.projectsem4.backend.repository.DirectorRepo;
import com.projectsem4.backend.repository.MovieRepo;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService{
    Path imagePath = Paths.get("images");

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private CastRepository castRepo;

    @Autowired
    private DirectorRepo directorRepo;

    @Override
    public Integer getMoviesCount() {
        List<Movie> list = movieRepo.findAll();
        return list.size();
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepo.findAllByDeletedIsFalse();
    }

    @Override
    public List<Movie> getAllMoviesByDeleteState(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedItemFilter");
        filter.setParameter("isDeleted", isDeleted);
        List<Movie> movies = movieRepo.findAll();
        session.disableFilter("deletedItemFilter");
        return movies;
    }

    @Override
    public List<Movie> getAllMoviesByDeleteStatePremium(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedItemFilter");
        filter.setParameter("isDeleted", isDeleted);
        List<Movie> movies = movieRepo.findAll();
        List<Movie> listRes = new ArrayList<>();
        for(int i = 0 ; i < movies.size() ; i++)
        {
            Movie movie = movies.get(i);
            if(movie.getMovieType().getName().equals(EMovie.MOVIE_PREMIUM))
            {
                listRes.add(movie);
            }
        }
        session.disableFilter("deletedItemFilter");
        return listRes;
    }

    @Override
    public Page<Movie> getMoviesByDeleteState(boolean isDeleted, Pageable pageable) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedItemFilter");
        filter.setParameter("isDeleted", isDeleted);
        Page<Movie> movies = movieRepo.findAll(pageable);
        session.disableFilter("deletedItemFilter");
        return movies;
    }

    @Override
    public Movie saveMovieForStateChange(Movie movie) {
        return movieRepo.save(movie);
    }


    @Override
    public Movie getMovieById(int id) {
        return movieRepo.findById(id).get();
    }

    @Override
    public void deleteMovieById(int id) {
        Movie movie = movieRepo.findById(id).get();
        movie.setDeleted(true);
        movieRepo.save(movie);
    }

    @Override
    public List<Movie> getAllMoviesByTrending() {
        return movieRepo.getAllMoviesTrending();
    }

    @Override
    public List<Movie> getAllMoviesByHot() {
        return movieRepo.getAllMoviesHot();
    }

    @Override
    public List<Movie> getAllMoviesByAction() {
        Category category = categoryRepo.findByName("Action");
        List<Movie> movieAction = movieRepo.findByCategories(category);
        List<Movie> movieActionres = new ArrayList<>();

        for (int i = 0 ; i < movieAction.size() ; i++)
        {
            Movie movie = movieAction.get(i);
            if(movie.getMovieType().getName().equals(EMovie.MOVIE_NORMAL))
            {
                movieActionres.add(movie);
            }
        }
        return movieActionres;
    }

    @Override
    public List<Movie> getAllMoviesByRomance() {
        Category category = categoryRepo.findByName("Romance");
        List<Movie> movieRomance = movieRepo.findByCategories(category);
        List<Movie> movieRomanceres = new ArrayList<>();

        for (int i = 0 ; i < movieRomance.size() ; i++)
        {
            Movie movie = movieRomance.get(i);
            if(movie.getMovieType().getName().equals(EMovie.MOVIE_NORMAL))
            {
                movieRomanceres.add(movie);
            }
        }
        return movieRomanceres;
    }

    @Override
    public List<Movie> getAllMoviesByTelevision() {
        Category category = categoryRepo.findByName("Television");
        List<Movie> movieTelevision = movieRepo.findByCategories(category);
        List<Movie> movieTelevisionres = new ArrayList<>();

        for (int i = 0 ; i < movieTelevision.size() ; i++)
        {
            Movie movie = movieTelevision.get(i);
            if(movie.getMovieType().getName().equals(EMovie.MOVIE_NORMAL))
            {
                movieTelevisionres.add(movie);
            }
        }
        return movieTelevisionres;
    }

    @Override
    public List<Movie> getMoviesPremium() {
        List<Movie> movieList = movieRepo.findAll();
        List<Movie> list = new ArrayList<>();
        for (int i = 0; i < movieList.size(); i++) {
            Movie movie = movieList.get(i);
            if (movie.getMovieType().getName().equals(EMovie.MOVIE_PREMIUM)) {
                list.add(movie);
            }
        }
        return list;
    }

    @Override
    public List<Movie> getMovieByCategory(Category category) {
        List<Movie> list = movieRepo.findByCategories(category);
        return list;
    }

    @Override
    public List<Movie> getMovieByCategoryPremium(Category category) {
        List<Movie> list = movieRepo.findByCategories(category);
        List<Movie> listRes = new ArrayList<>();
        for(int i = 0 ; i < list.size() ; i++)
        {
            Movie movie = list.get(i);
            if(movie.getMovieType().getName().equals(EMovie.MOVIE_PREMIUM))
            {
                listRes.add(movie);
            }
        }
        return listRes;
    }

    @Override
    public List<Movie> getMovieByCast(Cast cast) {
        return movieRepo.findByCasts(cast);
    }

    @Override
    public List<Movie> getMovieByDirector(Director director) {
        return movieRepo.findByDirectors(director);
    }

    @Override
    public List<Movie> getMovieByName(String name) {
        return movieRepo.findByNameContaining(name);
    }

    @Override
    public Page<Movie> getMoviesByName(String name, Pageable pageable) {
        return movieRepo.findByNameContaining(name , pageable);
    }

    @Override
    public Page<Movie> getMoviesByCast(Cast cast, Pageable pageable) {
        return movieRepo.findByCastsOrderById(cast , pageable);
    }

    @Override
    public Page<Movie> getMoviesByCategory(Category category, Pageable pageable) {
        return movieRepo.findByCategoriesOrderById(category , pageable);
    }

    @Override
    public Page<Movie> getMoviesByDirector(Director director, Pageable pageable) {
        return movieRepo.findByDirectorsOrderById(director , pageable);
    }

    @Override
    public Optional<Movie> findMovieById(int id) {
        return movieRepo.findById(id);
    }

}
