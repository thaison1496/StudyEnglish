package com.example.ngocsang.studyenglish.screen.fragment.study.words;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ngocsang.studyenglish.R;
import com.example.ngocsang.studyenglish.screen.fragment.base.BaseFragment;

/**
 * Created by Ngoc Sang on 11/26/2016.
 */

public class WordMySelf extends BaseFragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView=inflater.inflate(R.layout.screen_word_my_self,container,false);
        return contentView;
    }
}
