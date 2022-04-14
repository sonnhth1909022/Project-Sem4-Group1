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

public class ListOtherMovieAdapter extends RecyclerView.Adapter<ListOtherMovieAdapter.ListOtherMovieAdapterViewHolder> {
    private Activity activity;
    private List<MovieDto> listMovieByCategory;

    public ListOtherMovieAdapter(Activity activity, List<MovieDto> listMovieByCategory) {
        this.activity = activity;
        this.listMovieByCategory = listMovieByCategory;
    }

    public void reloadData(List<MovieDto> listMovieByCategory) {
        this.listMovieByCategory = listMovieByCategory;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListOtherMovieAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = ((Activity) parent.getContext()).getLayoutInflater().inflate(R.layout.item_movie, parent, false);
        return new ListOtherMovieAdapter.ListOtherMovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListOtherMovieAdapterViewHolder holder, int position) {
        MovieDto model = listMovieByCategory.get(position);
        ListOtherMovieAdapter.ListOtherMovieAdapterViewHolder vh = (ListOtherMovieAdapter.ListOtherMovieAdapterViewHolder) holder;
        vh.tvNameMovie.setText(model.getName());
        Glide.with(activity).load(model.getThumbnail()).into(vh.ivImageMovie);
    }

    @Override
    public int getItemCount() {
        return listMovieByCategory.size();
    }

    public class ListOtherMovieAdapterViewHolder extends RecyclerView.ViewHolder{
        TextView tvNameMovie;
        ImageView ivImageMovie;
        public ListOtherMovieAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameMovie = itemView.findViewById(R.id.tvTitle);
            ivImageMovie = itemView.findViewById(R.id.ivCover);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MovieDto movie = listMovieByCategory.get(getAdapterPosition());
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
