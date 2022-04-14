package com.movieapp.movienavigation.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.movieapp.movienavigation.R;
import com.movieapp.movienavigation.response.MovieDto;
import com.movieapp.movienavigation.response.User;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter {

    private  Activity activity;
    private List<MovieDto> listMovie;
    private String sectionTitle;
    private User user;

    public MovieAdapter(Activity activity, List<MovieDto> listMovie) {
        this.activity = activity;
        this.listMovie = listMovie;
    }


    public void reloadData(List<MovieDto> list) {
        this.listMovie = list;
        this.notifyDataSetChanged();
    }

    public void setSectionTitle(String sectionTitle) {
        this.sectionTitle = sectionTitle;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = null;
        if (sectionTitle.equalsIgnoreCase("Hot")) {
            itemView = ((Activity) parent.getContext()).getLayoutInflater().inflate(R.layout.item_movie_2, parent, false);
        } else if(sectionTitle.equalsIgnoreCase("Romance")){
            itemView = ((Activity) parent.getContext()).getLayoutInflater().inflate(R.layout.item_movie_2, parent, false);
        } else {
            itemView = ((Activity) parent.getContext()).getLayoutInflater().inflate(R.layout.item_movie, parent, false);
        }

        MovieHolder holder = new MovieHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MovieDto model = listMovie.get(position);
        MovieHolder vh = (MovieHolder) holder;
        vh.tvTitle.setText(model.getName());
        Glide.with(activity).load(model.getThumbnail()).into(vh.ivCover);
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }
    public class MovieHolder extends RecyclerView.ViewHolder {
        ImageView ivCover;
        TextView tvTitle;
        Button btnFav;
        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle =itemView.findViewById(R.id.tvTitle);
            ivCover =itemView.findViewById(R.id.ivCover);
            btnFav=itemView.findViewById(R.id.favBtn);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MovieDto movie = listMovie.get(getAdapterPosition());
                    EventBus.getDefault().post(new ClickItemMovie(movie.getName(),getAdapterPosition(),movie));
                }
            });

        }

    }
    public static class ClickItemMovie {
        public String message;
        public int position;
        public MovieDto movie;

        public ClickItemMovie(String message, int position, MovieDto movie) {
            this.message = message;
            this.position = position;
            this.movie = movie;
        }
    }
}
