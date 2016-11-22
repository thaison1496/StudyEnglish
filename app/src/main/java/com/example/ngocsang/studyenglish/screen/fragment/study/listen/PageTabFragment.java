package com.example.ngocsang.studyenglish.screen.fragment.study.listen;

import android.support.v4.app.Fragment;

/**
 * Created by Ngoc Sang on 11/22/2016.
 */

public class PageTabFragment {
    private String nameTab;
    private Fragment fragment;

    public PageTabFragment() {
    }

    public PageTabFragment(String nameTab, Fragment fragment) {
        this.nameTab = nameTab;
        this.fragment = fragment;
    }

    public String getNameTab() {
        return nameTab;
    }

    public void setNameTab(String nameTab) {
        this.nameTab = nameTab;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
