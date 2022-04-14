package com.movieapp.movienavigation.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.movieapp.movienavigation.R;
import com.movieapp.movienavigation.response.Category;
import com.movieapp.movienavigation.response.MovieDto;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class ListCategoryAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<String> listCategoryName;
    String nameCategory;
    public ListCategoryAdapter(Activity activity, List<String> listCategoryName) {
        this.activity = activity;
        this.listCategoryName = listCategoryName;
    }
    public interface OnItemClick {
        void onClickCategory (String value);
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
        View itemView = ((Activity) parent.getContext()).getLayoutInflater().inflate(R.layout.item_category, parent, false);
        ListCategoryAdapter.CategoryHolder holder = new ListCategoryAdapter.CategoryHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        nameCategory  = listCategoryName.get(position);
        ListCategoryAdapter.CategoryHolder vh = (ListCategoryAdapter.CategoryHolder) holder;
        vh.tvNameCategory.setText(nameCategory);
        vh.tvNameCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onClickCategory(nameCategory);
               // onItemClick.setOnItemClick(onItemClick);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listCategoryName.size();
    }

    public static class CategoryHolder extends RecyclerView.ViewHolder {
        ImageView ivCover;
        TextView tvNameCategory;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            tvNameCategory = itemView.findViewById(R.id.list_category);
        }

    }

}
