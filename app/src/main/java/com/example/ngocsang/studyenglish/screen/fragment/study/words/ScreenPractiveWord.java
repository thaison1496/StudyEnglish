package com.example.ngocsang.studyenglish.screen.fragment.study.words;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.CubeInTransformer;
import com.example.ngocsang.studyenglish.R;
import com.example.ngocsang.studyenglish.callback.OnReceiveVoice;
import com.example.ngocsang.studyenglish.model.ItemWord;
import com.example.ngocsang.studyenglish.screen.fragment.base.BaseFragment;
import com.example.ngocsang.studyenglish.widget.CustomPager;

import java.util.ArrayList;

/**
 * Created by Ngoc Sang on 11/29/2016.
 */

public class ScreenPractiveWord extends BaseFragment implements OnReceiveVoice{
    private ViewPager viewPager;
    private CustomPager customPager;
    private boolean isInWord=true;
    private ArrayList<ItemWord> arrayList;
    private ImageView btnBack,btnSpeak;
    private int positionDefault=-1;

    public void setArrayList(ArrayList<ItemWord> arrayList) {
        this.arrayList = arrayList;
    }

    public void setPositionDefault(int positionDefault) {
        this.positionDefault = positionDefault;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       contentView=inflater.inflate(R.layout.screen_practive_word,container,false);
        return contentView;
    }

    @Override
    protected void findViews() {
        super.findViews();
        btnBack=(ImageView)contentView.findViewById(R.id.btn_back);
        btnSpeak=(ImageView)contentView.findViewById(R.id.btn_speak);
        viewPager=(ViewPager)contentView.findViewById(R.id.view_pager_practive);
    }

    @Override
    protected void init() {
        super.init();

            customPager=new CustomPager(mActivity,arrayList);
          viewPager.setAdapter(customPager);
        viewPager.setPageTransformer(true,new CubeInTransformer());
        viewPager.setCurrentItem(positionDefault);
    }

    @Override
    protected void declareClick() {
        super.declareClick();
        mActivity.setReceiveVoice(this);
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mActivity.startVoice();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.onBackPressed();
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onResult(String text) {
        if(text.equals(arrayList.get(viewPager.getCurrentItem()).getName()))
        {
            Toast.makeText(mActivity,"Chính xác từ :"+text,Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(mActivity,"Sai rồi.Xin hãy thử lại",Toast.LENGTH_LONG).show();
        }
    }
}
