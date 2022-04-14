package com.projectsem4.backend.service;

import com.projectsem4.backend.entity.Category;
import com.projectsem4.backend.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {
    boolean checkExistNameUpdate(String name, Category category);

    List<Category> getAllCategories();
    List<Category> getAllCategoriesByDeleteState(boolean isDeleted);

    Category saveCategory(Category category , MultipartFile avt);
    Category getCategoryById(int id);
    Category UpdateCategory(int id , Category category , MultipartFile avt);
    void deleteCategoryById(int id);
    int categoryn();
    //pagination
    Page<Category> getCategoriesByDeleteState(boolean isDeleted , Pageable pageable);
    Page<Category> getCategoriesByName(String name , Pageable pageable);
}
