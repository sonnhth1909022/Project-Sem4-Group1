package com.movieapp.movienavigation.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movieapp.movienavigation.DetailActivity;
import com.movieapp.movienavigation.MainActivity;
import com.movieapp.movienavigation.R;
import com.movieapp.movienavigation.activity.ListMovieByCategoryActivity;
import com.movieapp.movienavigation.adapter.CategoryAdapter;
import com.movieapp.movienavigation.adapter.ListMovieByCategoryAdapter;
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


public class LibraryFragment extends Fragment {

    RecyclerView recyclerViewCategory,recyclerViewListMovie;
    List<Category> categoryList = new ArrayList<>();
    List<MovieDto> listMovie1 = new ArrayList<>();
    MainActivity mainActivity;
    String nameCategory;

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root= inflater.inflate(R.layout.fragment_library, container, false);
        mainActivity= (MainActivity) getActivity();
        initCategory(root);
        ListMovie(root,mainActivity.id);
        return root;
    }
     private void initCategory(View root) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategory =root.findViewById(R.id.rvCategory);
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
                    CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList, getActivity());
                    recyclerViewCategory.setAdapter(categoryAdapter);

                  //  replaceFrament(new MovieCategoryFragment());

                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
            }
        });
    }
    private void ListMovie(View root,int id){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerViewListMovie =root.findViewById(R.id.rvListMovie);
        recyclerViewListMovie.setLayoutManager(gridLayoutManager);
        recyclerViewListMovie.setHasFixedSize(true);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiManager service = retrofit.create(ApiManager.class);
        Call<HomeContentDto> call = service.ListMovie(id);
        call.enqueue(new Callback<HomeContentDto>() {
            @Override
            public void onResponse(Call<HomeContentDto> call, Response<HomeContentDto> response) {
                if(response.isSuccessful()){
                    HomeContentDto model = response.body();
                    listMovie1=model.getData();
                    ListMovieByCategoryAdapter listMovieByCategoryAdapter = new ListMovieByCategoryAdapter(getActivity(),listMovie1);
                    recyclerViewListMovie.setAdapter(listMovieByCategoryAdapter);

                }
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
    public void onMessageEvent(ListMovieByCategoryAdapter.ClickItemMovie event) {
        // Toast.makeText(getContext(),event.movie.getName(),Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra("movie",event.movie);
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(CategoryAdapter.ClickItemMovie event) {
        // Toast.makeText(getContext(),event.movie.getName(),Toast.LENGTH_LONG).show();
        /*CatagoryFragment catagoryFragment = new CatagoryFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.rvListMovie,catagoryFragment);
        transaction.addToBackStack(null);
        transaction.commit();*/
        Intent intent = new Intent(getContext(), ListMovieByCategoryActivity.class);
        intent.putExtra("category", event.category);
        startActivity(intent);
       /* nameCategory = event.category.getName();
        Bundle bundle = new Bundle();
        bundle.putString("key",nameCategory); // Put anything what you want

        MovieCategoryFragment movieCategoryFragment = new MovieCategoryFragment();
        movieCategoryFragment.setArguments(bundle);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.rvListMovie, movieCategoryFragment)
                .commit();*/
    }
    private void replaceFrament(Fragment fragment){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.rvListMovie,fragment);
        ft.commit();
    }

}