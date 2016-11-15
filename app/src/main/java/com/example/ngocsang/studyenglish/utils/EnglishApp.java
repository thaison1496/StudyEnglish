package com.example.ngocsang.studyenglish.utils;

import android.app.Application;
import android.content.Context;

import com.firebase.client.Firebase;

/**
 * Created by Ngoc Sang on 10/7/2016.
 */
public class EnglishApp extends Application{
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        EnglishApp.mContext = getApplicationContext();
        Firebase.setAndroidContext(this);
    }

    public static Context getAppContext() {
        return EnglishApp.mContext;
    }


}
