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
import com.movieapp.movienavigation.response.Category;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    List<Category> categoryList;
    Activity activity;

    public CategoryAdapter(List<Category> categoryList, Activity activity) {
        this.categoryList = categoryList;
        this.activity = activity;
    }
  /* private  interface  clickItemCategory{
        void ClickItemCategory(String name);
   }
   private clickItemCategory clickItemCategory;

    public CategoryAdapter.clickItemCategory getClickItemCategory() {
        return clickItemCategory;
    }

    public void setClickItemCategory(CategoryAdapter.clickItemCategory clickItemCategory) {
        this.clickItemCategory = clickItemCategory;
    }*/

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = ((Activity) parent.getContext()).getLayoutInflater().inflate(R.layout.item_list_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);
        CategoryAdapter.CategoryViewHolder vh = (CategoryAdapter.CategoryViewHolder) holder;
        vh.tvNameCategory.setText(category.getName());
        Glide.with(activity).load(category.getAvt()).into(vh.ivAvt);
//        vh.ivAvt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                clickItemCategory.ClickItemCategory(category.getName());
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        TextView tvNameCategory;
        ImageView ivAvt;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameCategory = itemView.findViewById(R.id.tvNameCategory);
            ivAvt = itemView.findViewById(R.id.ivAvt);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Category category = categoryList.get(getAdapterPosition());
                    EventBus.getDefault().post(new ClickItemMovie(category.getName(),getAdapterPosition(),category));
                }
            });
        }
    }

    public static class ClickItemMovie {
        public String message;
        public int position;
        public Category category;

        public ClickItemMovie(String message, int position, Category category) {
            this.message = message;
            this.position = position;
            this.category = category;
        }

        public ClickItemMovie() {
        }
    }

}
