package com.movieapp.movienavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class PlayVideo extends AppCompatActivity {
    VideoView playVideo;
    MediaController mediaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);
        playVideo=findViewById(R.id.videoView);
        Intent intent = getIntent();
        Uri videoUri = Uri.parse(intent.getStringExtra("url"));
        mediaController = new MediaController(this);
        mediaController.setAnchorView(playVideo);
        // sets the media player to the videoView
        mediaController.setMediaPlayer(playVideo);

        // sets the media controller to the videoView
        playVideo.setMediaController(mediaController);
        playVideo.setVideoURI(videoUri);
        playVideo.start();


    }
}