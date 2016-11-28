package com.example.ngocsang.studyenglish.screen.fragment.study.speak;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.ngocsang.studyenglish.R;
import com.example.ngocsang.studyenglish.adapter.ViewPagerAdapter;
import com.example.ngocsang.studyenglish.constant.Constant;
import com.example.ngocsang.studyenglish.screen.fragment.base.BaseFullScreenFragment;
import com.example.ngocsang.studyenglish.screen.fragment.study.listen.CollectionFragment;
import com.example.ngocsang.studyenglish.screen.fragment.study.listen.ListVideoFragment;
import com.example.ngocsang.studyenglish.screen.fragment.study.listen.PageTabFragment;
import com.example.ngocsang.studyenglish.screen.fragment.study.listen.PlaylistVideos;
import com.example.ngocsang.studyenglish.widget.CustomViewPager;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;

import java.util.ArrayList;

/**
 * Created by Ngoc Sang on 11/23/2016.
 */

public class ScreenSpeakFragment extends BaseFullScreenFragment{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<PageTabFragment> arrPager;
    private ViewPagerAdapter pagerAdapter;
    private ListVideoFragment a,b,c,d;
    private YouTube mYoutubeDataApi;
    private final GsonFactory mJsonFactory = new GsonFactory();
    private final HttpTransport mTransport = AndroidHttp.newCompatibleTransport();

    @Override
    protected void addView(LayoutInflater inflater, ViewGroup container) {
       contentView=inflater.inflate(R.layout.screen_speak_fragment,container,false);
       containerView.addView(contentView);
    }
    @Override
    protected void findViews() {
        super.findViews();
        tabLayout=(TabLayout)contentView.findViewById(R.id.tab_layout_screen_speak);
        viewPager=(ViewPager)contentView.findViewById(R.id.view_pager_screen_speak);
    }

    @Override
    protected void init() {
        super.init();
        arrPager=new ArrayList<>();
        mYoutubeDataApi = new YouTube.Builder(mTransport, mJsonFactory, null)
                .setApplicationName(getResources().getString(R.string.app_name))
                .build();
        a=new ListVideoFragment();
        a.setmPlaylistVideos(new PlaylistVideos(Constant.HỌC_GIAO_TIẾP_CƠ_BẢN));
       a.setmYouTubeDataApi(mYoutubeDataApi);
       b=new ListVideoFragment();
        b.setmYouTubeDataApi(mYoutubeDataApi);
       b.setmPlaylistVideos(new PlaylistVideos(Constant.LUYỆN_NGỮ_ÂM));
      c=new ListVideoFragment();
       c.setmYouTubeDataApi(mYoutubeDataApi);
       c.setmPlaylistVideos(new PlaylistVideos(Constant.LUYỆN_PHÁT_ÂM));
       d=new ListVideoFragment();
       d.setmPlaylistVideos(new PlaylistVideos(Constant.HOC_CUNG_CO_HOA));
       d.setmYouTubeDataApi(mYoutubeDataApi);
        arrPager.add(new PageTabFragment("Giao Tiếp Cơ Bản",a));
        arrPager.add(new PageTabFragment("Luyện Ngũ Âm",b));
        arrPager.add(new PageTabFragment("Luyện Phát Âm",c));
        arrPager.add(new PageTabFragment("Học Cùng Cô Hoa",d));
        pagerAdapter=new ViewPagerAdapter(arrPager,getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);




    }

    @Override
    protected void setUpScreen() {
        super.setUpScreen();
        setTitle("Luyện Phát Âm Qua Video");
    }
}
