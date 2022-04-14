package com.projectsem4.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@SQLDelete(sql = "UPDATE movies SET deleted = true WHERE id=?")
@Table(name = "movies")
public class Movie extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(columnDefinition = "text")
    private String thumbnail;
    @Column(columnDefinition = "text")
    private String description;

    private int duration;
    private int view;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movietype_id" , nullable = false)
    private MovieType movieType;

    @Column(columnDefinition = "text")
    private String url;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movie_categories" ,
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<Category>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movie_casts" ,
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "cast_id"))
    private Set<Cast> casts = new HashSet<Cast>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movie_directors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id"))
    private Set<Director> directors = new HashSet<Director>();


//    public void addCategory(Category category)
//    {
//        this.categories.add(category);
//        category.getMovies().add(this);
//    }
//
//    public void removeCategory(Category category)
//    {
//        this.getCategories().remove(category);
//        category.getMovies().remove(this);
//    }
//
//    public void removeCategory()
//    {
//        for (Category category : new HashSet<>(categories))
//        {
//            removeCategory(category);
//        }
//    }
//
//    public void addCast(Cast cast)
//    {
//        this.casts.add(cast);
//        cast.getMovies().add(this);
//    }
//
//    public void removeCast(Cast cast)
//    {
//        this.getCasts().remove(cast);
//        cast.getMovies().remove(this);
//    }
//    public void removeCast()
//    {
//        for (Cast cast : new HashSet<>(casts))
//        {
//            removeCast(cast);
//        }
//    }
//
//    public void addDirector(Director director)
//    {
//        this.directors.add(director);
//        director.getMovies().add(this);
//    }
//
//    public void removeDirector(Director director)
//    {
//        this.getDirectors().remove(director);
//        director.getMovies().remove(this);
//    }
//
//    public void removeDirector()
//    {
//        for (Director director : new HashSet<>(directors))
//        {
//            removeDirector(director);
//        }
//    }


}
