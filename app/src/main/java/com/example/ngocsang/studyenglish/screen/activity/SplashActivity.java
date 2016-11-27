package com.example.ngocsang.studyenglish.screen.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.ngocsang.studyenglish.R;
import com.example.ngocsang.studyenglish.constant.Constant;
import com.example.ngocsang.studyenglish.utils.SharePreferencesUtil;

/**
 * Created by Ngoc Sang on 10/7/2016.
 */
public class SplashActivity extends Activity{
    private int REQUEST_TIME_OUT=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.icon_anim_fade_in, R.anim.icon_anim_fade_out);
                    finish();

            }
        },REQUEST_TIME_OUT);
    }
}
