package com.projectsem4.backend.service;

import com.projectsem4.backend.entity.Cast;
import com.projectsem4.backend.entity.Director;
import com.projectsem4.backend.repository.CastRepository;
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
public class CastServiceImpl implements CastService{
    Path imagePath = Paths.get("images");

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CastRepository castRepository;

    @Override
    public List<Cast> getAllCasts() {
        return castRepository.findAllByDeletedIsFalse();
    }

    @Override
    public List<Cast> getAllCastsByDeleteState(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedItemFilter");
        filter.setParameter("isDeleted", isDeleted);
        List<Cast> casts = castRepository.findAll();
        session.disableFilter("deletedItemFilter");
        return casts;
    }

    @Override
    public Page<Cast> getCastsByDeleteState(boolean isDeleted, Pageable pageable) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedItemFilter");
        filter.setParameter("isDeleted", isDeleted);
        Page<Cast> casts = castRepository.findAll(pageable);
        session.disableFilter("deletedItemFilter");
        return casts;
    }

    @Override
    public Cast saveCast(Cast cast, MultipartFile image) {
        Cast cast1 = new Cast();
        cast1.setName(cast.getName());
        cast1.setDescription(cast.getDescription());
        cast1.setAvt(imagePath.resolve(image.getOriginalFilename()).toString());
        return castRepository.save(cast1);
    }

    @Override
    public Cast saveCastForStateChange(Cast cast) {
        return castRepository.save(cast);
    }

    @Override
    public Cast updateCast(int id, Cast cast, MultipartFile image) {
        Cast cast1 = castRepository.getById(id);
        cast1.setName(cast.getName());
        cast1.setDescription(cast.getDescription());
        cast1.setAvt(imagePath.resolve(image.getOriginalFilename()).toString());
        return castRepository.save(cast1);
    }

    @Override
    public Cast getCastById(int id) {
        return castRepository.getById(id);
    }

    @Override
    public void deleteCastById(int id) {
        this.castRepository.deleteById(id);
    }

    @Override
    public int castn() {
        List<Cast> casts = castRepository.findAll();
        return casts.size();
    }

    @Override
    public Page<Cast> getCastsByName(String name, Pageable pageable) {
        return castRepository.findByNameContaining(name , pageable);
    }


}
