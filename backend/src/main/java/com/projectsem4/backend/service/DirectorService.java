package com.projectsem4.backend.service;


import com.projectsem4.backend.entity.Director;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DirectorService {
    List<Director> getAllDirectors();
    List<Director> getAllDirectorsByDeleteState(boolean isDeleted);
    Page<Director> getDirectorsByDeleteState(boolean isDeleted , Pageable pageable);

    Director saveDirector(Director director , MultipartFile image);
    Director saveDirectorForStateChange(Director director);
    Director updateDirector(int id , Director director , MultipartFile image);

    Director getDirectorById(int id);
    void deleteDirectorById(int id);
    int directorn();

    Page<Director> getDirectorsByName(String name , Pageable pageable);
}
