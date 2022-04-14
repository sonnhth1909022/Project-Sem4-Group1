package com.movieapp.movienavigation.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.movieapp.movienavigation.R;
import com.movieapp.movienavigation.response.MovieDto;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class SearchNameAdapter extends RecyclerView.Adapter {

    private Activity activity;
    private List<MovieDto> listSearchMovie;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SearchNameAdapter(Activity activity, List<MovieDto> listSearchMovie) {
        this.activity = activity;
        this.listSearchMovie = listSearchMovie;
    }
    public void reloadData(List<MovieDto> list) {
        this.listSearchMovie = list;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = ((Activity)parent.getContext()).getLayoutInflater().inflate(R.layout.item_search,parent,false);
        SearchHolder holder = new SearchHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MovieDto model = listSearchMovie.get(position);
        SearchHolder vh = (SearchHolder) holder;
        vh.tvSearchName.setText(model.getName());
        Glide.with(activity).load(model.getThumbnail()).into(vh.ivCoverSearch);


    }

    @Override
    public int getItemCount() {
        return  listSearchMovie.size();
    }
    public class SearchHolder extends RecyclerView.ViewHolder {

        TextView tvSearchName;
        ImageView ivCoverSearch;
        public SearchHolder(@NonNull View itemView) {
            super(itemView);
            tvSearchName =itemView.findViewById(R.id.tvTitleSearch);
            ivCoverSearch =itemView.findViewById(R.id.ivCoverSearch);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MovieDto movie = listSearchMovie.get(getAdapterPosition());
                    EventBus.getDefault().post(new SearchNameAdapter.ClickItemMovieSearch(movie.getName(),getAdapterPosition(),movie));
                }
            });
        }

    }
    public static class ClickItemMovieSearch {
        public String message;
        public int position;
        public MovieDto movie;

        public ClickItemMovieSearch(String message, int position, MovieDto movie) {
            this.message = message;
            this.position = position;
            this.movie = movie;
        }
    }
}
