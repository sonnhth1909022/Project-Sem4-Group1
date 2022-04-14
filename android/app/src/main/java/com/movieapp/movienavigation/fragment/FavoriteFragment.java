package com.movieapp.movienavigation.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movieapp.movienavigation.DetailActivity;
import com.movieapp.movienavigation.MainActivity;
import com.movieapp.movienavigation.R;
import com.movieapp.movienavigation.adapter.ListFavApdater;
import com.movieapp.movienavigation.adapter.MovieAdapter;
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


public class FavoriteFragment extends Fragment {
    ListFavApdater listFavApdater;
    List<MovieDto> listMovieFav = new ArrayList<>();
    RecyclerView recyclerView;
    MainActivity mainActivity;
    public int id;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_favorite,container,false);
        recyclerView =root.findViewById(R.id.rvFavMovie);
        mainActivity = (MainActivity) getActivity();
        id = mainActivity.id;
        Api(id);
        return root;
    }
    private void Api(int id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiManager service = retrofit.create(ApiManager.class);
        service.TakeMovieFav(id).enqueue(new Callback<HomeContentDto>() {
            @Override
            public void onResponse(Call<HomeContentDto> call, Response<HomeContentDto> response) {
                Log.d("TAG", "onResponse: "+response);

                    HomeContentDto model = response.body();
                    listMovieFav = model.getData();

                    //Log.d("TAG", "onResponse: "+listMovie);
                    listFavApdater = new ListFavApdater(
                            getActivity(), listMovieFav);
                    //b3
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    //b4

                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(listFavApdater);


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
    public void onMessageEvent(MovieAdapter.ClickItemMovie event) {
        // Toast.makeText(getContext(),event.movie.getName(),Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra("movie",event.movie);
        startActivity(intent);
    }


}