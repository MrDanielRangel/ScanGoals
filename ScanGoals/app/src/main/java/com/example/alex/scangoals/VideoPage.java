package com.example.alex.scangoals;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoPage extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener {
    // google api key so they can watch youtube video
    private String GOOGLE_API_KEY = "AIzaSyAfMBf1d8pwfrhaSXBFJh8zhvsysOr1bFo";
    // grab the ket from the choice class
    public String YOUTUBE_VIDEO_ID = QRChoices.stuffToGrab.getString("videoKey12");//"kd45Q18ML-s";

    // on create these items
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_page);
        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        youTubePlayerView.initialize(GOOGLE_API_KEY, this);
    }
    // when the video initializes successfully
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        Toast.makeText(this, "Initialized Youtube PLayer successfully",Toast.LENGTH_LONG).show();
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);
        // play video
        if(!wasRestored){
            youTubePlayer.cueVideo(YOUTUBE_VIDEO_ID);
        }
    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        // if the video is playing send message
        @Override
        public void onPlaying() {
            Toast.makeText(VideoPage.this, "Good, video is playing ok", Toast.LENGTH_LONG).show();
        }
        // if the video was paused send message
        @Override
        public void onPaused() {
            Toast.makeText(VideoPage.this, "Video has paused", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onStopped() {

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };

    YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }
        // if the ad has started play message
        @Override
        public void onAdStarted() {
            Toast.makeText(VideoPage.this, "Click Ad now", Toast.LENGTH_LONG).show();
        }
        // if the video has started send message
        @Override
        public void onVideoStarted() {
            Toast.makeText(VideoPage.this, "Video has started", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onVideoEnded() {

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };
    // if the video faild to load send message
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Failed to Initialized Youtube PLayer",Toast.LENGTH_LONG).show();
    }

}
