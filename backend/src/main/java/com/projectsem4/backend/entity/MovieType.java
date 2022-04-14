package com.projectsem4.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "movietypes")
public class MovieType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EMovie name;

    @OneToMany(mappedBy = "movieType")
    @JsonIgnore
    private Set<Movie> movies;

    public EMovie getName() {
        return name;
    }
}
