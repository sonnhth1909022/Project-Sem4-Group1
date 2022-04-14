package com.movieapp.movienavigation.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.movieapp.movienavigation.MainActivity;
import com.movieapp.movienavigation.R;
import com.movieapp.movienavigation.activity.LoginActivity;
import com.movieapp.movienavigation.adapter.MyProductAdapter;
import com.movieapp.movienavigation.network.ApiManager;
import com.movieapp.movienavigation.response.BaseResponseDto;
import com.movieapp.movienavigation.response.Notify;
import com.movieapp.movienavigation.response.TransactionResDto;
import com.movieapp.movienavigation.ulti.BillingClientSetup;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SubscriptionFragment extends Fragment implements PurchasesUpdatedListener {

    BillingClient billingClient;
    AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener;
    RecyclerView recyclerView;
    TextView txtPremium,txtExpireDate, txtValidUntil;
    MainActivity activity;
    public int id;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_subscription, container, false);
        activity = (MainActivity) getActivity();
        id = activity.id;
        setupBillingClient();
        initSub(root);
        return root;
    }

    private void initSub(View root) {
        txtPremium = root.findViewById(R.id.txt_premium);
        txtValidUntil = root.findViewById(R.id.tvValidUntil);
        txtExpireDate = root.findViewById(R.id.txt_expire_date);
        recyclerView = root.findViewById(R.id.recycler_product);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),layoutManager.getOrientation()));
    }


    private void setupBillingClient() {
        acknowledgePurchaseResponseListener = new AcknowledgePurchaseResponseListener() {
            @Override
            public void onAcknowledgePurchaseResponse(@NonNull BillingResult billingResult) {
                if(billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK){
                    txtPremium.setVisibility(View.VISIBLE);
                    txtValidUntil.setVisibility(View.VISIBLE);
                    txtExpireDate.setVisibility(View.VISIBLE);
                }
            }
        };

        billingClient = BillingClientSetup.getInstance(getActivity(),this);
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingServiceDisconnected() {
            }

            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                if(billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK){
                    List<Purchase> purchases = billingClient.queryPurchases(BillingClient.SkuType.SUBS)
                            .getPurchasesList();
                    if(purchases.size() > 0) {
                        recyclerView.setVisibility(View.GONE);
                        for (Purchase purchase: purchases){
                            handleItemAlreadyPurchased(purchase);
                        }
                    }
                    else {
                        txtPremium.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        txtValidUntil.setVisibility(View.GONE);
                        txtExpireDate.setVisibility(View.GONE);
                        loadAllSubscribePackages();
                    }
                }else {
                    Toast.makeText(getActivity(),"Error code: " +
                            billingResult.getResponseCode(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }




    private void ApiVip(int id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiManager service = retrofit.create(ApiManager.class);
        service.updateAccountVip(id).enqueue(new Callback<Notify>() {
            @Override
            public void onResponse(Call<Notify> call, Response<Notify> response) {
                Log.d("TAG", "onResponse: "+ response);
                if(response.isSuccessful()){
                    Notify notify = response.body();
                    Toast.makeText(getActivity(),notify.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Notify> call, Throwable t) {
                Toast.makeText(getActivity(),"fail",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadAllSubscribePackages() {
        if(billingClient.isReady()){
            SkuDetailsParams params = SkuDetailsParams.newBuilder()
                    .setSkusList(Arrays.asList("monthly_subscription"))
                    .setType(BillingClient.SkuType.SUBS)
                    .build();
            billingClient.querySkuDetailsAsync(params, new SkuDetailsResponseListener() {
                @Override
                public void onSkuDetailsResponse(@NonNull BillingResult billingResult, @Nullable List<SkuDetails> list) {
                    if(billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK){
                        MyProductAdapter adapter = new MyProductAdapter((AppCompatActivity) getActivity(),list,billingClient);
                        recyclerView.setAdapter(adapter);
                    }
                    else {
                        Toast.makeText(getActivity(),"Error: "+billingResult.getResponseCode(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else {
            Toast.makeText(getActivity(),"Billing client not ready!", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleItemAlreadyPurchased(Purchase purchase) {
        if(purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED){
            if(!purchase.isAcknowledged()){
                AcknowledgePurchaseParams acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
                        .setPurchaseToken(purchase.getPurchaseToken())
                        .build();
                billingClient.acknowledgePurchase(acknowledgePurchaseParams,acknowledgePurchaseResponseListener);
            }
            else {
                getDateType(id);
                recyclerView.setVisibility(View.GONE);
                txtPremium.setVisibility(View.VISIBLE);
                txtPremium.setText("You have Premium Access. We hope you enjoy our service!");
                txtValidUntil.setVisibility(View.VISIBLE);
                txtExpireDate.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> list) {
        if(billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK
                && list != null)
        {
            for(Purchase purchase: list){
                handleItemAlreadyPurchased(purchase);
            }
            ApiVip(id);
            recyclerView.setVisibility(View.GONE);
            txtPremium.setVisibility(View.VISIBLE);
            txtPremium.setText("You have Premium Access. We hope you enjoy our service!");
            txtValidUntil.setVisibility(View.VISIBLE);
            txtExpireDate.setVisibility(View.VISIBLE);
            getDateType(id);
        }
    }


    private void getDateType (int id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiManager service = retrofit.create(ApiManager.class);
        service.getSubscriptionDate(id).enqueue(new Callback<TransactionResDto>() {
            @Override
            public void onResponse(Call<TransactionResDto> call, Response<TransactionResDto> response) {
                Log.d("TAG", "onResponse: "+response);
                TransactionResDto transactionResDto = response.body();
                txtExpireDate.setText(transactionResDto.getValidUntil());
               // passDataFromMainActivity.passData(transactionResDto);

            }

            @Override
            public void onFailure(Call<TransactionResDto> call, Throwable t) {

            }
        });

    }
}