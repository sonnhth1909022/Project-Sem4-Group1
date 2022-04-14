package com.projectsem4.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE categories SET deleted = true WHERE id=?")
@Table(name = "categories")
public class Category extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(columnDefinition = "text")
    private String description;

    @Column(columnDefinition = "text")
    private String avt;

    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private Set<Movie> movies;
}
