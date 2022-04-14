package com.projectsem4.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE casts SET deleted = true WHERE id=?")
@Table(name = "casts")
public class Cast extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(columnDefinition = "text")
    private String avt;

    @Column(columnDefinition = "text")
    private String description;

    @ManyToMany(mappedBy = "casts")
    @JsonIgnore
    private Set<Movie> movies;
}
