package com.projectsem4.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_movie_favorite")
public class CustomerMovieFavorite extends BaseEntity{
    @EmbeddedId
    private CustomerMovieKey id;

    @Column(name = "customer_id", insertable = false, updatable = false)
    private int customerId;

    @Column(name = "movie_id", insertable = false, updatable = false)
    private int movieId;

    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(name = "customer_id")
    private User user;

    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    private Movie movie;


    private String favorite;


}
