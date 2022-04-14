package com.projectsem4.backend.dto.cast;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CastDtoRes {
    private int id;
    private String name;
    private String avt;
    private String description;
}
