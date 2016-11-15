package com.example.ngocsang.studyenglish.screen.fragment.forum;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ngocsang.studyenglish.R;
import com.example.ngocsang.studyenglish.screen.fragment.base.BaseFragment;

/**
 * Created by Ngoc Sang on 10/5/2016.
 */
public class ForumFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView lvPostForum;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView=inflater.inflate(R.layout.fragment_forum,container,false);
        return contentView;
    }
    @Override
    protected void findViews() {
        super.findViews();
        refreshLayout=(SwipeRefreshLayout)contentView.findViewById(R.id.swipe_refresh_load);
        lvPostForum=(RecyclerView)contentView.findViewById(R.id.lv_post_forum);
    }

    @Override
    protected void declareClick() {
        super.declareClick();
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {

    }
}
