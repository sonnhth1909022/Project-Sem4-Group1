package com.projectsem4.backend.controller;

import com.projectsem4.backend.Enums.FavoriteStatus;
import com.projectsem4.backend.Payload.Response.MessageResponse;
import com.projectsem4.backend.dto.cast.CastDtoRes;
import com.projectsem4.backend.dto.cast.CastMapper;
import com.projectsem4.backend.dto.category.CategoryDtoRes;
import com.projectsem4.backend.dto.category.CategoryMapper;
import com.projectsem4.backend.dto.director.DirectorDtoRes;
import com.projectsem4.backend.dto.director.DirectorMapper;
import com.projectsem4.backend.dto.favorite.FavoriteDto;
import com.projectsem4.backend.dto.movie.HomeContentDto;
import com.projectsem4.backend.dto.movie.MovieDtoRes;
import com.projectsem4.backend.dto.movie.MovieMapper;
import com.projectsem4.backend.dto.movie.MovieTypeDto;
import com.projectsem4.backend.dto.payment.SubscriptionDto;
import com.projectsem4.backend.dto.user.AccountTypeDto;
import com.projectsem4.backend.dto.user.ChangePasswordDto;
import com.projectsem4.backend.dto.user.UpdateDto;
import com.projectsem4.backend.dto.user.UserDto;
import com.projectsem4.backend.entity.*;
import com.projectsem4.backend.repository.*;
import com.projectsem4.backend.service.*;
import com.projectsem4.backend.ulti.RESTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/all/")
public class CustomerViewController {

    /**
     * Api lấy movie
     **/
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private CustomerMovieFavService customerMovieFavService;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private DirectorRepo directorRepo;

    @Autowired
    private CastRepository castRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepo movieRepo;

