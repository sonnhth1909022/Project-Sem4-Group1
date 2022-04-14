package com.projectsem4.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectsem4.backend.dto.director.DirectorDto;
import com.projectsem4.backend.dto.movie.MovieDto;
import com.projectsem4.backend.dto.movie.MovieDtoRes;
import com.projectsem4.backend.dto.movie.MovieMapper;
import com.projectsem4.backend.entity.*;
import com.projectsem4.backend.repository.*;
import com.projectsem4.backend.service.MovieService;
import com.projectsem4.backend.service.MovieTypeService;
import com.projectsem4.backend.ulti.PaginationResponse;
import com.projectsem4.backend.ulti.RESTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api/v1/movie/")
public class MovieController {
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieTypeRepo movieTypeRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private CastRepository castRepo;

    @Autowired
    private DirectorRepo directorRepo;

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private MovieTypeService movieTypeService;

    @PostMapping("add")
    public ResponseEntity<?> addMovie(@Valid @RequestParam MultipartFile thumbnail , String jsonObject) throws IOException {
        MovieDto movieDto = null;
        movieDto = new ObjectMapper().readValue(jsonObject , MovieDto.class);

        Path staticPath = Paths.get("static");
        Path imagePath = Paths.get("images");
        String filename = thumbnail.getOriginalFilename();
        int index = filename.lastIndexOf('.');
        String extension = filename.substring(index + 1);
        if(extension.equalsIgnoreCase("jpg")) {
            if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
                Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
            }
            Path file = CURRENT_FOLDER.resolve(staticPath).resolve(imagePath).resolve(thumbnail.getOriginalFilename());
            try (OutputStream os = Files.newOutputStream(file)) {
                os.write(thumbnail.getBytes());
            }

            String movieType = movieDto.getMovieType();
            Movie movie1 = new Movie();
            if (movieType == null) {
                MovieType movieType1 = movieTypeRepo.findByName(EMovie.MOVIE_NORMAL)
                        .orElseThrow(() -> new RuntimeException(("Error: MovieType is not found")));
                movie1.setMovieType(movieType1);
            } else {
                switch (movieType) {
                    case "normal":
                        MovieType movieType1 = movieTypeRepo.findByName(EMovie.MOVIE_NORMAL)
                                .orElseThrow(() -> new RuntimeException(("Error: MovieType is not found")));
                        movie1.setMovieType(movieType1);
                        break;
                    case "premium":
                        MovieType movieType2 = movieTypeRepo.findByName(EMovie.MOVIE_PREMIUM)
                                .orElseThrow(() -> new RuntimeException(("Error: MovieType is not found")));
                        movie1.setMovieType(movieType2);
                        break;
                    case "MOVIE_NORMAL":
                        MovieType movieType3 = movieTypeRepo.findByName(EMovie.MOVIE_NORMAL)
                                .orElseThrow(() -> new RuntimeException(("Error: MovieType is not found")));
                        movie1.setMovieType(movieType3);
                        break;
                    case "MOVIE_PREMIUM":
                        MovieType movieType4 = movieTypeRepo.findByName(EMovie.MOVIE_PREMIUM)
                                .orElseThrow(() -> new RuntimeException(("Error: MovieType is not found")));
                        movie1.setMovieType(movieType4);
                        break;
                }
            }

            movie1.setName(movieDto.getName());
            movie1.setView(0);
            movie1.setDescription(movieDto.getDescription());
            movie1.setDuration(movieDto.getDuration());
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("Zathura.jpg")) {
                String result = "https://i.ibb.co/Z1LtvZ9/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("Iron-man.jpg")) {
                String result = "https://i.ibb.co/vH0ZKDw/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("6th-sense.jpg")) {
                String result = "https://i.ibb.co/sy4tPdL/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("signs.jpg")) {
                String result = "https://i.ibb.co/xHfWPYZ/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("Robocop.jpg")) {
                String result = "https://i.ibb.co/98QmCj1/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("Starship-trooper.jpg")) {
                String result = "https://i.ibb.co/qmKYz1f/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("Terminator.jpg")) {
                String result = "https://i.ibb.co/yf6gM1m/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("Titanic.jpg")) {
                String result = "https://i.ibb.co/kHBJqrD/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("Tomorrowland.jpg")) {
                String result = "https://i.ibb.co/JHmC61R/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("The-Incredibles.jpg")) {
                String result = "https://i.ibb.co/JnGCScq/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("existenz.jpg")) {
                String result = "https://i.ibb.co/gdj5nD0/1.jpg";
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("Videodrome.jpg")) {
                String result = "https://i.ibb.co/z5F0CfT/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("Jurasic-Park.jpg")) {
                String result = "https://i.ibb.co/FBpcRNG/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("Schindler-slist.jpg")) {
                String result = "https://i.ibb.co/RHxRnBF/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("Kill-Bill-1.jpg")) {
                String result = "https://i.ibb.co/1R2Gxm0/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("Mr-fox.jpg")) {
                String result = "https://i.ibb.co/q1FNDh2/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("Bumblebee.jpg")) {
                String result = "https://i.ibb.co/P5g4Tgf/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("Tron-Legacy.jpg")) {
                String result = "https://i.ibb.co/VJb5fMh/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("Army-of-darkness.jpg")) {
                String result = "https://i.ibb.co/PChNTBp/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("Goblin.jpg")) {
                String result = "https://i.imgur.com/kCLQINu.jpg";
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("Manofsteel.jpg")) {
                String result = "https://i.ibb.co/L66nTT8/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("The-Batman.jpg")) {
                String result = "https://i.ibb.co/cTNhhzM/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("The-Avengers.jpg")) {
                String result = "https://i.ibb.co/t3Ky3fR/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("Ratchet-Clank.jpg")) {
                String result = "https://i.ibb.co/Qd2rD5M/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("Spidey-No-Way-Home.jpg")) {
                String result = "https://i.imgur.com/v6NRH9X.jpg";
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("Morbius.jpg")) {
                String result = "https://i.ibb.co/Gd4hJwF/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("Darkman.jpg")) {
                String result = "https://i.ibb.co/2q378MQ/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("scarface.jpg")) {
                String result = "https://i.ibb.co/L8rQC39/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("The-godfather.jpg")) {
                String result = "https://i.ibb.co/MVvvTBB/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("Mad-Max.jpg")) {
                String result = "https://i.ibb.co/3hNw121/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("The-Matrix.jpg")) {
                String result = "https://i.ibb.co/hXq9GNL/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("Venom.jpg")) {
                String result = "https://i.ibb.co/Gd4b2t6/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("Sin-City.jpg")) {
                String result = "https://i.ibb.co/q7Kpxc0/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("Akira.jpg")) {
                String result = "https://i.ibb.co/yYQkB9s/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("Max-Payne.jpg")) {
                String result = "https://i.ibb.co/JsZSB0D/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("Prince-of-persia.jpg")) {
                String result = "https://i.ibb.co/xMwqgcz/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }
            if(thumbnail.getOriginalFilename().equalsIgnoreCase("shazam.jpg")) {
                String result = "https://i.ibb.co/gwrBZc3/" + thumbnail.getOriginalFilename();
                movie1.setThumbnail(result);
            }



            movie1.setUrl(movieDto.getUrl());
            movie1.getCategories().addAll(movieDto.getCategories().stream()
                    .map(m -> {
                        Category category = categoryRepo.findByName(m.getName());
                        category.getMovies().add(movie1);
                        return category;
                    }).collect(Collectors.toList()));
            movie1.getCasts().addAll(movieDto.getCasts().stream()
                    .map(c -> {
                        Cast cast = castRepo.findByName(c.getName());
                        cast.getMovies().add(movie1);
                        return cast;
                    }).collect(Collectors.toList()));
            movie1.getDirectors().addAll(movieDto.getDirectors().stream()
                    .map(d -> {
                        Director director = directorRepo.findByName(d.getName());
                        director.getMovies().add(movie1);
                        return director;
                    }).collect(Collectors.toList()));

            movieRepo.save(movie1);
            return new ResponseEntity<>(RESTResponse.success(movie1
                    , "Create Movie successful!"), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new RESTResponse.Error()
                    .badRequestWithMessage("Wrong file extension")
                    .build(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateMovie(@Valid @PathVariable int id , @RequestParam MultipartFile thumbnail , String jsonObject) throws IOException
    {
        try {
            MovieDto movieDto = null;
            movieDto = new ObjectMapper().readValue(jsonObject , MovieDto.class);

            Path staticPath = Paths.get("static");
            Path imagePath = Paths.get("images");
            String filename = thumbnail.getOriginalFilename();
            int index = filename.lastIndexOf('.');
            String extension = filename.substring(index + 1);
            if(extension.equalsIgnoreCase("jpg")) {
                if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
                    Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
                }
                Path file = CURRENT_FOLDER.resolve(staticPath).resolve(imagePath).resolve(thumbnail.getOriginalFilename());
                try (OutputStream os = Files.newOutputStream(file)) {
                    os.write(thumbnail.getBytes());
                }

                String movieType = movieDto.getMovieType();
                Movie movie1 = movieService.getMovieById(id);
                if (movieType == null) {
                    MovieType movieType1 = movieTypeRepo.findByName(EMovie.MOVIE_NORMAL)
                            .orElseThrow(() -> new RuntimeException(("Error: MovieType is not found")));
                    movie1.setMovieType(movieType1);
                } else {
                    switch (movieType) {
                        case "normal":
                            MovieType movieType1 = movieTypeRepo.findByName(EMovie.MOVIE_NORMAL)
                                    .orElseThrow(() -> new RuntimeException(("Error: MovieType is not found")));
                            movie1.setMovieType(movieType1);
                            break;
                        case "premium":
                            MovieType movieType2 = movieTypeRepo.findByName(EMovie.MOVIE_PREMIUM)
                                    .orElseThrow(() -> new RuntimeException(("Error: MovieType is not found")));
                            movie1.setMovieType(movieType2);
                            break;
                        case "MOVIE_NORMAL":
                            MovieType movieType3 = movieTypeRepo.findByName(EMovie.MOVIE_NORMAL)
                                    .orElseThrow(() -> new RuntimeException(("Error: MovieType is not found")));
                            movie1.setMovieType(movieType3);
                            break;
                        case "MOVIE_PREMIUM":
                            MovieType movieType4 = movieTypeRepo.findByName(EMovie.MOVIE_PREMIUM)
                                    .orElseThrow(() -> new RuntimeException(("Error: MovieType is not found")));
                            movie1.setMovieType(movieType4);
                            break;
                    }
                }

                movie1.setName(movieDto.getName());
                movie1.setView(movieDto.getView());
                movie1.setDescription(movieDto.getDescription());
                movie1.setDuration(movieDto.getDuration());
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("Zathura.jpg")) {
                    String result = "https://i.ibb.co/Z1LtvZ9/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("Iron-man.jpg")) {
                    String result = "https://i.ibb.co/vH0ZKDw/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("6th-sense.jpg")) {
                    String result = "https://i.ibb.co/sy4tPdL/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("signs.jpg")) {
                    String result = "https://i.ibb.co/xHfWPYZ/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("Robocop.jpg")) {
                    String result = "https://i.ibb.co/98QmCj1/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("Starship-trooper.jpg")) {
                    String result = "https://i.ibb.co/qmKYz1f/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("Terminator.jpg")) {
                    String result = "https://i.ibb.co/yf6gM1m/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("Titanic.jpg")) {
                    String result = "https://i.ibb.co/kHBJqrD/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("Tomorrowland.jpg")) {
                    String result = "https://i.ibb.co/JHmC61R/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("The-Incredibles.jpg")) {
                    String result = "https://i.ibb.co/JnGCScq/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("existenz.jpg")) {
                    String result = "https://i.ibb.co/gdj5nD0/1.jpg";
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("Videodrome.jpg")) {
                    String result = "https://i.ibb.co/z5F0CfT/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("Jurasic-Park.jpg")) {
                    String result = "https://i.ibb.co/FBpcRNG/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("Schindler-slist.jpg")) {
                    String result = "https://i.ibb.co/RHxRnBF/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("Kill-Bill-1.jpg")) {
                    String result = "https://i.ibb.co/1R2Gxm0/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("Mr-fox.jpg")) {
                    String result = "https://i.ibb.co/q1FNDh2/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("Bumblebee.jpg")) {
                    String result = "https://i.ibb.co/P5g4Tgf/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("Tron-Legacy.jpg")) {
                    String result = "https://i.ibb.co/VJb5fMh/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("Army-of-darkness.jpg")) {
                    String result = "https://i.ibb.co/PChNTBp/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("Goblin.jpg")) {
                    String result = "https://i.imgur.com/kCLQINu.jpg";
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("Manofsteel.jpg")) {
                    String result = "https://i.ibb.co/L66nTT8/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("The-Batman.jpg")) {
                    String result = "https://i.ibb.co/cTNhhzM/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("The-Avengers.jpg")) {
                    String result = "https://i.ibb.co/t3Ky3fR/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("Ratchet-Clank.jpg")) {
                    String result = "https://i.ibb.co/Qd2rD5M/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("Spidey-No-Way-Home.jpg")) {
                    String result = "https://i.imgur.com/v6NRH9X.jpg";
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("Morbius.jpg")) {
                    String result = "https://i.ibb.co/Gd4hJwF/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("Darkman.jpg")) {
                    String result = "https://i.ibb.co/2q378MQ/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("scarface.jpg")) {
                    String result = "https://i.ibb.co/L8rQC39/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("The-godfather.jpg")) {
                    String result = "https://i.ibb.co/MVvvTBB/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("Mad-Max.jpg")) {
                    String result = "https://i.ibb.co/3hNw121/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("The-Matrix.jpg")) {
                    String result = "https://i.ibb.co/hXq9GNL/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("Venom.jpg")) {
                    String result = "https://i.ibb.co/Gd4b2t6/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("Sin-City.jpg")) {
                    String result = "https://i.ibb.co/q7Kpxc0/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("Akira.jpg")) {
                    String result = "https://i.ibb.co/yYQkB9s/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("Max-Payne.jpg")) {
                    String result = "https://i.ibb.co/JsZSB0D/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("Prince-of-persia.jpg")) {
                    String result = "https://i.ibb.co/xMwqgcz/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }
                if(thumbnail.getOriginalFilename().equalsIgnoreCase("shazam.jpg")) {
                    String result = "https://i.ibb.co/gwrBZc3/" + thumbnail.getOriginalFilename();
                    movie1.setThumbnail(result);
                }

                movie1.setUrl(movieDto.getUrl());

                movie1.getCategories().removeAll(movie1.getCategories().stream()
                        .map(m -> {
                            Category category = categoryRepo.findByName(m.getName());
                            category.getMovies().remove(movie1);
                            return category;
                        }).collect(Collectors.toList()));

                movie1.getCasts().removeAll(movie1.getCasts().stream()
                        .map(c -> {
                            Cast cast = castRepo.findByName(c.getName());
                            cast.getMovies().remove(movie1);
                            return cast;
                        }).collect(Collectors.toList()));

                movie1.getDirectors().removeAll(movie1.getDirectors().stream()
                        .map(d -> {
                            Director director = directorRepo.findByName(d.getName());
                            director.getMovies().remove(movie1);
                            return director;
                        }).collect(Collectors.toList()));

                movie1.getCategories().addAll(movieDto.getCategories().stream()
                        .map(m -> {
                            Category category = categoryRepo.findByName(m.getName());
                            category.getMovies().add(movie1);
                            return category;
                        }).collect(Collectors.toList()));

                movie1.getCasts().addAll(movieDto.getCasts().stream()
                        .map(c -> {
                            Cast cast = castRepo.findByName(c.getName());
                            cast.getMovies().add(movie1);
                            return cast;
                        }).collect(Collectors.toList()));
                movie1.getDirectors().addAll(movieDto.getDirectors().stream()
                        .map(d -> {
                            Director director = directorRepo.findByName(d.getName());
                            director.getMovies().add(movie1);
                            return director;
                        }).collect(Collectors.toList()));
                movieRepo.save(movie1);
                return new ResponseEntity<>(RESTResponse.success(movie1
                        , "Update movie successful!"), HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(new RESTResponse.Error()
                        .badRequestWithMessage("Wrong file extension")
                        .build(),HttpStatus.BAD_REQUEST);
            }
        }catch(EntityNotFoundException e){ }
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Wrong id or id doesn't exist!")
                .build(), HttpStatus.NOT_FOUND);
    }


    @GetMapping("admin/list")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllMoviesByDeleteState(@RequestParam(name="isDeleted") boolean deleted,
                                                       @RequestParam(value = "page" , defaultValue = "0") int page,
                                                       @RequestParam(value = "size" , defaultValue = "3") int size){

        Pageable paging = PageRequest.of(page , size);
        PaginationResponse paginationResponse = new PaginationResponse();
        Page<Movie> pageTuts;
        pageTuts = movieService.getMoviesByDeleteState(deleted , paging);
        paginationResponse.currentPage = pageTuts.getNumber();
        paginationResponse.totalItems = (int) pageTuts.getTotalElements();
        paginationResponse.totalPages = pageTuts.getTotalPages();
        paginationResponse.datas = pageTuts.getContent();
        return new ResponseEntity<>(RESTResponse.success(paginationResponse ,"Get list movie successful!")
                , HttpStatus.OK);
    }

    @GetMapping("/movietype")
    public ResponseEntity<?> getAllMovieTypes(){
        return new ResponseEntity<>(RESTResponse.success(movieTypeService.getAllMovieTypes() , "Get list Movie Type successful!")
        ,HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> softDeleteMovie(@PathVariable(value="id")int id){
        try{
            movieService.deleteMovieById(id);
            return new ResponseEntity<>(new RESTResponse.SuccessNoData()
                    .setMessage("Delete successful !")
                    .build(), HttpStatus.OK);
        }catch(EmptyResultDataAccessException e){}
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Wrong id or id doesn't exist!")
                .build(), HttpStatus.NOT_FOUND);
    }

    @PutMapping("statechange/{id}")
    public ResponseEntity<?> setDeleteStateMovie(@PathVariable(value="id") int id, @RequestParam(name="isDeleted") boolean deleted){
        try{
            Movie movie = movieService.getMovieById(id);
            movie.setDeleted(deleted);
            movieService.saveMovieForStateChange(movie);
            return new ResponseEntity<>(new RESTResponse.SuccessNoData()
                    .setMessage("Change Delete State successful !")
                    .build(), HttpStatus.OK);
        }catch(EntityNotFoundException e){}
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Wrong id or id doesn't exist!")
                .build(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/movieCount")
    public ResponseEntity<?> getMovieCount()
    {
        return new ResponseEntity<>(RESTResponse.success(movieService.getMoviesCount(),"Get list movie successful!")
                , HttpStatus.OK);
    }

    @GetMapping("admin/getName")
    public ResponseEntity<?> getAllMoviesByName(@RequestParam(name = "name" , required = false) String name,
                                               @RequestParam(value = "page" , defaultValue = "0") int page,
                                               @RequestParam(value = "size" , defaultValue = "3") int size)
    {
        if(name == null)
        {
            Pageable paging = PageRequest.of(page , size);
            PaginationResponse paginationResponse = new PaginationResponse();
            Page<Movie> pageTuts;
            pageTuts = movieService.getMoviesByDeleteState(false , paging);
            paginationResponse.currentPage = pageTuts.getNumber();
            paginationResponse.totalItems = (int) pageTuts.getTotalElements();
            paginationResponse.totalPages = pageTuts.getTotalPages();
            paginationResponse.datas = pageTuts.getContent();
            return new ResponseEntity<>(RESTResponse.success(paginationResponse ,"Get list movie successful!")
                    , HttpStatus.OK);
        }
        else {
            Pageable paging = PageRequest.of(page, size);
            PaginationResponse paginationResponse = new PaginationResponse();
            Page<Movie> pageTuts;
            pageTuts = movieService.getMoviesByName(name, paging);
            paginationResponse.currentPage = pageTuts.getNumber();
            paginationResponse.totalItems = (int) pageTuts.getTotalElements();
            paginationResponse.totalPages = pageTuts.getTotalPages();
            paginationResponse.datas = pageTuts.getContent();
            return new ResponseEntity<>(RESTResponse.success(paginationResponse, "Get list movie by name successful!")
                    , HttpStatus.OK);
        }
    }

    @GetMapping("admin/getCategory")
    public ResponseEntity<?> getAllMoviesByCategories(@RequestParam(name = "category") String category,
                                               @RequestParam(value = "page" , defaultValue = "0") int page,
                                               @RequestParam(value = "size" , defaultValue = "3") int size)
    {
        Category category1 = categoryRepo.findByName(category);
        Pageable paging = PageRequest.of(page , size);
        PaginationResponse paginationResponse = new PaginationResponse();
        Page<Movie> pageTuts;
        pageTuts = movieService.getMoviesByCategory(category1 , paging);
        paginationResponse.currentPage = pageTuts.getNumber();
        paginationResponse.totalItems = (int) pageTuts.getTotalElements();
        paginationResponse.totalPages = pageTuts.getTotalPages();
        paginationResponse.datas = pageTuts.getContent();
        return new ResponseEntity<>(RESTResponse.success(paginationResponse ,"Get list movie by category successful!")
                , HttpStatus.OK);
    }

    @GetMapping("admin/getDirector")
    public ResponseEntity<?> getAllMoviesByDirectors(@RequestParam(name = "director") String director,
                                                      @RequestParam(value = "page" , defaultValue = "0") int page,
                                                      @RequestParam(value = "size" , defaultValue = "3") int size)
    {
        Director director1 = directorRepo.findByName(director);
        Pageable paging = PageRequest.of(page , size);
        PaginationResponse paginationResponse = new PaginationResponse();
        Page<Movie> pageTuts;
        pageTuts = movieService.getMoviesByDirector(director1 , paging);
        paginationResponse.currentPage = pageTuts.getNumber();
        paginationResponse.totalItems = (int) pageTuts.getTotalElements();
        paginationResponse.totalPages = pageTuts.getTotalPages();
        paginationResponse.datas = pageTuts.getContent();
        return new ResponseEntity<>(RESTResponse.success(paginationResponse ,"Get list movie by director successful!")
                , HttpStatus.OK);
    }

    @GetMapping("admin/getCast")
    public ResponseEntity<?> getAllMoviesByCasts(   @RequestParam(name = "cast") String cast,
                                                     @RequestParam(value = "page" , defaultValue = "0") int page,
                                                     @RequestParam(value = "size" , defaultValue = "3") int size)
    {
        Cast cast1 = castRepo.findByName(cast);
        Pageable paging = PageRequest.of(page , size);
        PaginationResponse paginationResponse = new PaginationResponse();
        Page<Movie> pageTuts;
        pageTuts = movieService.getMoviesByCast(cast1 , paging);
        paginationResponse.currentPage = pageTuts.getNumber();
        paginationResponse.totalItems = (int) pageTuts.getTotalElements();
        paginationResponse.totalPages = pageTuts.getTotalPages();
        paginationResponse.datas = pageTuts.getContent();
        return new ResponseEntity<>(RESTResponse.success(paginationResponse ,"Get list movie by cast successful!")
                , HttpStatus.OK);
    }


    @GetMapping("admin/getMovie/{id}")
    public ResponseEntity<?> getMovieById( @PathVariable(value ="id") int id )
    {
        Movie movie = movieService.getMovieById(id);
        return new ResponseEntity<>(RESTResponse.success( movie ,"Get movie by cast successful!")
                , HttpStatus.OK);
    }



}
