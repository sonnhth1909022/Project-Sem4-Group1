package com.movieapp.movienavigation.model;

import com.movieapp.movienavigation.adapter.MovieAdapter;
import com.movieapp.movienavigation.response.MovieDto;

import java.util.List;

public class Section {
    private String title;
    private List<MovieDto> listMovies;
    private MovieAdapter movieAdapter;

    public Section() {
    }

    public Section(String title, List<MovieDto> listMovies, MovieAdapter movieAdapter) {
        this.title = title;
        this.listMovies = listMovies;
        this.movieAdapter = movieAdapter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MovieDto> getListMovies() {
        return listMovies;
    }

    public void setListMovies(List<MovieDto> listMovies) {
        this.listMovies = listMovies;
    }

    public MovieAdapter getMovieAdapter() {
        return movieAdapter;
    }

    public void setMovieAdapter(MovieAdapter movieAdapter) {
        this.movieAdapter = movieAdapter;
    }
}