    //Lấy toàn bộ movie
    @GetMapping("movie/list")
    public ResponseEntity<?> getAllMovies(@RequestParam int customerId) {
        Optional<User> user = userService.findUserById(customerId);
        if(user.isPresent()){
            List<MovieDtoRes> listAllMovies = movieMapper.INSTANCE.lsMovieToMovieDtoRes(movieService.getAllMovies());
            for (MovieDtoRes item: listAllMovies){
                Optional<CustomerMovieFavorite> favorite = customerMovieFavService.findFavoriteByCustomerIdAndMovieId(customerId, item.getId());
                if(favorite.isPresent()){
                    item.setFavorite(favorite.get().getFavorite());
                }
                else{
                    item.setFavorite(FavoriteStatus.FALSE.name());
                }
            }
            return new ResponseEntity<>(RESTResponse.success(listAllMovies, "Get list Movie successful!")
                    , HttpStatus.OK);
        }
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Customer Id not found!")
                .build(), HttpStatus.NOT_FOUND);
    }

    //Lấy 1 movie theo id
    @GetMapping("movie/get")
    public ResponseEntity<?> getMovieById(@RequestParam int movieId, @RequestParam int customerId) {
        Optional<User> user = userService.findUserById(customerId);
        Optional<Movie> movie = movieService.findMovieById(movieId);
        if (user.isPresent()){
            if(movie.isPresent()){
                MovieDtoRes movieDtoRes = movieMapper.INSTANCE.movieToMovieDtoRes(movie.get());
                Optional<CustomerMovieFavorite> favorite = customerMovieFavService.findFavoriteByCustomerIdAndMovieId(customerId, movieId);
                if(favorite.isPresent()){
//                    movieDtoRes.setFavorite(favorite.get().getFavorite());
                    return new ResponseEntity<>(RESTResponse.success(movieDtoRes
                            , "Found movie with this id !"), HttpStatus.OK);
                }
//                movieDtoRes.setFavorite(FavoriteStatus.FALSE.name());
                return new ResponseEntity<>(RESTResponse.success(movieDtoRes
                        , "Found movie with this id !"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new RESTResponse.Error()
                    .checkErrorWithMessage("Movie not found!")
                    .build(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Customer not found!")
                .build(), HttpStatus.NOT_FOUND);
    }


    //Lấy các list movie hiển thị ra trang home (android)
    @GetMapping("movie/list/home")
    public ResponseEntity<?> getHomeContentMovies(@RequestParam int customerId) {

        Optional<User> user = userService.findUserById(customerId);
        if (user.isPresent()){
            HomeContentDto homeContentDto = new HomeContentDto();

            List<MovieDtoRes> listPremium = movieMapper.INSTANCE.lsMovieToMovieDtoRes(movieService.getMoviesPremium());
            for(MovieDtoRes item: listPremium){
                Optional<CustomerMovieFavorite> favorite = customerMovieFavService.findFavoriteByCustomerIdAndMovieId(customerId, item.getId());
                if(favorite.isPresent()){
                    item.setFavorite(favorite.get().getFavorite());
                }
                else{
                    item.setFavorite(FavoriteStatus.FALSE.name());
                }
            }


            List<MovieDtoRes> listTrending = movieMapper.INSTANCE.lsMovieToMovieDtoRes(movieService.getAllMoviesByTrending());

            for(MovieDtoRes item: listTrending){
                Optional<CustomerMovieFavorite> favorite = customerMovieFavService.findFavoriteByCustomerIdAndMovieId(customerId, item.getId());
                if(favorite.isPresent()){
                    item.setFavorite(favorite.get().getFavorite());
                }
                else{
                    item.setFavorite(FavoriteStatus.FALSE.name());
                }
            }

            List<MovieDtoRes> listHot = movieMapper.INSTANCE.lsMovieToMovieDtoRes(movieService.getAllMoviesByHot());

            for(MovieDtoRes item: listHot){
                Optional<CustomerMovieFavorite> favorite = customerMovieFavService.findFavoriteByCustomerIdAndMovieId(customerId, item.getId());
                if(favorite.isPresent()){
                    item.setFavorite(favorite.get().getFavorite());
                }
                else{
                    item.setFavorite(FavoriteStatus.FALSE.name());
                }
            }

            List<MovieDtoRes> listAction = movieMapper.INSTANCE.lsMovieToMovieDtoRes(movieService.getAllMoviesByAction());

            for(MovieDtoRes item: listAction){
                Optional<CustomerMovieFavorite> favorite = customerMovieFavService.findFavoriteByCustomerIdAndMovieId(customerId, item.getId());
                if(favorite.isPresent()){
                    item.setFavorite(favorite.get().getFavorite());
                }
                else{
                    item.setFavorite(FavoriteStatus.FALSE.name());
                }
            }

            List<MovieDtoRes> listRomance = movieMapper.INSTANCE.lsMovieToMovieDtoRes(movieService.getAllMoviesByRomance());

            for(MovieDtoRes item: listRomance){
                Optional<CustomerMovieFavorite> favorite = customerMovieFavService.findFavoriteByCustomerIdAndMovieId(customerId, item.getId());
                if(favorite.isPresent()){
                    item.setFavorite(favorite.get().getFavorite());
                }
                else{
                    item.setFavorite(FavoriteStatus.FALSE.name());
                }
            }

            List<MovieDtoRes> listTelevision = movieMapper.INSTANCE.lsMovieToMovieDtoRes(movieService.getAllMoviesByTelevision());

            for(MovieDtoRes item: listTelevision){
                Optional<CustomerMovieFavorite> favorite = customerMovieFavService.findFavoriteByCustomerIdAndMovieId(customerId, item.getId());
                if(favorite.isPresent()){
                    item.setFavorite(favorite.get().getFavorite());
                }
                else{
                    item.setFavorite(FavoriteStatus.FALSE.name());
                }
            }


            homeContentDto.setListPremium(listPremium);
            homeContentDto.setListTrending(listTrending);
            homeContentDto.setListHot(listHot);
            homeContentDto.setListAction(listAction);
            homeContentDto.setListRomance(listRomance);
            homeContentDto.setListTelevision(listTelevision);


            return new ResponseEntity<>(RESTResponse.success(homeContentDto
                    , "Get Home contents successful!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Customer Id not found!")
                .build(), HttpStatus.NOT_FOUND);
    }

    //lay movie theo category ( ca normal va premium )
    @GetMapping("/category")
    public ResponseEntity<?> getMovieByCategory(@RequestParam(value = "category" , required = false) String category, @RequestParam int customerId)
    {
        if(category == null)
        {
            List<MovieDtoRes> listAllMovies = movieMapper.INSTANCE.lsMovieToMovieDtoRes(movieService.getAllMoviesByDeleteState(false));
            for(MovieDtoRes item: listAllMovies){
                Optional<CustomerMovieFavorite> favorite = customerMovieFavService.findFavoriteByCustomerIdAndMovieId(customerId, item.getId());
                if(favorite.isPresent()){
                    item.setFavorite(favorite.get().getFavorite());
                }
                else {
                    item.setFavorite(FavoriteStatus.FALSE.name());
                }
            }
            return new ResponseEntity<>(RESTResponse.success(listAllMovies,"Get list Movie successful!")
                        , HttpStatus.OK);
        }
        else
        {
            Category category1 = categoryRepo.findByName(category);
            List<MovieDtoRes> listMoviesByCategory = movieMapper.INSTANCE.lsMovieToMovieDtoRes(movieService.getMovieByCategory(category1));
            for(MovieDtoRes item1: listMoviesByCategory){
                Optional<CustomerMovieFavorite> favorite = customerMovieFavService.findFavoriteByCustomerIdAndMovieId(customerId, item1.getId());
                if(favorite.isPresent()){
                    item1.setFavorite(favorite.get().getFavorite());
                }
                else {
                    item1.setFavorite(FavoriteStatus.FALSE.name());
                }
            }

            return new ResponseEntity<>(RESTResponse.success(listMoviesByCategory,"Get list Movie successful!")
                    , HttpStatus.OK);
        }
    }



    // lay movie theo director
    @GetMapping("/director")
    public ResponseEntity<?> getMovieByDirector(@RequestParam(value = "director", required = false) String director, @RequestParam int customerId) {
        if (director == null) {
            List<MovieDtoRes> listAllMovies4 = movieMapper.INSTANCE.lsMovieToMovieDtoRes(movieService.getAllMoviesByDeleteState(false));
            for(MovieDtoRes item: listAllMovies4){
                Optional<CustomerMovieFavorite> favorite = customerMovieFavService.findFavoriteByCustomerIdAndMovieId(customerId, item.getId());
                if(favorite.isPresent()){
                    item.setFavorite(favorite.get().getFavorite());
                }
                else {
                    item.setFavorite(FavoriteStatus.FALSE.name());
                }
            }
            return new ResponseEntity<>(RESTResponse.success(listAllMovies4,"Get list Movie successful!")
                    , HttpStatus.OK);
        } else {
            Director director1 = directorRepo.findByName(director);
            List<MovieDtoRes> listMoviesByDirector = movieMapper.INSTANCE.lsMovieToMovieDtoRes(movieService.getMovieByDirector(director1));
            for(MovieDtoRes item4 : listMoviesByDirector){
                Optional<CustomerMovieFavorite> favorite = customerMovieFavService.findFavoriteByCustomerIdAndMovieId(customerId, item4.getId());
                if(favorite.isPresent()){
                    item4.setFavorite(favorite.get().getFavorite());
                }
                else {
                    item4.setFavorite(FavoriteStatus.FALSE.name());
                }
            }
            return new ResponseEntity<>(RESTResponse.success(listMoviesByDirector,"Get list Movie successful!")
                    , HttpStatus.OK);
        }
    }

    // lay movie theo cast
    @GetMapping("/cast")
    public ResponseEntity<?> getMovieByCast(@RequestParam(value = "cast", required = false) String cast, @RequestParam int customerId) {
        if (cast == null) {
            List<MovieDtoRes> listAllMovies3 = movieMapper.INSTANCE.lsMovieToMovieDtoRes(movieService.getAllMoviesByDeleteState(false));
            for(MovieDtoRes item: listAllMovies3){
                Optional<CustomerMovieFavorite> favorite = customerMovieFavService.findFavoriteByCustomerIdAndMovieId(customerId, item.getId());
                if(favorite.isPresent()){
                    item.setFavorite(favorite.get().getFavorite());
                }
                else {
                    item.setFavorite(FavoriteStatus.FALSE.name());
                }
            }
            return new ResponseEntity<>(RESTResponse.success(listAllMovies3,"Get list Movie successful!")
                    , HttpStatus.OK);
        } else {
            Cast cast1 = castRepository.findByName(cast);
            List<MovieDtoRes> listMoviesByCast = movieMapper.INSTANCE.lsMovieToMovieDtoRes(movieService.getMovieByCast(cast1));
            for(MovieDtoRes item3 : listMoviesByCast){
                Optional<CustomerMovieFavorite> favorite = customerMovieFavService.findFavoriteByCustomerIdAndMovieId(customerId, item3.getId());
                if(favorite.isPresent()){
                    item3.setFavorite(favorite.get().getFavorite());
                }
                else {
                    item3.setFavorite(FavoriteStatus.FALSE.name());
                }
            }
            return new ResponseEntity<>(RESTResponse.success(listMoviesByCast,"Get list Movie successful!")
                    , HttpStatus.OK);
        }
    }

    // lay movie theo name
    @GetMapping("/name")
    public ResponseEntity<?> getMovieByName( @RequestParam(value = "name" , required = false) String name, @RequestParam int customerId)
    {
        if(name == null)
        {
            List<MovieDtoRes> listAllMovies1 = movieMapper.INSTANCE.lsMovieToMovieDtoRes(movieService.getAllMoviesByDeleteState(false));
            for(MovieDtoRes item: listAllMovies1){
                Optional<CustomerMovieFavorite> favorite = customerMovieFavService.findFavoriteByCustomerIdAndMovieId(customerId, item.getId());
                if(favorite.isPresent()){
                    item.setFavorite(favorite.get().getFavorite());
                }
                else {
                    item.setFavorite(FavoriteStatus.FALSE.name());
                }
            }
            return new ResponseEntity<>(RESTResponse.success(listAllMovies1,"Get list Movie successful!")
                    , HttpStatus.OK);
        }
        else
        {
            List<MovieDtoRes> listMoviesByName = movieMapper.INSTANCE.lsMovieToMovieDtoRes(movieService.getMovieByName(name));
            for(MovieDtoRes item1 : listMoviesByName){
                Optional<CustomerMovieFavorite> favorite = customerMovieFavService.findFavoriteByCustomerIdAndMovieId(customerId, item1.getId());
                if(favorite.isPresent()){
                    item1.setFavorite(favorite.get().getFavorite());
                }
                else {
                    item1.setFavorite(FavoriteStatus.FALSE.name());
                }
            }
            return new ResponseEntity<>(RESTResponse.success(listMoviesByName,"Get list Movie successful!")
                    , HttpStatus.OK);
        }
    }

    /**
     * Api lấy category
     **/
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryService categoryService;

    //Lấy list item (chỉ có thể lấy list các item chưa xóa, dành cho user thường hoặc customer)
    @GetMapping("category/list")
    public ResponseEntity<?> getAllCategories() {
        return new ResponseEntity<>(RESTResponse.success(categoryMapper.INSTANCE
                .lsCategoryToCategoryDtoRes(categoryService
                        .getAllCategories()), "Get list Category successful!")
                , HttpStatus.OK);
    }

    // Lấy item theo id
    @GetMapping("category/get/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable(value = "id") int id) {
        try {
            Category category = categoryService.getCategoryById(id);
            CategoryDtoRes categoryDtoRes = categoryMapper.INSTANCE.categoryToCategoryDtoRes(category);
            return new ResponseEntity<>(RESTResponse.success(categoryDtoRes
                    , "Found a category with this id !"), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
        }
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Wrong id or id doesn't exist!")
                .build(), HttpStatus.NOT_FOUND);
    }


    /**
     * Api lấy director
     **/

    @Autowired
    private DirectorMapper directorMapper;

    @Autowired
    private DirectorService directorService;

    @GetMapping("director/list")
    public ResponseEntity<?> getAllDirectors() {
        return new ResponseEntity<>(RESTResponse.success(directorMapper.INSTANCE
                .lsDirectorToDirectorDtoRes(directorService
                        .getAllDirectors()), "Get list Director successful!")
                , HttpStatus.OK);
    }

    @GetMapping("director/get/{id}")
    public ResponseEntity<?> getDirectorById(@PathVariable(value = "id") int id) {
        try {
            Director director = directorService.getDirectorById(id);
            DirectorDtoRes directorDtoRes = directorMapper.INSTANCE.directorToDirectorDtoRes(director);
            return new ResponseEntity<>(RESTResponse.success(directorDtoRes
                    , "Found a director with this id !"), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
        }
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Wrong id or id doesn't exist!")
                .build(), HttpStatus.NOT_FOUND);
    }


    /**
     * Lấy api cast
     **/
    @Autowired
    private CastMapper castMapper;

    @Autowired
    private CastService castService;


    @GetMapping("cast/list")
    public ResponseEntity<?> getAllCasts() {
        return new ResponseEntity<>(RESTResponse.success(castMapper.INSTANCE
                .lsCastToCastDtoRes(castService
                        .getAllCasts()), "Get List cast successful!")
                , HttpStatus.OK);
    }

    @GetMapping("cast/get/{id}")
    public ResponseEntity<?> getCastById(@PathVariable(value = "id") int id) {
        try {
            Cast cast = castService.getCastById(id);
            CastDtoRes castDtoRes = castMapper.INSTANCE.castToCastDtoRes(cast);
            return new ResponseEntity<>(RESTResponse.success(castDtoRes
                    , "Found a cast with this id !"), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
        }
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Wrong id or id doesn't exist!")
                .build(), HttpStatus.NOT_FOUND);
    }


    /**
     * Lấy list favorite và add favorite
     **/
    @PostMapping("favorite/add")
    public ResponseEntity<?> addFavorite(@RequestParam int movieId, @RequestParam int customerId) {
        Optional<User> user = userService.findUserById(customerId);
        Optional<Movie> movie = movieService.findMovieById(movieId);
        if (user.isPresent()) {
            if (movie.isPresent()) {
                CustomerMovieKey favoriteKey = new CustomerMovieKey();
                favoriteKey.customerId = customerId;
                favoriteKey.movieId = movieId;
                CustomerMovieFavorite favorite = new CustomerMovieFavorite();
                favorite.setId(favoriteKey);
                favorite.setMovieId(movieId);
                favorite.setCustomerId(customerId);
                favorite.setUser(user.get());
                favorite.setMovie(movie.get());
                favorite.setFavorite(FavoriteStatus.TRUE.name());
                customerMovieFavService.addFavorite(favorite);
                FavoriteDto favoriteDto = new FavoriteDto();
                favoriteDto.setFavorite(favorite.getFavorite());
                return new ResponseEntity<>(RESTResponse.success(favoriteDto
                        , "Add movie to favorite successfully!"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new RESTResponse.Error()
                    .checkErrorWithMessage("Wrong movie Id!")
                    .build(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Wrong customer Id!")
                .build(), HttpStatus.NOT_FOUND);
    }


    @GetMapping("favorite/get/all")
    public ResponseEntity<?> getAllFavorites(@RequestParam int customerId) {
        Optional<User> user = userService.findUserById(customerId);
        if (user.isPresent()) {
            List<CustomerMovieFavorite> lsFavorite = customerMovieFavService.getAllFavorites(customerId);
            if (lsFavorite.isEmpty()) {
                return new ResponseEntity<>(new RESTResponse.SuccessNoData()
                        .setMessage("List favorite is empty !")
                        .build(), HttpStatus.OK);
            }
            Set<MovieDtoRes> favoritesResult = new HashSet<>();
            for(CustomerMovieFavorite item :lsFavorite){
                Optional<Movie> movie = movieService.findMovieById(item.getMovieId());
                if (movie.isPresent()){
                    MovieDtoRes movieDtoRes = movieMapper.INSTANCE.movieToMovieDtoRes(movie.get());
                    movieDtoRes.setFavorite(FavoriteStatus.TRUE.name());
                    favoritesResult.add(movieDtoRes);
                }
            }
            return new ResponseEntity<>(RESTResponse.success(favoritesResult
                    , "Get List Favorite successful !"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Wrong customer Id!")
                .build(), HttpStatus.NOT_FOUND);
    }

    @Transactional
    @DeleteMapping("favorite/remove")
    public ResponseEntity<?> removeFavorite(@RequestParam int movieId, @RequestParam int customerId) {
        Optional<CustomerMovieFavorite> favorite = customerMovieFavService.findFavoriteByCustomerIdAndMovieId(customerId, movieId);
        if (favorite.isPresent()) {
            customerMovieFavService.deleteFavoriteByCustomerIdAndMovieId(customerId, movieId);
            FavoriteDto favoriteDto = new FavoriteDto();
            favoriteDto.setFavorite(FavoriteStatus.FALSE.name());
            return new ResponseEntity<>(RESTResponse.success(favoriteDto
                    , "Remove favorite successfully!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("No favorite to remove!")
                .build(), HttpStatus.NOT_FOUND);
    }


    //Lấy trường favorite dựa theo movie và user id
    @GetMapping("favorite/status")
    public ResponseEntity<?> getFavoriteStatus(@RequestParam int movieId, @RequestParam int customerId){
        Optional<User> user = userService.findUserById(customerId);
        Optional<Movie> movie = movieService.findMovieById(movieId);
        if(user.isPresent()){
            if(movie.isPresent()){
                FavoriteDto favoriteDto = new FavoriteDto();
                Optional<CustomerMovieFavorite> favorite = customerMovieFavService.findFavoriteByCustomerIdAndMovieId(customerId, movieId);
                if(favorite.isPresent()){
                    favoriteDto.setFavorite(favorite.get().getFavorite());
                    return new ResponseEntity<>(RESTResponse.success(favoriteDto
                            , "Get Favorite Status successful !"), HttpStatus.OK);
                }
                favoriteDto.setFavorite(FavoriteStatus.FALSE.name());
                return new ResponseEntity<>(RESTResponse.success(favoriteDto
                        , "Get Favorite Status successful !"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new RESTResponse.Error()
                    .checkErrorWithMessage("Movie not found!")
                    .build(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Customer not found!")
                .build(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getMovie/{id}")
    public ResponseEntity<?> getListMovieType(@PathVariable(value="id")int id) {
        MovieTypeDto movieTypeDto = new MovieTypeDto();
        User user = userRepository.findById(id).get();
        List<Movie> list = new ArrayList<>();
        List<Movie> list1 = new ArrayList<>();
        List<Movie> movieList = movieRepo.findAll();
        if (user.getAccountType().getName().equals(EAccount.ACCOUNT_NORMAL)) {
            for (int i = 0; i < movieList.size(); i++) {
                Movie movie = movieList.get(i);
                if (movie.getMovieType().getName().equals(EMovie.MOVIE_NORMAL)) {
                    list.add(movie);
                }
            }

            movieTypeDto.setListNormal(movieMapper.INSTANCE
                    .lsMovieToMovieDtoRes(list));
            return new ResponseEntity<>(RESTResponse.success(movieTypeDto
                    , "Get list movie by type successfully"), HttpStatus.OK);
        } else if (user.getAccountType().getName().equals(EAccount.ACCOUNT_VIP)) {
            for (int i = 0; i < movieList.size(); i++) {
                Movie movie = movieList.get(i);
                if (movie.getMovieType().getName().equals(EMovie.MOVIE_PREMIUM)) {
                    list1.add(movie);
                }
                if (movie.getMovieType().getName().equals(EMovie.MOVIE_NORMAL)) {
                    list.add(movie);
                }
            }
            movieTypeDto.setListNormal(movieMapper.INSTANCE
                    .lsMovieToMovieDtoRes(list));
            movieTypeDto.setListPremium(movieMapper.INSTANCE
                    .lsMovieToMovieDtoRes(list1));
            return new ResponseEntity<>(RESTResponse.success(movieTypeDto
                    , "Get list movie by type successfully"), HttpStatus.OK);
        } else {
            return null;
        }
    }


    @GetMapping("/getAllPremiumMovies")
    private ResponseEntity<?> getListMoviePremium()
    {
        List<Movie> movieList = movieRepo.findAll();
        List<Movie> list = new ArrayList<>();
        for (int i = 0; i < movieList.size(); i++) {
            Movie movie = movieList.get(i);
            if (movie.getMovieType().getName().equals(EMovie.MOVIE_PREMIUM)) {
                list.add(movie);
            }
        }
        return new ResponseEntity<>(RESTResponse.success(list
                , "Get list movie premium successfully"), HttpStatus.OK);
    }

    @GetMapping("/user/get/{id}")
    public ResponseEntity<?> getUser(@PathVariable(value="id")int id)
    {
        try{
            User user = userService.getUserById(id);
            return new ResponseEntity<>(RESTResponse.success(user
                    ,"Found an user with this id !"),HttpStatus.OK);
        }catch(EntityNotFoundException e){}
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Wrong id or id doesn't exist!")
                .build(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/get/accountType")
    public ResponseEntity<?> getAccountType(@RequestParam int customerId){
        Optional<User> user = userService.findUserById(customerId);
        if(user.isPresent()){
            AccountTypeDto accountTypeDto = new AccountTypeDto();
            accountTypeDto.setAccountType(user.get().getAccountType().getName());
            return new ResponseEntity<>(RESTResponse.success(accountTypeDto
                    , "Get Account Type successful !"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Customer not found!")
                .build(), HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(value = "id") int id , @RequestBody UpdateDto updateDto)
    {
        User user = userRepository.findById(id).get();
        if(userRepository.existsByUsername(updateDto.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error : Username is already taken!"));
        }

        if (userRepository.existsByEmail(updateDto.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error : Email is already in use"));
        }
        user.setUsername(updateDto.getUsername());
        user.setPhone(updateDto.getPhone());
        user.setEmail(updateDto.getEmail());
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("Update user successfully"));
    }

    @PutMapping("/updatePassword/{id}")
    public ResponseEntity<?> updatePassword(@Valid @PathVariable(value = "id") int id , @RequestBody ChangePasswordDto changePasswordDto)
    {
        User user = userRepository.findById(id).get();
        if(!changePasswordDto.getNewPassword().equalsIgnoreCase(changePasswordDto.getRePassword()))
        {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error : Repassword is not equal to your new password"));
        }
        else
        {
            user.setPassword(encoder.encode(changePasswordDto.getNewPassword()));
            userRepository.save(user);
            return ResponseEntity.ok(new MessageResponse("Update user password successfully"));
        }
    }



    // Update tài khoản vip hoặc trở về tài khoản normal
    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionHistoryService transactionHistoryService;



    @PutMapping("account/type/normal")
    public ResponseEntity<?> updateAccountTypeNormal(@RequestParam int customerId){
        Optional<User> user = userService.findUserById(customerId);
        if(user.isPresent()){
            Optional<AccountType> accountType = accountService.findAccountTypeByName(EAccount.ACCOUNT_NORMAL);
            if(accountType.isPresent()){
                user.get().setAccountType(accountType.get());
                userService.saveUser(user.get());
                return new ResponseEntity<>(new RESTResponse.SuccessNoData()
                        .setMessage("Account type changed to normal !")
                        .build(), HttpStatus.OK);
            }
            return new ResponseEntity<>(new RESTResponse.Error()
                    .checkErrorWithMessage("Account Type not found!")
                    .build(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Customer not found!")
                .build(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("subscription/status")
    public ResponseEntity<?> getLatestSubscription(@RequestParam int customerId){
        Optional<User> user = userService.findUserById(customerId);
        if(user.isPresent()){
            Optional<TransactionHistory> sub = transactionHistoryService
                    .findLatestSubscriptionByCreatedDate(user.get().getId());
            EAccount accountType = user.get().getAccountType().getName();

            if(sub.isPresent() && accountType.equals(EAccount.ACCOUNT_VIP)){
                SubscriptionDto subDto = new SubscriptionDto();
                subDto.setPurchaseDate(sub.get().getPurchaseDate().toLocalDate().toString());
                subDto.setValidUntil(sub.get().getExpirationDate().toLocalDate().toString());
                subDto.setAccountType(accountType);
                return new ResponseEntity<>(subDto, HttpStatus.OK);
            } else{
                AccountTypeDto accountTypeDto = new AccountTypeDto();
                accountTypeDto.setAccountType(accountType);
                return new ResponseEntity<>(accountTypeDto, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Customer not found!")
                .build(), HttpStatus.NOT_FOUND);
    }

    @PutMapping("account/type/vip")
    public ResponseEntity<?> updateAccountTypeVip(@RequestParam int customerId){
        Optional<User> user = userService.findUserById(customerId);
        if(user.isPresent()){
            Optional<AccountType> accountType = accountService.findAccountTypeByName(EAccount.ACCOUNT_VIP);
            if(accountType.isPresent()){
                user.get().setAccountType(accountType.get());
                userService.saveUser(user.get());
                TransactionHistory transactionHistory = new TransactionHistory();
                transactionHistory.setAmount(70000);
                transactionHistory.setPurchaseDate(LocalDateTime.now());
                transactionHistory.setExpirationDate(LocalDateTime.now().plusDays(30));
                transactionHistory.setCustomerId(user.get().getId());
                transactionHistory.setUser(user.get());
                transactionHistoryService.saveTransactionHistory(transactionHistory);
                return new ResponseEntity<>(new RESTResponse.SuccessNoData()
                        .setMessage("Account type changed to Vip !")
                        .build(), HttpStatus.OK);
            }
            return new ResponseEntity<>(new RESTResponse.Error()
                    .checkErrorWithMessage("Account Type not found!")
                    .build(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Customer not found!")
                .build(), HttpStatus.NOT_FOUND);
    }

    @PutMapping("/viewcal/{id}")
    public ResponseEntity<?> updateMovie(@Valid @PathVariable int id)
    {
        try
        {
            Movie movie = movieService.getMovieById(id);
            movie.setView(movie.getView() + 1);
            movieRepo.save(movie);
            return new ResponseEntity<>(RESTResponse.success(movie
                    , "Update movie successful!"), HttpStatus.OK);
        }catch(EntityNotFoundException e){ }
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Wrong id or id doesn't exist!")
                .build(), HttpStatus.NOT_FOUND);
    }
}
