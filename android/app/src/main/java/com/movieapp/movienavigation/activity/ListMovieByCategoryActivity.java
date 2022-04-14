package com.movieapp.movienavigation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.movieapp.movienavigation.DetailActivity;
import com.movieapp.movienavigation.MainActivity;
import com.movieapp.movienavigation.R;
import com.movieapp.movienavigation.adapter.CategoryAdapter;
import com.movieapp.movienavigation.adapter.ListMovieByCategoryAdapter;
import com.movieapp.movienavigation.event.SaveID;
import com.movieapp.movienavigation.network.ApiManager;
import com.movieapp.movienavigation.response.Category;
import com.movieapp.movienavigation.response.CategoryResponse;
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

public class ListMovieByCategoryActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_list_movie_by_category);

        Intent intent = getIntent();
        Category category = (Category) intent.getSerializableExtra("category");
        String newText = category.getName();

        idCus = saveID.getId(ListMovieByCategoryActivity.this);
        initData(newText, idCus);
    }

    private void initData(String text, int id) {

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
    }
    private void initCategory() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListMovieByCategoryActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategory =findViewById(R.id.rvCategoryList);
        recyclerViewCategory.setLayoutManager(layoutManager);
        recyclerViewCategory.setHasFixedSize(true);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiManager service = retrofit.create(ApiManager.class);
        Call<CategoryResponse> call = service.getAllCategory();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if(response.isSuccessful()){
                    CategoryResponse model = response.body();
                    categoryList = model.getData();
                    CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList, ListMovieByCategoryActivity.this);
                    recyclerViewCategory.setAdapter(categoryAdapter);
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
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
        Intent intent = new Intent(ListMovieByCategoryActivity.this, DetailActivity.class);
        intent.putExtra("movie",event.movie);
        startActivity(intent);


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(CategoryAdapter.ClickItemMovie event) {
        // Toast.makeText(getContext(),event.movie.getName(),Toast.LENGTH_LONG).show();
        Intent intent = new Intent(ListMovieByCategoryActivity.this, DetailActivity.class);
        intent.putExtra("category", event.category);
        startActivity(intent);
    }
}