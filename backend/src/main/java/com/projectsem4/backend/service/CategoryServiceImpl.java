package com.projectsem4.backend.service;

import com.projectsem4.backend.entity.Category;
import com.projectsem4.backend.repository.CategoryRepo;
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
public class CategoryServiceImpl implements CategoryService{
    Path imagePath = Paths.get("images");

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private EntityManager entityManager;

    @Override
    public boolean checkExistNameUpdate(String name, Category category) {
        try{
            boolean bl = categoryRepo.existsByName(name);
            if (bl){
                if (!category.getName().equals(name)){
                    return true;
                }
            }
        }catch(Exception e){e.printStackTrace();}
        return false;
    }


    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAllByDeletedIsFalse();
    }

    @Override
    public List<Category> getAllCategoriesByDeleteState(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedItemFilter");
        filter.setParameter("isDeleted", isDeleted);
        List<Category> categories = categoryRepo.findAll();
        session.disableFilter("deletedItemFilter");
        return categories;
    }

    @Override
    public Page<Category> getCategoriesByDeleteState(boolean isDeleted, Pageable pageable) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedItemFilter");
        filter.setParameter("isDeleted", isDeleted);
        Page<Category> categories = categoryRepo.findAll(pageable);
        session.disableFilter("deletedItemFilter");
        return categories;
    }

    @Override
    public Page<Category> getCategoriesByName(String name, Pageable pageable) {
        return categoryRepo.findByNameContaining(name , pageable);
    }


    @Override
    public Category saveCategory(Category category , MultipartFile avt) {
        Category category1 = new Category();
        category1.setName(category.getName());
        category1.setDescription(category.getDescription());
        if(avt.getOriginalFilename().equalsIgnoreCase("action.png"))
        {
            category1.setAvt("https://i.imgur.com/3GQBrv0.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("romance.png"))
        {
            category1.setAvt("https://i.imgur.com/XJ7C1xo.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("television.png"))
        {
            category1.setAvt("https://i.imgur.com/7ozv2uF.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("science.png"))
        {
            category1.setAvt("https://i.imgur.com/lAJBwZi.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("adventure.png"))
        {
            category1.setAvt("https://i.imgur.com/aEqxgxh.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("drama.png"))
        {
            category1.setAvt("https://i.imgur.com/2Pjsn1v.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("mystery.png"))
        {
            category1.setAvt("https://i.imgur.com/SzFB4nb.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("thriller.png"))
        {
            category1.setAvt("https://i.imgur.com/TfT3qMK.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("crime.png"))
        {
            category1.setAvt("https://i.imgur.com/aFLa4qh.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("family.png"))
        {
            category1.setAvt("https://i.imgur.com/RKzxPOD.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("animation.png"))
        {
            category1.setAvt("https://i.imgur.com/LLY3zzp.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("horror.png"))
        {
            category1.setAvt("https://i.imgur.com/ZHSWUx1.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("biography.png"))
        {
            category1.setAvt("https://i.imgur.com/8Zdjqg7.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("history.png"))
        {
            category1.setAvt("https://i.imgur.com/sbS9w3Z.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("comedy.png"))
        {
            category1.setAvt("https://i.imgur.com/XAVsFnt.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("western.png"))
        {
            category1.setAvt("https://i.imgur.com/5emmS68.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("war.png"))
        {
            category1.setAvt("https://i.imgur.com/CSej0x6.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("sport.png"))
        {
            category1.setAvt("https://i.imgur.com/PYWSGHm.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("geography.png"))
        {
            category1.setAvt("https://i.imgur.com/2XseZfp.jpg");
        }

        return categoryRepo.save(category1);
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryRepo.getById(id);
    }

    @Override
    public Category UpdateCategory(int id, Category category, MultipartFile avt) {
        Category category1 = categoryRepo.getById(id);
        category1.setName(category.getName());
        category1.setDescription(category.getDescription());
        if(avt.getOriginalFilename().equalsIgnoreCase("action.png"))
        {
            category1.setAvt("https://i.imgur.com/3GQBrv0.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("romance.png"))
        {
            category1.setAvt("https://i.imgur.com/XJ7C1xo.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("television.png"))
        {
            category1.setAvt("https://i.imgur.com/7ozv2uF.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("science.png"))
        {
            category1.setAvt("https://i.imgur.com/lAJBwZi.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("adventure.png"))
        {
            category1.setAvt("https://i.imgur.com/aEqxgxh.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("drama.png"))
        {
            category1.setAvt("https://i.imgur.com/2Pjsn1v.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("mystery.png"))
        {
            category1.setAvt("https://i.imgur.com/SzFB4nb.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("thriller.png"))
        {
            category1.setAvt("https://i.imgur.com/TfT3qMK.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("crime.png"))
        {
            category1.setAvt("https://i.imgur.com/aFLa4qh.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("family.png"))
        {
            category1.setAvt("https://i.imgur.com/RKzxPOD.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("animation.png"))
        {
            category1.setAvt("https://i.imgur.com/LLY3zzp.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("horror.png"))
        {
            category1.setAvt("https://i.imgur.com/ZHSWUx1.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("biography.png"))
        {
            category1.setAvt("https://i.imgur.com/8Zdjqg7.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("history.png"))
        {
            category1.setAvt("https://i.imgur.com/sbS9w3Z.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("comedy.png"))
        {
            category1.setAvt("https://i.imgur.com/XAVsFnt.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("western.png"))
        {
            category1.setAvt("https://i.imgur.com/5emmS68.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("war.png"))
        {
            category1.setAvt("https://i.imgur.com/CSej0x6.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("sport.png"))
        {
            category1.setAvt("https://i.imgur.com/PYWSGHm.png");
        }
        if(avt.getOriginalFilename().equalsIgnoreCase("geography.png"))
        {
            category1.setAvt("https://i.imgur.com/2XseZfp.jpg");
        }
        return categoryRepo.save(category1);
    }

    @Override
    public void deleteCategoryById(int id) {
        this.categoryRepo.deleteById(id);
    }

    @Override
    public int categoryn() {
        List<Category> categories = categoryRepo.findAll();
        return categories.size();
    }

}
