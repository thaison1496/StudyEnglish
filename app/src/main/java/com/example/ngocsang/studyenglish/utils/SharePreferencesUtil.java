package com.example.ngocsang.studyenglish.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.ngocsang.studyenglish.constant.Constant;

/**
 * Created by Ngoc Sang on 10/7/2016.
 */
public class SharePreferencesUtil {
    private static SharedPreferences sharedPreferences;
    private SharePreferencesUtil()
    {

    }
    public static SharedPreferences getInstance()
    {
        if(sharedPreferences==null)
        {
           sharedPreferences=  EnglishApp.getAppContext().getSharedPreferences(Constant.ENGLISH, Activity.MODE_PRIVATE);
        }
        return sharedPreferences;
    }
    private static SharedPreferences.Editor getEdittor()
    {
        return getInstance().edit();
    }
    public static String getString(String key)
    {
        return getInstance().getString(key,"");
    }
    public static int getInt(String key)
    {
        return getInstance().getInt(key,0);
    }
    public static boolean getBoolean(String key)
    {
        return getInstance().getBoolean(key,false);
    }
    public static long getLong(String key)
    {
        return getInstance().getLong(key,0);
    }
    public static void putString(String key,String value)
    {
        getEdittor().putString(key,value).commit();
    }
    public static void putInt(String key,int value)
    {
        getEdittor().putInt(key,value).commit();
    }
    public static void putBoolean(String key,boolean value)
    {
        getEdittor().putBoolean(key,value).commit();
    }
    public static void putLong(String key,long value)
    {
        getEdittor().putLong(key,value).commit();
    }
}
