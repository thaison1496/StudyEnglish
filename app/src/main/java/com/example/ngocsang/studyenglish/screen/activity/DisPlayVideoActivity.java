package com.example.ngocsang.studyenglish.screen.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.Window;
import android.view.WindowManager;

import com.example.ngocsang.studyenglish.R;
import com.example.ngocsang.studyenglish.screen.fragment.study.listen.ApiKey;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by Ngoc Sang on 11/22/2016.
 */

public class DisPlayVideoActivity extends YouTubeBaseActivity{
    private YouTubePlayerView youTubePlayerView;
    private String id="";
    private YouTubePlayer.OnInitializedListener initializedListener;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = this.getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.color_black));
        setContentView(R.layout.display_video_screen);
        id=getIntent().getStringExtra("idVideo");
        youTubePlayerView=(YouTubePlayerView)findViewById(R.id.youtube_display);
        initializedListener=new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                  youTubePlayer.loadVideo(id);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        if(id!="")
        {
            youTubePlayerView.initialize(ApiKey.YOUTUBE_API_KEY,initializedListener);
        }


    }
}
