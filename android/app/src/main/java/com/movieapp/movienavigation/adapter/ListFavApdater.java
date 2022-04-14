package com.movieapp.movienavigation.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.movieapp.movienavigation.R;
import com.movieapp.movienavigation.activity.LoginActivity;
import com.movieapp.movienavigation.network.ApiManager;
import com.movieapp.movienavigation.response.Favorite;
import com.movieapp.movienavigation.response.MovieDto;
import com.movieapp.movienavigation.response.User;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListFavApdater extends RecyclerView.Adapter {


    private Activity activity;
    private List<MovieDto> listMovie;
    private Context context;
    LoginActivity loginActivity;
    MovieDto movieDto;

    public ListFavApdater(Activity activity, List<MovieDto> listMovie) {
        this.activity = activity;
        this.listMovie = listMovie;
    }

    private User user;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = ((Activity) parent.getContext()).getLayoutInflater().inflate(R.layout.item_favorite, parent, false);
        ListFavApdater.FavHolder holder = new ListFavApdater.FavHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MovieDto model = listMovie.get(position);
        ListFavApdater.FavHolder vh = (ListFavApdater.FavHolder) holder;
        vh.tvTitle.setText(model.getName());
        Glide.with(activity).load(model.getThumbnail()).into(vh.ivCover);
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
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                } else {
                    String message = "Delete To Favorite Failed";
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Favorite> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return listMovie == null ? 0 : listMovie.size();

    }

    public class FavHolder extends RecyclerView.ViewHolder {
        ImageView ivCover;
        TextView tvTitle;
        Button btnDisFav;

        public FavHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.favTextView);
            ivCover = itemView.findViewById(R.id.favImageView);
            btnDisFav = itemView.findViewById(R.id.favBtn);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MovieDto movie = listMovie.get(getAdapterPosition());
                    EventBus.getDefault().post(new MovieAdapter.ClickItemMovie(movie.getName(),getAdapterPosition(),movie));
                }
            });
        }
    }
}
