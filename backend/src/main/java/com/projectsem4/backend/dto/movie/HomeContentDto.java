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
public class HomeContentDto {
    List<MovieDtoRes> listPremium;
    List<MovieDtoRes> listTrending;
    List<MovieDtoRes> listHot;
    List<MovieDtoRes> listAction;
    List<MovieDtoRes> listRomance;
    List<MovieDtoRes> listTelevision;
}
