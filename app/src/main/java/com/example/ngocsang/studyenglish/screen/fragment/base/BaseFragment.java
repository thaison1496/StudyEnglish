package com.example.ngocsang.studyenglish.screen.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ngocsang.studyenglish.screen.activity.MainActivity;

/**
 * Created by Ngoc Sang on 10/4/2016.
 */
public class BaseFragment extends Fragment{
    protected View contentView;
    protected MainActivity mActivity;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof MainActivity)
        {
            mActivity=(MainActivity)context;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unRegis();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews();
        init();
        declareClick();
        onLoad();
    }
    protected void findViews()
    {

    }
    protected void declareClick()
    {

    }
    protected void init()
    {

    }
    protected void onLoad()
    {
        onLoadDynamicData();
    }
    public void onLoadDynamicData()
    {
           regis();
        setDefaultScreen();
        setUpScreen();
    }
    protected void setDefaultScreen()
    {

    }
    protected void setUpScreen()
    {

    }
    protected void regis()
    {

    }
    private void unRegis()
    {

    }

}
