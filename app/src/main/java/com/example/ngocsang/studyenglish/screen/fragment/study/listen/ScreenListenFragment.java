package com.example.ngocsang.studyenglish.screen.fragment.study.listen;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.ngocsang.studyenglish.R;
import com.example.ngocsang.studyenglish.adapter.ViewPagerAdapter;
import com.example.ngocsang.studyenglish.constant.Constant;
import com.example.ngocsang.studyenglish.screen.fragment.base.BaseFullScreenFragment;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;

import java.util.ArrayList;

/**
 * Created by Ngoc Sang on 11/22/2016.
 */

public class ScreenListenFragment extends BaseFullScreenFragment{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<PageTabFragment> arrPager;
    private ViewPagerAdapter pagerAdapter;
    private ListVideoFragment voa,bbcLearningEnglish,englishEveryDay,music;
    private CollectionFragment collectionFragment;
    private YouTube mYoutubeDataApi;
    private final GsonFactory mJsonFactory = new GsonFactory();
    private final HttpTransport mTransport = AndroidHttp.newCompatibleTransport();

    @Override
    protected void addView(LayoutInflater inflater, ViewGroup container) {
                  contentView=inflater.inflate(R.layout.fragment_study_listen,container,false);
        containerView.addView(contentView);
    }

    @Override
    protected void findViews() {
        super.findViews();
        tabLayout=(TabLayout)contentView.findViewById(R.id.tab_layout);
        viewPager=(ViewPager)contentView.findViewById(R.id.view_pager);
    }

    @Override
    protected void init() {
        super.init();
        arrPager=new ArrayList<>();
        mYoutubeDataApi = new YouTube.Builder(mTransport, mJsonFactory, null)
                .setApplicationName(getResources().getString(R.string.app_name))
                .build();
        collectionFragment=new CollectionFragment();
        voa=new ListVideoFragment();
        voa.setmPlaylistVideos(new PlaylistVideos(Constant.VOA));
        voa.setmYouTubeDataApi(mYoutubeDataApi);
        bbcLearningEnglish=new ListVideoFragment();
        bbcLearningEnglish.setmYouTubeDataApi(mYoutubeDataApi);
        bbcLearningEnglish.setmPlaylistVideos(new PlaylistVideos(Constant.BBC_LEARNING_ENGLISH));
        englishEveryDay=new ListVideoFragment();
        englishEveryDay.setmYouTubeDataApi(mYoutubeDataApi);
        englishEveryDay.setmPlaylistVideos(new PlaylistVideos(Constant.TIENG_ANH_MOI_NGAY));
        music=new ListVideoFragment();
        music.setmPlaylistVideos(new PlaylistVideos(Constant.TIENG_ANH_QUA_BAI_HAT));
        music.setmYouTubeDataApi(mYoutubeDataApi);
        arrPager.add(new PageTabFragment("VOA",voa));
        arrPager.add(new PageTabFragment("BBC LEARNING ENGLISH",bbcLearningEnglish));
        arrPager.add(new PageTabFragment("ENGHLISH EVERY DAY",englishEveryDay));
        arrPager.add(new PageTabFragment("LISTEN MUSIC",music));
        arrPager.add(new PageTabFragment("COLLECTIONS",collectionFragment));
        pagerAdapter=new ViewPagerAdapter(arrPager,getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);




    }

    @Override
    protected void setUpScreen() {
        super.setUpScreen();
        setTitle("Luyện Nghe Qua Video");
    }
}
