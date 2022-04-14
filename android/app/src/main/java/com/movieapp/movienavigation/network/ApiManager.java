package com.movieapp.movienavigation.network;

import com.movieapp.movienavigation.response.Accountype;
import com.movieapp.movienavigation.response.BaseResponseDto;
import com.movieapp.movienavigation.response.CategoryResponse;
import com.movieapp.movienavigation.response.Data;
import com.movieapp.movienavigation.response.Favorite;
import com.movieapp.movienavigation.response.HomeContentDto;
import com.movieapp.movienavigation.response.LoginRequest;
import com.movieapp.movienavigation.response.Notify;
import com.movieapp.movienavigation.response.PasswordDto;
import com.movieapp.movienavigation.response.TransactionResDto;
import com.movieapp.movienavigation.response.User;
import com.movieapp.movienavigation.response.UserDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiManager {
    String SERVER = "http://10.0.2.2:8080";

    @GET("/api/v1/all/movie/list/home")
    Call<BaseResponseDto<HomeContentDto>> apiHome(@Query("customerId") int id);

    @POST("/api/auth/signin")
    Call<User> loginUser(@Body LoginRequest loginRequest);

    @POST("/api/auth/signup")
    Call<User> registerUser(@Body User user);

    @GET("/api/v1/all/name")
    Call<HomeContentDto> getNameMovie(@Query("name")String text,
                                      @Query("customerId") int id);

    @GET("/api/v1/all/category/list")
    Call<CategoryResponse> getAllCategory();

//    @GET("/api/v1/all/category/listNormal")
//    Call<HomeContentDto> getMovieByCategory(@Query("category")String text);
//
//    @GET("/api/v1/all/category/listNormal")
//    Call<HomeContentDto> getListMovieByCategory(@Query("category")List<String> text);

    @GET("/api/v1/all/category")
    Call<HomeContentDto> getMovieByCategory(@Query("category")String text,
                                            @Query("customerId") int id);

    @GET("/api/v1/all/category")
    Call<HomeContentDto> getListMovieByCategory(@Query("category")List<String> text,
                                                @Query("customerId") int id);

    @GET("/api/v1/all/favorite/get/all")
    Call<HomeContentDto>TakeMovieFav(@Query("customerId") Integer id);

    @POST("/api/v1/all/favorite/add")
    Call<Favorite>PostMovieFav(@Query("movieId") Integer movieid,
                               @Query("customerId") Integer cusid);

    @DELETE("/api/v1/all/favorite/remove")
    Call<Favorite>DeleteMovieFav(@Query("movieId") Integer movieid,
                               @Query("customerId") Integer cusid);

    @PUT("/api/v1/all/updateUser/{id}")
    Call<User> updateUser(@Path("id") int id,@Body UserDto userDto);

    @PUT("/api/v1/all/updatePassword/{id}")
    Call<User> updatePassword(@Path("id") int id,@Body PasswordDto passwordDto);

    @PUT("/api/v1/all/viewcal/{id}")
    Call<HomeContentDto> AddView(@Path("id") int id);

    @GET("/api/v1/all/movie/list")
    Call<HomeContentDto> ListMovie( @Query("customerId") int id);

    @PUT("/api/v1/all/account/type/vip")
    Call<Notify> updateAccountVip(@Query("customerId") int id);

    @PUT("/api/v1/all/account/type/normal")
    Call<Notify> updateAccountNormal(@Query("customerId") int id);
    @GET("/api/v1/all/user/get/accountType")
    Call<Accountype> AccountType(@Query("customerId") int id);
    @GET("/api/v1/all/director")
    Call<HomeContentDto> getMovieByDirector(@Query("director")String text,
                                            @Query("customerId") int id);
    @GET("/api/v1/all/cast")
    Call<HomeContentDto> getMovieByCast(@Query("cast")String text,
                                            @Query("customerId") int id);

    @GET("/api/v1/all/subscription/status")
    Call<TransactionResDto> getSubscriptionDate(@Query("customerId") int id);
}
