package com.projectsem4.backend.dto.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieTypeDto {
    List<MovieDtoRes> listNormal;
    List<MovieDtoRes> listPremium;
}
