package com.example.ngocsang.studyenglish.screen.fragment.study;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ngocsang.studyenglish.R;
import com.example.ngocsang.studyenglish.screen.fragment.BaseFragment;

/**
 * Created by Ngoc Sang on 10/5/2016.
 */
public class StudyFragment extends BaseFragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView=inflater.inflate(R.layout.fragment_study,container,false);
        return contentView;
    }
}
