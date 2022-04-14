package com.movieapp.movienavigation.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.movieapp.movienavigation.R;
import com.movieapp.movienavigation.model.Section;
import com.movieapp.movienavigation.response.MovieDto;

import java.util.List;

public class SectionAdapter  extends RecyclerView.Adapter{
    private Activity activity;
    private List<Section> listSection;

    public SectionAdapter(Activity activity, List<Section> listSection) {
        this.activity = activity;
        this.listSection = listSection;
    }
    public void reloadData(List<Section> listSection) {
        this.listSection = listSection;
        this.notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = activity.getLayoutInflater().inflate(R.layout.item_section, parent, false);
        SectionHolder holder = new SectionHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Section model = listSection.get(position);//lấy dữ liệu ra
        SectionHolder vh = (SectionHolder) holder;
        vh.tvSection.setText(model.getTitle());
        //cho du lieu vao recycle view bang 4 buoc
        //B1: Data source
        List<MovieDto> listMovies = model.getListMovies();
        //B2: Layout Manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        //B3
       MovieAdapter adapter = model.getMovieAdapter();
        adapter.setSectionTitle(model.getTitle());
        //B4
        vh.rvMovie.setLayoutManager(layoutManager);
        vh.rvMovie.setHasFixedSize(true);
        vh.rvMovie.setAdapter(adapter);
    }

    @Override
    public int getItemCount() { return listSection.size(); }
    public class SectionHolder extends RecyclerView.ViewHolder{
        TextView tvSection;
        RecyclerView rvMovie;
        public SectionHolder (@NonNull View view){
            super(view);
            tvSection=view.findViewById(R.id.tvTitle);
            rvMovie=view.findViewById(R.id.rvMovie);

        }
    }
}
