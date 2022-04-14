package com.projectsem4.backend.dto.movie;

import com.projectsem4.backend.dto.cast.CastDtoRes;
import com.projectsem4.backend.dto.category.CategoryDtoRes;
import com.projectsem4.backend.dto.director.DirectorDtoRes;
import com.projectsem4.backend.entity.Cast;
import com.projectsem4.backend.entity.Category;
import com.projectsem4.backend.entity.Director;
import com.projectsem4.backend.entity.MovieType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDtoRes {
    private int id;
    private String name;
    private String thumbnail;
    private String description;
    private int duration;
    private int view;
    private MovieType movieType;
    private String url;
    private Set<CategoryDtoRes> categories;
    private Set<DirectorDtoRes> directors;
    private Set<CastDtoRes> casts;

    private String favorite;
}
