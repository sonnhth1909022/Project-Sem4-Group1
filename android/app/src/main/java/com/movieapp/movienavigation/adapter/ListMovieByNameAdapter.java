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
import com.movieapp.movienavigation.response.Cast;
import com.movieapp.movienavigation.response.MovieDto;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListMovieByNameAdapter extends RecyclerView.Adapter{
    private Activity activity;
    private List<MovieDto> listCast;

    public ListMovieByNameAdapter(Activity activity, List<MovieDto> listCast) {
        this.activity = activity;
        this.listCast = listCast;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = ((Activity) parent.getContext()).getLayoutInflater().inflate(R.layout.item_list_movie_cast, parent, false);
        return new ListMovieByNameAdapter.ListMovieByNameyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MovieDto movieDto = listCast.get(position);
        ListMovieByNameAdapter.ListMovieByNameyViewHolder vh = (ListMovieByNameAdapter.ListMovieByNameyViewHolder) holder;
        ArrayList<Cast> casts = movieDto.getCasts();
       for (Cast element : casts){
           vh.tvNameCast.setText(element.getName());
           vh.tvDesCast.setText(element.getDescription());
           Glide.with(activity).load(element.getAvt()).into(vh.circleImageView);
       }
    }

    @Override
    public int getItemCount() {
        return listCast.size();
    }
    public class ListMovieByNameyViewHolder extends RecyclerView.ViewHolder{
        TextView tvNameCast,tvDesCast;
        CircleImageView circleImageView;
        //RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//  itemRoot.setLayoutParams(params);
        public ListMovieByNameyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameCast = itemView.findViewById(R.id.tvNameCast);
            tvDesCast = itemView.findViewById(R.id.tvDescCast);
            circleImageView = itemView.findViewById(R.id.ivAvt);

        }
    }
}
