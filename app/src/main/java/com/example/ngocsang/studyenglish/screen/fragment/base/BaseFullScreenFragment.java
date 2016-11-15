package com.example.ngocsang.studyenglish.screen.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ngocsang.studyenglish.R;

/**
 * Created by Ngoc Sang on 10/5/2016.
 */
public abstract class BaseFullScreenFragment extends BaseFragment implements View.OnClickListener{
    protected View root;
    protected FrameLayout containerView;
    protected ImageView btnBack;
    protected TextView tvTitleToolBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      super.onCreateView(inflater, container, savedInstanceState);
        root=inflater.inflate(R.layout.layout_base_full_screen,container,false);
        containerView=(FrameLayout)root.findViewById(R.id.container_layout_full_screen);
        addView(inflater,container);
        return root;

    }
   protected abstract void addView(LayoutInflater inflater,ViewGroup container);

    @Override
    protected void findViews() {
        super.findViews();
        btnBack=(ImageView)root.findViewById(R.id.img_btn_back);
        tvTitleToolBar=(TextView)root.findViewById(R.id.tv_title_tool_bar);
    }

    @Override
    protected void declareClick() {
        super.declareClick();
        btnBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.img_btn_back:
                mActivity.onBackPressed();
                break;
        }
    }
    public void setTitle(String title)
    {
        tvTitleToolBar.setText(title);
    }
}
