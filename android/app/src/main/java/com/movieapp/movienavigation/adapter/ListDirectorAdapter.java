package com.movieapp.movienavigation.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.movieapp.movienavigation.R;
import com.movieapp.movienavigation.response.MovieDto;

import java.util.List;

public class ListDirectorAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<String> listDirectorName;

    public ListDirectorAdapter(Activity activity, List<String> listDirectorName) {
        this.activity = activity;
        this.listDirectorName = listDirectorName;
    }
    public interface OnItemClick {
        void onClickDirector (String value);
    }
    private OnItemClick onItemClick;

    public OnItemClick getOnItemClick() {
        return onItemClick;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = ((Activity)parent.getContext()).getLayoutInflater().inflate(R.layout.item_director,parent,false);
        ListDirectorAdapter.DirectorHolder holder = new ListDirectorAdapter.DirectorHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String name = listDirectorName.get(position);
        ListDirectorAdapter.DirectorHolder vh = (ListDirectorAdapter.DirectorHolder) holder;
        vh.tvNameDirector.setText(name);
        vh.tvNameDirector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onClickDirector(name);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listDirectorName.size();
    }
    public class DirectorHolder extends RecyclerView.ViewHolder {
        ImageView ivCover;
        TextView tvNameDirector;
        public DirectorHolder(@NonNull View itemView) {
            super(itemView);
            tvNameDirector =itemView.findViewById(R.id.list_director);
        }

    }
    public static class ClickNameDriector {
        public String message;
        public int position;
        public MovieDto movie;

        public ClickNameDriector(String message, int position, MovieDto movie) {
            this.message = message;
            this.position = position;
            this.movie = movie;
        }
    }
}
