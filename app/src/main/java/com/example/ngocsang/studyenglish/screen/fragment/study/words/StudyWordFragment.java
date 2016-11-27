package com.example.ngocsang.studyenglish.screen.fragment.study.words;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.ngocsang.studyenglish.R;
import com.example.ngocsang.studyenglish.adapter.ViewPagerAdapter;
import com.example.ngocsang.studyenglish.screen.fragment.base.BaseFullScreenFragment;
import com.example.ngocsang.studyenglish.screen.fragment.study.listen.PageTabFragment;

import java.util.ArrayList;

import info.hoang8f.android.segmented.SegmentedGroup;

/**
 * Created by Ngoc Sang on 11/26/2016.
 */

public class StudyWordFragment extends BaseFullScreenFragment{
    private ViewPager viewPager;
    private SegmentedGroup segmentedGroup;
    private WordMySelf wordMySelf;
    private WordOrderTopic wordOrderTopic;
    private ArrayList<PageTabFragment> arrPager;
    private ViewPagerAdapter pagerAdapter;

    private RadioButton wordTopic,wordSelf;
    @Override
    protected void addView(LayoutInflater inflater, ViewGroup container) {
        contentView=inflater.inflate(R.layout.fragment_words,container,false);
        containerView.addView(contentView);
    }

    @Override
    protected void findViews() {
        super.findViews();
        viewPager=(ViewPager)contentView.findViewById(R.id.view_pager_word);
        segmentedGroup=(SegmentedGroup)contentView.findViewById(R.id.radio_group);
        wordTopic=(RadioButton)contentView.findViewById(R.id.word_order_topic);
        wordSelf=(RadioButton)contentView.findViewById(R.id.word_myselft);
    }

    @Override
    protected void init() {
        super.init();
        arrPager=new ArrayList<>();
        wordMySelf=new WordMySelf();
        wordOrderTopic=new WordOrderTopic();
        arrPager.add(new PageTabFragment("Từ vựng theo chủ đề",wordOrderTopic));
        arrPager.add(new PageTabFragment("Từ vựng của bạn",wordMySelf));
        pagerAdapter=new ViewPagerAdapter(arrPager,getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);
    }


    @Override
    protected void setUpScreen() {
        super.setUpScreen();
        setTitle("Từ vựng");
    }

    @Override
    protected void declareClick() {
        super.declareClick();
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                   switch (position)
                   {
                       case 0:
                           wordTopic.setChecked(true);
                           break;
                       case 1:
                           wordSelf.setChecked(true);
                           break;
                   }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        segmentedGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.word_order_topic:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.word_myselft:
                        viewPager.setCurrentItem(1);
                        break;
                }
            }
        });
    }
}
