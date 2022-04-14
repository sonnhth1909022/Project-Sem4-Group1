package com.movieapp.movienavigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.movieapp.movienavigation.activity.ListMovieByCastActivity;
import com.movieapp.movienavigation.activity.ListMovieByCategoryActivity;
import com.movieapp.movienavigation.activity.ListMovieByCategoryDetailActivity;
import com.movieapp.movienavigation.activity.ListMovieByDirectorActivity;
import com.movieapp.movienavigation.activity.PaymentActivity;
import com.movieapp.movienavigation.adapter.CategoryAdapter;
import com.movieapp.movienavigation.adapter.ListActorAdapter;
import com.movieapp.movienavigation.adapter.ListCategoryAdapter;
import com.movieapp.movienavigation.adapter.ListDirectorAdapter;
import com.movieapp.movienavigation.adapter.ListMovieByCategoryAdapter;
import com.movieapp.movienavigation.adapter.ListOtherMovieAdapter;
import com.movieapp.movienavigation.adapter.MovieAdapter;
import com.movieapp.movienavigation.adapter.SearchNameAdapter;
import com.movieapp.movienavigation.adapter.SectionAdapter;
import com.movieapp.movienavigation.event.SaveAccountType;
import com.movieapp.movienavigation.event.SaveID;
import com.movieapp.movienavigation.model.Section;
import com.movieapp.movienavigation.network.ApiManager;
import com.movieapp.movienavigation.network.PassDataInterFace;
import com.movieapp.movienavigation.response.Accountype;
import com.movieapp.movienavigation.response.Cast;
import com.movieapp.movienavigation.response.Category;
import com.movieapp.movienavigation.response.Data;
import com.movieapp.movienavigation.response.Director;
import com.movieapp.movienavigation.response.Favorite;
import com.movieapp.movienavigation.response.HomeContentDto;
import com.movieapp.movienavigation.response.MovieDto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.movieapp.movienavigation.response.User;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity implements PassDataInterFace {
    RecyclerView recyclerView1;
    ImageView ivCover;
    ImageView ivCover2, ivFavBtn;
    Button btnFav;
    Button btnShareMovie;
    TextView tvDes, tvNameMov, tvNameDirector, tvFav;
    List<String> listDirector = new ArrayList<String>();
    List<String> listNameActor = new ArrayList<String>();
    List<String> listNameCategory = new ArrayList<String>();
    String nameCategory = new String();
    List<Section> listSections = new ArrayList<>();
    List<MovieDto> listMovie = new ArrayList<>();
    private SectionAdapter sectionAdapter;
    private MovieAdapter movieAdapter;
    private ListActorAdapter listActorApdater;
    private ListDirectorAdapter listDirectorAdapter;
    private ListCategoryAdapter listCategoryAdapter;
    int i = 0;
    SaveID saveID = new SaveID();
    public int idcus;
    int idCusDetail;
    SaveAccountType saveAccountType = new SaveAccountType();
    FloatingActionButton clickVideo;
    String nameAccountType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        idcus = saveID.getId(DetailActivity.this);
        AcountType(idcus);
        init();

    }

   /* @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }*/

    private void init() {

        Intent intent = getIntent();
        MovieDto movie = (MovieDto) intent.getSerializableExtra("movie");
        ArrayList<Cast> casts = movie.getCasts();
        ArrayList<Director> directors = movie.getDirectors();
        ArrayList<Category> categories = movie.getCategories();
        ivCover = findViewById(R.id.detail_movie_img);
        ivCover2 = findViewById(R.id.detail_movie_cover);
        tvDes = findViewById(R.id.detail_movie_title);
        tvFav = findViewById(R.id.tvFav);
        tvNameMov = findViewById(R.id.name_movie);
        clickVideo = findViewById(R.id.play_movie);
        ivFavBtn = findViewById(R.id.imageFavBtn);
       // ivPaymentBtn = findViewById(R.id.imagePaymentBtn);
        btnFav = findViewById(R.id.favBtn);
        btnShareMovie = findViewById(R.id.shareMovie);
        tvDes.setText(movie.getDescription());
        //  tvDes.setText(String.valueOf(movie.getView()));
        tvNameMov.setText(movie.getName());

        //lay name actor

        for (Cast elemet : casts) {
            listNameActor.add(elemet.getName());
          //  listNameActor.add(elemet.getDescription());
        }
        RecyclerView recyclerView = findViewById(R.id.name_actor);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listActorApdater = new ListActorAdapter(this, listNameActor);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listActorApdater);
        listActorApdater.setOnItemClick(new ListActorAdapter.OnItemClick() {
            @Override
            public void onClickCast(String value) {
                Intent intent = new Intent(DetailActivity.this, ListMovieByCastActivity.class);
                intent.putExtra("nameCast",value);
                startActivity(intent);
            }
        });


        //lay name director
        for (Director element : directors) {
            listDirector.add(element.getName());
        }
        RecyclerView recyclerViewDirector = findViewById(R.id.name_director);
        RecyclerView.LayoutManager layoutManagerDirector = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listDirectorAdapter = new ListDirectorAdapter(this, listDirector);
        recyclerViewDirector.setLayoutManager(layoutManagerDirector);
        recyclerViewDirector.setHasFixedSize(true);
        recyclerViewDirector.setAdapter(listDirectorAdapter);
        listDirectorAdapter.setOnItemClick(new ListDirectorAdapter.OnItemClick() {
            @Override
            public void onClickDirector(String value) {
                Intent intent = new Intent(DetailActivity.this, ListMovieByDirectorActivity.class);
                intent.putExtra("nameDirector",value);
                startActivity(intent);
            }
        });

        Glide.with(this).load(movie.getThumbnail()).into(ivCover);
        Glide.with(this).load(movie.getThumbnail()).into(ivCover2);

        //lay category
        for (Category element : categories) {
            listNameCategory.add(element.getName());
            break;
        }

        RecyclerView recyclerViewCategory = findViewById(R.id.category);
        RecyclerView.LayoutManager layoutManagerCategory = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listCategoryAdapter = new ListCategoryAdapter(this, listNameCategory);
        recyclerViewCategory.setLayoutManager(layoutManagerCategory);
        recyclerViewCategory.setHasFixedSize(true);
        recyclerViewCategory.setAdapter(listCategoryAdapter);
        listCategoryAdapter.setOnItemClick(new ListCategoryAdapter.OnItemClick() {
            @Override
            public void onClickCategory(String value) {
                Intent intent = new Intent(DetailActivity.this,ListMovieByCategoryDetailActivity.class);
                intent.putExtra("nameCategory",value);
                startActivity(intent);
            }
        });
        //other movie\
        List<String> newText = listNameCategory;
        idCusDetail = saveID.getId(DetailActivity.this);
        initData(newText, idCusDetail);

        Glide.with(this).load(movie.getThumbnail()).into(ivCover);
        Glide.with(this).load(movie.getThumbnail()).into(ivCover2);
        clickVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (movie.getMovieType().getName().equalsIgnoreCase("MOVIE_PREMIUM")&& nameAccountType.equalsIgnoreCase("ACCOUNT_NORMAL")) {

                    Toast.makeText(DetailActivity.this, "You don't have Premium Access!", Toast.LENGTH_LONG).show();

                } else {
                    AddView(movie.getId());
                    Intent intent1 = new Intent(DetailActivity.this, PlayVideo.class);
                    intent1.putExtra("url", movie.getUrl());
                    startActivity(intent1);

                }

            }
        });
        if (movie.getFavorite().equalsIgnoreCase("TRUE")) {
            ivFavBtn.setImageResource(R.drawable.ic_favorite_red_24);
        } else if (movie.getFavorite().equalsIgnoreCase("FALSE")) {
            ivFavBtn.setImageResource(R.drawable.ic__favorite);
        }

        btnFav.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                i++;
                Handler handler = new Handler();
                Runnable r = new Runnable() {

                    @Override
                    public void run() {
                        i = 0;
                    }
                };

                if (i == 1 && movie.getFavorite().equalsIgnoreCase("FALSE")) {
                    //Single click
                    ivFavBtn.setImageResource(R.drawable.ic_favorite_red_24);
                    ApiClickFav(movie.getId(), idcus);
                    handler.postDelayed(r, 250);
                } else if (i == 2) {
                    //Double click
                    ivFavBtn.setImageResource(R.drawable.ic__favorite);
                    ApiClickDisFav(movie.getId(), idcus);
                    i = 0;
                }


            }
        });

        btnShareMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "Want to watch a movie but too lazy to go to theater ?"
                        + "\nTry our app, it's free on Google Play: \n"
                        + "https://play.google.com/apps/internaltest/4701129790585039908");
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent, "Send To"));
            }
        });

    }

    private void ApiClickFav(int movieid, int cusid) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiManager service = retrofit.create(ApiManager.class);
        service.PostMovieFav(movieid, cusid).enqueue(new Callback<Favorite>() {
            @Override
            public void onResponse(Call<Favorite> call, Response<Favorite> response) {
                Log.d("TAG", "onResponse: " + response);
                if (response.isSuccessful()) {
                    Favorite model = response.body();
                    String message = model.getMessage();
                    Toast.makeText(DetailActivity.this, message, Toast.LENGTH_SHORT).show();
                    Api(idcus);
                } else {
                    String message = "Add To Favorite Failed";
                    Toast.makeText(DetailActivity.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Favorite> call, Throwable t) {

            }
        });
    }

    private void ApiClickDisFav(int movieid, int cusid) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiManager service = retrofit.create(ApiManager.class);
        service.DeleteMovieFav(movieid, cusid).enqueue(new Callback<Favorite>() {
            @Override
            public void onResponse(Call<Favorite> call, Response<Favorite> response) {
                Log.d("TAG", "onResponse: " + response);
                if (response.isSuccessful()) {
                    Favorite model = response.body();
                    String message = model.getMessage();
                    Toast.makeText(DetailActivity.this, message, Toast.LENGTH_SHORT).show();
                    Api(idcus);
                } else {
                    String message = "Delete To Favorite Failed";
                    Toast.makeText(DetailActivity.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Favorite> call, Throwable t) {

            }
        });
    }
    private void initData(List<String> newText, int id) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView1 = findViewById(R.id.rv_otherMovies);
        recyclerView1.setLayoutManager(layoutManager);
        recyclerView1.setHasFixedSize(true);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiManager service = retrofit.create(ApiManager.class);
        Call<HomeContentDto> call = service.getListMovieByCategory(newText, id);
        call.enqueue(new Callback<HomeContentDto>() {
            @Override
            public void onResponse(Call<HomeContentDto> call, Response<HomeContentDto> response) {
                if (response.isSuccessful()) {
                    HomeContentDto model = response.body();
                    listMovie = model.getData();
                    ListOtherMovieAdapter listOtherMovieAdapter = new ListOtherMovieAdapter(DetailActivity.this, listMovie);
                    recyclerView1.setAdapter(listOtherMovieAdapter);
                }
            }

            @Override
            public void onFailure(Call<HomeContentDto> call, Throwable t) {
                Log.d("TAG", "onFailure: ");
            }
        });
    }

    //add view
    private void AddView(int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiManager service = retrofit.create(ApiManager.class);
        service.AddView(id).enqueue(new Callback<HomeContentDto>() {
            @Override
            public void onResponse(Call<HomeContentDto> call, Response<HomeContentDto> response) {
                Log.d("TAG", "onResponse: " + response);
            }

            @Override
            public void onFailure(Call<HomeContentDto> call, Throwable t) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SearchNameAdapter.ClickItemMovieSearch event) {
        // Toast.makeText(getContext(),event.movie.getName(),Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("movie", event.movie);
        startActivity(intent);
    }
    //clickCategory
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(CategoryAdapter.ClickItemMovie event) {
        Intent intent = new Intent(DetailActivity.this, ListMovieByCategoryActivity.class);
        intent.putExtra("category", event.category);
        startActivity(intent);

    }

    @Override
    public void onDataReceived(String data) {

    }

    private void Api(int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiManager service = retrofit.create(ApiManager.class);
        service.TakeMovieFav(id).enqueue(new Callback<HomeContentDto>() {
            @Override
            public void onResponse(Call<HomeContentDto> call, Response<HomeContentDto> response) {

            }

            @Override
            public void onFailure(Call<HomeContentDto> call, Throwable t) {

            }
        });
    }

    private void AcountType(int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiManager service = retrofit.create(ApiManager.class);
        service.AccountType(id).enqueue(new Callback<Accountype>() {
            @Override
            public void onResponse(Call<Accountype> call, Response<Accountype> response) {
                Log.d("TAG", "onResponse: "+response);
                Accountype accountype = response.body();
                nameAccountType = accountype.data.accountType;
            }

            @Override
            public void onFailure(Call<Accountype> call, Throwable t) {

            }
        });
    }


    //listmovie by category
  /*  private void initData(String text, int id) {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView = findViewById(R.id.rvMovieByCategory);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiManager service = retrofit.create(ApiManager.class);
        Call<HomeContentDto> call = service.getMovieByCategory(text, id);
        call.enqueue(new Callback<HomeContentDto>() {
            @Override
            public void onResponse(Call<HomeContentDto> call, Response<HomeContentDto> response) {
                if(response.isSuccessful()){
                    HomeContentDto model = response.body();
                    listMovie = model.getData();
                    ListMovieByCategoryAdapter listMovieByCategoryAdapter = new ListMovieByCategoryAdapter(ListMovieByCategoryActivity.this,listMovie );
                    recyclerView.setAdapter(listMovieByCategoryAdapter);
                }
            }

            @Override
            public void onFailure(Call<HomeContentDto> call, Throwable t) {
                Log.d("TAG", "onFailure: ");
            }
        });
    }*/
}