package com.movieapp.movienavigation.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movieapp.movienavigation.MainActivity;
import com.movieapp.movienavigation.R;
import com.movieapp.movienavigation.activity.ListMovieByCategoryActivity;
import com.movieapp.movienavigation.adapter.ListMovieByCategoryAdapter;
import com.movieapp.movienavigation.event.SaveID;
import com.movieapp.movienavigation.network.ApiManager;
import com.movieapp.movienavigation.response.Category;
import com.movieapp.movienavigation.response.HomeContentDto;
import com.movieapp.movienavigation.response.MovieDto;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieCategoryFragment extends Fragment {
    RecyclerView recyclerView;
    List<MovieDto> listMovie = new ArrayList<>();
    RecyclerView recyclerViewCategory,recyclerViewListMovie;
    List<Category> categoryList = new ArrayList<>();
    MainActivity mainActivity;
    SaveID saveID = new SaveID();
    int idCus;
    String nameCategory;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_movie_category, container, false);
        Bundle bundle = this.getArguments();

        if(bundle != null){
             nameCategory = getArguments().getString("key");
        }
        idCus = saveID.getId(getActivity());
        initData(root,nameCategory, idCus);
        return root;
    }
    private void initData(View root,String text, int id) {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView = root.findViewById(R.id.rvListMovie1);
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
                    ListMovieByCategoryAdapter listMovieByCategoryAdapter = new ListMovieByCategoryAdapter(getActivity(),listMovie );
                    recyclerView.setAdapter(listMovieByCategoryAdapter);
                }
            }

            @Override
            public void onFailure(Call<HomeContentDto> call, Throwable t) {
                Log.d("TAG", "onFailure: ");
            }
        });
    }
}