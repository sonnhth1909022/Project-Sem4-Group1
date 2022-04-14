/*
package com.example.movienavigation.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movienavigation.R;
import com.example.movienavigation.VideoFolder;
import com.example.movienavigation.entity.YouTubeVideos;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter {
    List<YouTubeVideos> youtubeVideoList;
    MediaController mediaController;
    public VideoAdapter() {
    }

    public VideoAdapter(List<YouTubeVideos> youtubeVideoList) {
        this.youtubeVideoList = youtubeVideoList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext()).inflate(R.layout.video, parent, false);
        VideoViewHolder holder = new VideoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        holder..loadData( youtubeVideoList.get(position).getVideoUrl(), "text/html" , "utf-8" );
        YouTubeVideos model = youtubeVideoList.get(position);
        VideoViewHolder vh = (VideoViewHolder) holder;
        String vidAddress = model.getVideoUrl();

        vh.buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                */
/*Intent intent = new Intent(vh.itemView.getContext(), VideoFolder.class);
                vh.itemView.getContext().startActivity(intent);*//*

                Uri vidUri = Uri.parse(vidAddress);
                vh.videoWeb.setVideoURI(vidUri);
                mediaController = new MediaController(vh.itemView.getContext());
                mediaController.setAnchorView(vh.videoWeb);
                // sets the media player to the videoView
                mediaController.setMediaPlayer(vh.videoWeb);

                // sets the media controller to the videoView
                vh.videoWeb.setMediaController(mediaController);
                vh.videoWeb.start();
            }
        });

    }

    @Override
    public int getItemCount() {
        return youtubeVideoList.size();
    }
    public class VideoViewHolder extends RecyclerView.ViewHolder {

        VideoView videoWeb;
        Button buttonPlay;

        public VideoViewHolder(View itemView) {
            super(itemView);

            videoWeb = (VideoView) itemView.findViewById(R.id.videoView);
            buttonPlay =(Button)  itemView.findViewById(R.id.playVideo);

        }
    }

}
*/
