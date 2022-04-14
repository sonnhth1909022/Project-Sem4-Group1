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

public class ListMovieByCategoryAdapter extends RecyclerView.Adapter<ListMovieByCategoryAdapter.ListMovieByCategoryViewHolder> {
    private Activity activity;
    private List<MovieDto> listMovieByCategory;

    public ListMovieByCategoryAdapter(Activity activity, List<MovieDto> listMovieByCategory) {
        this.activity = activity;
        this.listMovieByCategory = listMovieByCategory;
    }

    public void reloadData(List<MovieDto> listMovieByCategory) {
        this.listMovieByCategory = listMovieByCategory;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListMovieByCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = ((Activity) parent.getContext()).getLayoutInflater().inflate(R.layout.item_movie_by_category, parent, false);
        return new ListMovieByCategoryAdapter.ListMovieByCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMovieByCategoryViewHolder holder, int position) {
        MovieDto model = listMovieByCategory.get(position);
        ListMovieByCategoryAdapter.ListMovieByCategoryViewHolder vh = (ListMovieByCategoryAdapter.ListMovieByCategoryViewHolder) holder;
        vh.tvNameMovie.setText(model.getName());
        Glide.with(activity).load(model.getThumbnail()).into(vh.ivImageMovie);
    }

    @Override
    public int getItemCount() {
        return listMovieByCategory.size();
    }

    public class ListMovieByCategoryViewHolder extends RecyclerView.ViewHolder{
        TextView tvNameMovie;
        ImageView ivImageMovie;
//        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        itemRoot.setLayoutParams(params);
        public ListMovieByCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameMovie = itemView.findViewById(R.id.tvNameMovie);
            ivImageMovie = itemView.findViewById(R.id.ivImageMovie);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MovieDto movie = listMovieByCategory.get(getAdapterPosition());
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
