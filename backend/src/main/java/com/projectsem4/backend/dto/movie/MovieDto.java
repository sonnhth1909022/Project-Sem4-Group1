package com.projectsem4.backend.dto.movie;

import com.projectsem4.backend.entity.Cast;
import com.projectsem4.backend.entity.Category;
import com.projectsem4.backend.entity.Director;
import com.projectsem4.backend.entity.MovieType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    @NotBlank(message = "Name must not be blank!")
    private String name;
    private String description;
    private int duration;
    private int view;
    private String movieType;
    private String url;
    private String thumbnail;
    private Set<Category> categories;
    private Set<Director> directors;
    private Set<Cast> casts;
}
