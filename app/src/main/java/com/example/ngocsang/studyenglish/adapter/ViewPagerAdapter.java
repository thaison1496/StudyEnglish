package com.example.ngocsang.studyenglish.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ngocsang.studyenglish.screen.fragment.study.listen.PageTabFragment;

import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<PageTabFragment> pages;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public ViewPagerAdapter(List<PageTabFragment> pages, FragmentManager fm) {
        super(fm);
        this.pages = pages;
    }

    @Override
    public Fragment getItem(int position) {
        return pages.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        super.getPageTitle(position);
        return pages.get(position).getNameTab();
    }
}
