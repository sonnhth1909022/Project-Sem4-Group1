package com.projectsem4.backend.service;

import com.projectsem4.backend.entity.MovieType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieTypeService {
    List<MovieType> getAllMovieTypes();
}
