package com.movieapp.movienavigation.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.movieapp.movienavigation.DetailActivity;
import com.movieapp.movienavigation.MainActivity;
import com.movieapp.movienavigation.R;
import com.movieapp.movienavigation.activity.ListMovieByCastActivity;
import com.movieapp.movienavigation.activity.ListMovieByCategoryActivity;
import com.movieapp.movienavigation.adapter.CategoryAdapter;
import com.movieapp.movienavigation.adapter.MovieAdapter;
import com.movieapp.movienavigation.adapter.SectionAdapter;
//import com.example.movienavigation.adapter.VideoAdapter;
import com.movieapp.movienavigation.entity.YouTubeVideos;
import com.movieapp.movienavigation.model.Section;
import com.movieapp.movienavigation.network.ApiManager;
import com.movieapp.movienavigation.response.BaseResponseDto;
import com.movieapp.movienavigation.response.Category;
import com.movieapp.movienavigation.response.HomeContentDto;
import com.movieapp.movienavigation.response.MovieDto;
import com.movieapp.movienavigation.response.Notify;
import com.movieapp.movienavigation.response.TransactionResDto;
import com.movieapp.movienavigation.ulti.BillingClientSetup;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

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

public class HomeFragment extends Fragment implements PurchasesUpdatedListener {

    BillingClient billingClient;
    AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener;

    RecyclerView recyclerView;
    List<YouTubeVideos> youtubeVideos = new ArrayList<>();
    CarouselView carouselView;
    List<MovieDto> listMovie = new ArrayList<>();
    List<Section> listSections= new ArrayList<>();
    SubscriptionFragment subscriptionFragment;

    RecyclerView recyclerViewCategory;
    List<Category> categoryList = new ArrayList<>();
    private MainActivity mainActivity;
    private TextView tvCategory;
    private int id;

    int[] sampleImages = {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4};
    private MovieAdapter movieAdapter;
    private SectionAdapter sectionAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home,container,false);
        mainActivity = (MainActivity) getActivity();
        id = mainActivity.id;
        initView(root);

        silder(root);
        setupBillingClientShared();

        //initCategory(root);
        return root;

    }
    public void setupBillingClientShared(){
        billingClient = BillingClientSetup.getInstance(getActivity(),this);
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                if(billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK){
                    List<Purchase> purchases = billingClient.queryPurchases(BillingClient.SkuType.SUBS)
                            .getPurchasesList();
                    if(purchases.size() == 0) {
                        ApiNormal(id);
                    }
                }
            }

            @Override
            public void onBillingServiceDisconnected() {

            }
        });
    }

    public void ApiNormal(int id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiManager service = retrofit.create(ApiManager.class);
        service.updateAccountNormal(id).enqueue(new Callback<Notify>() {
            @Override
            public void onResponse(Call<Notify> call, Response<Notify> response) {
                Log.d("TAG", "onResponse: "+ response);
                if(response.isSuccessful()){
                    Notify notify = response.body();
                }
            }

            @Override
            public void onFailure(Call<Notify> call, Throwable t) {
                Toast.makeText(getActivity(),"fail",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView(View root) {
        initData(id);
        //b2 apdater
        sectionAdapter = new SectionAdapter(getActivity(),listSections);
          //b3
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
       //b4
       recyclerView =root.findViewById(R.id.rvHome);
       recyclerView.setLayoutManager(layoutManager);
       recyclerView.setHasFixedSize(true);
       recyclerView.setAdapter(sectionAdapter);
    }

    //slider
    private void silder(View root) {
        carouselView = root.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);

        ImageListener imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(sampleImages[position]);
            }
        };
        carouselView.setImageListener(imageListener);
        //su kien click

    }
    //goi api
    public void initData(int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiManager service = retrofit.create(ApiManager.class);
        service.apiHome(id).enqueue(new Callback<BaseResponseDto<HomeContentDto>>() {
            @Override
            public void onResponse(Call<BaseResponseDto<HomeContentDto>> call, Response<BaseResponseDto<HomeContentDto>> response) {
                Log.d("TAG", "onResponse: ");
                BaseResponseDto<HomeContentDto> model = response.body();
               //HomeContentDto homeContentDto=model.getData();
//               listMovie=homeContentDto.getListTrending();
               /* listMovie = model.getData().getListTrending();
                movieAdapter.reloadData(listMovie);*/
                
                //lay tung section ra hien thi
                listSections.add( new Section("Premium",model.getData().getListPremium(),
                        new MovieAdapter(getActivity(),model.getData().getListPremium())));

                listSections.add( new Section("Trending",model.getData().getListTrending(),
                        new MovieAdapter(getActivity(),model.getData().getListTrending())));

                listSections.add( new Section("Hot",model.getData().getListHot(),
                        new MovieAdapter(getActivity(),model.getData().getListHot())));

                listSections.add( new Section("Action",model.getData().getListAction(),
                        new MovieAdapter(getActivity(),model.getData().getListAction())));

                listSections.add(new Section("Romance", model.getData().getListRomance(),
                        new MovieAdapter(getActivity(),model.getData().getListRomance())));

                listSections.add(new Section("Television", model.getData().getListTelevision(),
                        new MovieAdapter(getActivity(),model.getData().getListTelevision())));

                sectionAdapter.reloadData(listSections);
            }

            @Override
            public void onFailure(Call<BaseResponseDto<HomeContentDto>> call, Throwable t) {
                Log.d("TAG", "onFailure: ");
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
    public void onMessageEvent(MovieAdapter.ClickItemMovie event) {
       // Toast.makeText(getContext(),event.movie.getName(),Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getContext(), DetailActivity.class);
            intent.putExtra("movie", event.movie);
            startActivity(intent);

    }
   /* @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MovieAdapter.ClickItemMovie event) {
        // Toast.makeText(getContext(),event.movie.getName(),Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getContext(), ListMovieByCastActivity.class);
        intent.putExtra("movie", event.movie);
        startActivity(intent);

    }*/

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(CategoryAdapter.ClickItemMovie event) {
        // Toast.makeText(getContext(),event.movie.getName(),Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getContext(), ListMovieByCategoryActivity.class);
        intent.putExtra("category", event.category);
        startActivity(intent);
    }

    @Override
    public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> list) {

    }

}
