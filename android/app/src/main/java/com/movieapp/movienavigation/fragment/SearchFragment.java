package com.movieapp.movienavigation.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movieapp.movienavigation.DetailActivity;
import com.movieapp.movienavigation.MainActivity;
import com.movieapp.movienavigation.R;
import com.movieapp.movienavigation.adapter.SearchNameAdapter;
import com.movieapp.movienavigation.network.ApiManager;
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

public class SearchFragment extends Fragment  {
    List<MovieDto> listMovie = new ArrayList<>();
    RecyclerView rvNameSearch;
    private SearchNameAdapter searchNameAdapter;
    private Context mContext;
    private MainActivity mainActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root= inflater.inflate(R.layout.fragment_search, container, false);
        rvNameSearch = root.findViewById(R.id.resultSearch);

        mainActivity=(MainActivity)getActivity();
        mainActivity.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mainActivity = (MainActivity) getActivity();
                int idCusHome = mainActivity.id;
                ApiSearch(newText, idCusHome);
                return false;
            }
        });
       /* mainActivity.searchView.setOnCloseListener(new SearchView.OnCloseListener(){

            @Override
            public boolean onClose() {
                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);
                return true;
            }
        });*/
        return root;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setHasOptionsMenu(true);
    }

    private void ApiSearch(String text, int id) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiManager service = retrofit.create(ApiManager.class);
        service.getNameMovie(text, id).enqueue(new Callback<HomeContentDto>() {
            @Override
            public void onResponse(Call<HomeContentDto> call, Response<HomeContentDto> response) {
                Log.d("TAG", "onResponse: ");
                if (response.isSuccessful()) {
                    HomeContentDto model = response.body();
                    listMovie = model.getData();
                    //Log.d("TAG", "onResponse: "+listMovie);
                    SearchNameAdapter searchNameAdapter = new SearchNameAdapter(
                            getActivity(), listMovie);
                    //b3
                  /*  RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);*/
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
                    //b4

                    rvNameSearch.setLayoutManager(gridLayoutManager);
                    rvNameSearch.setHasFixedSize(true);
                    rvNameSearch.setAdapter(searchNameAdapter);
                }

            }

            @Override
            public void onFailure(Call<HomeContentDto> call, Throwable t) {
                Log.d("TAG", "onFailure: ");
                //  linearLayout.setVisibility(View.VISIBLE);
                //rvNameSearch.setVisibility(View.GONE);
            }
        });
    }

    //nhan du lieu click
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
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra("movie",event.movie);
        startActivity(intent);
    }
}