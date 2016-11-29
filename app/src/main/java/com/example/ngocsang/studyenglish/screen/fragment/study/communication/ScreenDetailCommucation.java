package com.example.ngocsang.studyenglish.screen.fragment.study.communication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ngocsang.studyenglish.R;
import com.example.ngocsang.studyenglish.adapter.WordAdapter;
import com.example.ngocsang.studyenglish.database.DataBaseManager;
import com.example.ngocsang.studyenglish.model.ItemWord;
import com.example.ngocsang.studyenglish.screen.fragment.base.BaseFullScreenFragment;
import com.example.ngocsang.studyenglish.screen.fragment.study.words.ScreenPractiveWord;

import java.util.ArrayList;

/**
 * Created by Ngoc Sang on 11/29/2016.
 */

public class ScreenDetailCommucation extends BaseFullScreenFragment{
    private ListView lvWord;
    private ArrayList<ItemWord> arr;
    private int idTopic;
    private WordAdapter wordAdapter;
    private String titleScreen;
    public void setTitleScreen(String titleScreen) {
        this.titleScreen = titleScreen;
    }

    private DataBaseManager dataBaseManager;

    public void setIdTopic(int idTopic) {
        this.idTopic = idTopic;
    }
    @Override
    protected void addView(LayoutInflater inflater, ViewGroup container) {
        contentView=inflater.inflate(R.layout.screen_detail_communication,container,false);
        containerView.addView(contentView);
    }

    @Override
    protected void declareClick() {
        super.declareClick();
        lvWord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ScreenPractiveWord practiveWord=new ScreenPractiveWord();
                practiveWord.setPositionDefault(position);
                practiveWord.setArrayList(arr);
                mActivity.replaceFullScreen(practiveWord,true,"practive");
            }
        });
    }
    @Override
    protected void setUpScreen() {
        super.setUpScreen();
        setTitle(titleScreen);
    }
    @Override
    protected void init() {
        super.init();
        dataBaseManager=new DataBaseManager(mActivity);

        arr=new ArrayList<>();
        arr.addAll(dataBaseManager.getCommunicationOrderTopic(idTopic));
        wordAdapter=new WordAdapter(arr,mActivity);
        lvWord.setAdapter(wordAdapter);

    }

    @Override
    protected void findViews() {
        super.findViews();
        lvWord=(ListView)contentView.findViewById(R.id.lv_communication);
    }

}
