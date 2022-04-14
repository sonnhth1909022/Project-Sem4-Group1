package com.movieapp.movienavigation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.movieapp.movienavigation.DetailActivity;
import com.movieapp.movienavigation.MainActivity;
import com.movieapp.movienavigation.R;
import com.movieapp.movienavigation.adapter.ListCategoryAdapter;
import com.movieapp.movienavigation.adapter.ListMovieByCategoryAdapter;
import com.movieapp.movienavigation.event.SaveID;
import com.movieapp.movienavigation.network.ApiManager;
import com.movieapp.movienavigation.response.Category;
import com.movieapp.movienavigation.response.HomeContentDto;
import com.movieapp.movienavigation.response.MovieDto;

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

public class ListMovieByCategoryDetailActivity extends AppCompatActivity  {
    RecyclerView recyclerView;
    List<MovieDto> listMovie = new ArrayList<>();
    RecyclerView recyclerViewCategory,recyclerViewListMovie;
    List<Category> categoryList = new ArrayList<>();
    MainActivity mainActivity;
    SaveID saveID = new SaveID();
    int idCus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movie_by_category_detail);
        Intent intent = getIntent();
        String newText= intent.getStringExtra("nameCategory");
        idCus=saveID.getId(ListMovieByCategoryDetailActivity.this);
        initData(newText,idCus);
    }
    private void initData(String text, int id) {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView = findViewById(R.id.rvMovieByCategoryDetail);
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
                    ListMovieByCategoryAdapter listMovieByCategoryAdapter = new ListMovieByCategoryAdapter(ListMovieByCategoryDetailActivity.this,listMovie );
                    recyclerView.setAdapter(listMovieByCategoryAdapter);
                }
            }

            @Override
            public void onFailure(Call<HomeContentDto> call, Throwable t) {
                Log.d("TAG", "onFailure: ");
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
    public void onMessageEvent(ListMovieByCategoryAdapter.ClickItemMovie event) {
        // Toast.makeText(getContext(),event.movie.getName(),Toast.LENGTH_LONG).show();
        Intent intent = new Intent(ListMovieByCategoryDetailActivity.this, DetailActivity.class);
        intent.putExtra("movie",event.movie);
        startActivity(intent);


    }

}