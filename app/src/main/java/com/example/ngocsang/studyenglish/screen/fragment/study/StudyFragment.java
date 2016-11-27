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
import com.example.ngocsang.studyenglish.screen.fragment.study.listen.ScreenListenFragment;
import com.example.ngocsang.studyenglish.screen.fragment.study.speak.ScreenSpeakFragment;
import com.example.ngocsang.studyenglish.screen.fragment.study.words.StudyWordFragment;

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
        arrLesson.add(new ItemLesson(R.drawable.item_grammar,"Học ngữ pháp",R.color.Blue_Grey));
        arrLesson.add(new ItemLesson(R.drawable.item_communicate,"Giao tiếp theo chủ đề",R.color.Light_Green));
        arrLesson.add(new ItemLesson(R.drawable.item_listening,"Luyện nghe",R.color.Light_Blue));
        arrLesson.add(new ItemLesson(R.drawable.item_speak,"Từ vựng",R.color.Deep_Orange));
        arrLesson.add(new ItemLesson(R.drawable.conversation,"Luyện nói",R.color.color_pink));
        arrLesson.add(new ItemLesson(R.drawable.tuvung,"Từ Điển",R.color.color_lime));





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
            case 1:
                break;
            case 2:
                mActivity.replaceFullScreen(new ScreenListenFragment(),true, TagFragment.SCREEN_LISTEN);
                break;
            case 3:
                mActivity.replaceFullScreen(new StudyWordFragment(),true, TagFragment.SCRENN_WORD);
                break;
            case 4:
                mActivity.replaceFullScreen(new ScreenSpeakFragment(),true, TagFragment.SCREEN_SPEAK);
                break;
            case 5:
                break;

        }
    }
}
