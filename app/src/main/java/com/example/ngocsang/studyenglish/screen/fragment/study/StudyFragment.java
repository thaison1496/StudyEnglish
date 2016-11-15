package com.example.ngocsang.studyenglish.screen.fragment.study;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.ngocsang.studyenglish.R;
import com.example.ngocsang.studyenglish.adapter.AdapterLesson;
import com.example.ngocsang.studyenglish.constant.TagFragment;
import com.example.ngocsang.studyenglish.model.ItemLesson;
import com.example.ngocsang.studyenglish.screen.fragment.base.BaseFragment;
import com.example.ngocsang.studyenglish.screen.fragment.study.Grammar.GrammarFragment;

import java.util.ArrayList;

/**
 * Created by Ngoc Sang on 10/5/2016.
 */
public class StudyFragment extends BaseFragment{
    private GridView lvLesson;
    private AdapterLesson adapterLesson;
    private ArrayList<ItemLesson> arrLesson;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView=inflater.inflate(R.layout.fragment_study,container,false);
        return contentView;
    }

    @Override
    protected void findViews() {
        super.findViews();
        lvLesson=(GridView)contentView.findViewById(R.id.grid_list_lesson);

    }

    @Override
    protected void init() {
        super.init();
        arrLesson=new ArrayList<>();
        arrLesson.add(new ItemLesson(R.drawable.item_grammar,"Ngữ Pháp"));
        arrLesson.add(new ItemLesson(R.drawable.item_communicate,"Giao Tiếp"));
        arrLesson.add(new ItemLesson(R.drawable.item_listening,"Luyện Nghe"));
        arrLesson.add(new ItemLesson(R.drawable.item_speak,"Luyện Nói"));
        adapterLesson=new AdapterLesson(arrLesson,mActivity);
        lvLesson.setAdapter(adapterLesson);

    }

    @Override
    protected void declareClick() {
        super.declareClick();
        lvLesson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              startLesson(position);
            }
        });
    }
    private void startLesson(int positon)
    {
        switch (positon)
        {
            case 0:
                mActivity.replaceFullScreen(new GrammarFragment(),true, TagFragment.GRAMMAR_FRAGMENT);
                break;
        }
    }
}
