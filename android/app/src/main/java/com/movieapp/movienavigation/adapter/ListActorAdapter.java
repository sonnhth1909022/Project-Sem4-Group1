package com.movieapp.movienavigation.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.movieapp.movienavigation.R;
import com.movieapp.movienavigation.response.Cast;

import java.util.ArrayList;
import java.util.List;

public class ListActorAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<String> listActorName;

    public ListActorAdapter(Activity activity, List<String> listActorName) {
        this.activity = activity;
        this.listActorName = listActorName;
    }



    public interface OnItemClick {
        void onClickCast (String name);
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
        View itemView = ((Activity)parent.getContext()).getLayoutInflater().inflate(R.layout.item_actor,parent,false);
        ListActorAdapter.ActorHolder holder = new ListActorAdapter.ActorHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String name = listActorName.get(position);
        ListActorAdapter.ActorHolder vh = (ListActorAdapter.ActorHolder) holder;
        vh.tvNameActor.setText(name);
        vh.tvNameActor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onClickCast(name);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listActorName.size();
    }
    public class ActorHolder extends RecyclerView.ViewHolder {
        TextView tvNameActor;
        public ActorHolder(@NonNull View itemView) {
            super(itemView);
            tvNameActor =itemView.findViewById(R.id.list_actor);
        }

    }
}
