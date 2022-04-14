package com.projectsem4.backend.service;

import com.projectsem4.backend.entity.MovieType;
import com.projectsem4.backend.repository.MovieTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieTypeServiceImpl implements MovieTypeService {
    @Autowired
    private MovieTypeRepo movieTypeRepo;
    @Override
    public List<MovieType> getAllMovieTypes() {
        return movieTypeRepo.findAll();
    }
}
