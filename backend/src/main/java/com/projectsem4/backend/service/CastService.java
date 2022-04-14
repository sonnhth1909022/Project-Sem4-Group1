package com.projectsem4.backend.service;

import com.projectsem4.backend.entity.Cast;
import com.projectsem4.backend.entity.Director;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CastService {
    List<Cast> getAllCasts();
    List<Cast> getAllCastsByDeleteState(boolean isDeleted);
    Page<Cast> getCastsByDeleteState(boolean isDeleted , Pageable pageable);

    Cast saveCast(Cast cast, MultipartFile image);
    Cast saveCastForStateChange(Cast cast);
    Cast updateCast(int id , Cast cast , MultipartFile image);
    Cast getCastById(int id);
    void deleteCastById(int id);
    int castn();

    Page<Cast> getCastsByName(String name , Pageable pageable);
}
