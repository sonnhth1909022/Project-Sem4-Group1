package com.projectsem4.backend.service;

import com.projectsem4.backend.entity.Director;
import com.projectsem4.backend.repository.DirectorRepo;
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
import java.util.List;

@Service
public class DirectorServiceImpl implements DirectorService{
    Path imagePath = Paths.get("images");

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DirectorRepo directorRepo;

    @Override
    public List<Director> getAllDirectors() {
        return directorRepo.findAllByDeletedIsFalse();
    }

    @Override
    public List<Director> getAllDirectorsByDeleteState(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedItemFilter");
        filter.setParameter("isDeleted", isDeleted);
        List<Director> directors = directorRepo.findAll();
        session.disableFilter("deletedItemFilter");
        return directors;
    }

    @Override
    public Page<Director> getDirectorsByDeleteState(boolean isDeleted , Pageable pageable) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedItemFilter");
        filter.setParameter("isDeleted", isDeleted);
        Page<Director> directors = directorRepo.findAll(pageable);
        session.disableFilter("deletedItemFilter");
        return directors;
    }

    @Override
    public Director saveDirector(Director director , MultipartFile avatar) {
        Director director1 = new Director();
        director1.setName(director.getName());
        director1.setDescription(director.getDescription());
        director1.setAvt(imagePath.resolve(avatar.getOriginalFilename()).toString());
        return directorRepo.save(director1);
    }

    @Override
    public Director saveDirectorForStateChange(Director director) {
        return directorRepo.save(director);
    }

    @Override
    public Director updateDirector(int id, Director director, MultipartFile image) {
        Director director1 = directorRepo.getById(id);
        director1.setName(director.getName());
        director1.setDescription(director.getDescription());
        director1.setAvt(imagePath.resolve(image.getOriginalFilename()).toString());
        return directorRepo.save(director1);
    }

    @Override
    public Director getDirectorById(int id) {
        return directorRepo.getById(id);
    }

    @Override
    public void deleteDirectorById(int id) {
        this.directorRepo.deleteById(id);
    }

    @Override
    public int directorn() {
        List<Director> directors = directorRepo.findAll();
        return directors.size();
    }

    @Override
    public Page<Director> getDirectorsByName(String name, Pageable pageable) {
        return directorRepo.findByNameContaining(name , pageable);
    }
}
