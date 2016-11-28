package com.example.ngocsang.studyenglish.screen.fragment.study.words;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ngocsang.studyenglish.R;
import com.example.ngocsang.studyenglish.adapter.WordAdapter;
import com.example.ngocsang.studyenglish.database.DataBaseManager;
import com.example.ngocsang.studyenglish.model.ItemWord;
import com.example.ngocsang.studyenglish.screen.fragment.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by Ngoc Sang on 11/26/2016.
 */

public class WordMySelf extends BaseFragment{
    private ListView lvWord;
    private ArrayList<ItemWord> arr;
    private WordAdapter wordAdapter;
    private DataBaseManager dataBaseManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView=inflater.inflate(R.layout.screen_word_my_self,container,false);
        return contentView;
    }
    @Override
    protected void findViews() {
        super.findViews();
        lvWord=(ListView)contentView.findViewById(R.id.lv_word_my_list);
    }
    @Override
    protected void init() {
        super.init();
        dataBaseManager=new DataBaseManager(mActivity);

        arr=new ArrayList<>();
        arr.addAll(dataBaseManager.getWordFromMyList());
        wordAdapter=new WordAdapter(arr,mActivity);
        lvWord.setAdapter(wordAdapter);

    }
}
