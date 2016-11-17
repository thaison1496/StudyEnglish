package com.example.ngocsang.studyenglish.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ngocsang.studyenglish.R;

/**
 * Created by PhamVanLong on 11/8/2016.
 */

public class ItemBtnSelect extends RelativeLayout{
    private TextView tvContentBtn,tvTitle,tvSubAnswer;
    private RelativeLayout mainBtn;
    private Context context;

    public ItemBtnSelect(Context context) {
        super(context);
        inflateView(context);

    }

    public ItemBtnSelect(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateView(context);
    }

    public ItemBtnSelect(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ItemBtnSelect(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inflateView(context);
    }
    private void inflateView(Context context)
    {   this.context=context;
        inflate(context, R.layout.item_btn_select,this);
        tvContentBtn=(TextView)findViewById(R.id.tv_content_answer);
        tvTitle=(TextView)findViewById(R.id.tv_title_alphabet);
        tvSubAnswer=(TextView)findViewById(R.id.tv_sub_answer);
        mainBtn=(RelativeLayout)findViewById(R.id.main_custom_btn);

    }
    public void initData(String title,String content)
    {
        tvTitle.setText(title);
        tvContentBtn.setText(content);
        invalidate();
        requestLayout();
    }
    public void setDefaultBtn()
    {   mainBtn.clearAnimation();
        tvSubAnswer.setVisibility(GONE);
        tvTitle.setBackground(context.getResources().getDrawable(R.drawable.background_circle));
        mainBtn.setBackground(context.getResources().getDrawable(R.drawable.state_default));

        invalidate();
        requestLayout();


    }
    public void selectBtn()
    {
        tvTitle.setBackground(context.getResources().getDrawable(R.drawable.background_circle));
        mainBtn.setBackground(context.getResources().getDrawable(R.drawable.state_select));
        invalidate();
        requestLayout();
    }
    public void setFocusBtn()
    {

        tvTitle.setBackground(context.getResources().getDrawable(R.drawable.background_circle));
        mainBtn.setBackground(context.getResources().getDrawable(R.drawable.state_focus));
        invalidate();
        requestLayout();
    }
    public void setStatus(String subAnswer,boolean right)
    {
        tvSubAnswer.setVisibility(VISIBLE);
        if(right)
        {     tvTitle.setBackground(context.getResources().getDrawable(R.drawable.background_circle_true));
            mainBtn.setBackground(context.getResources().getDrawable(R.drawable.background_button));
            tvSubAnswer.setBackground(context.getResources().getDrawable(R.drawable.background_button));
        }
        else {
            tvTitle.setBackground(context.getResources().getDrawable(R.drawable.background_cirrcle_wrong));
            mainBtn.setBackground(context.getResources().getDrawable(R.drawable.background_state_wrong));
            tvSubAnswer.setBackground(context.getResources().getDrawable(R.drawable.background_state_wrong));
        }

        tvSubAnswer.setText(subAnswer);
        mainBtn.startAnimation(AnimationUtils.loadAnimation(context,R.anim.blur));
        invalidate();
        requestLayout();
    }
    public void setStatusTrue()
    {
        tvTitle.setBackground(context.getResources().getDrawable(R.drawable.background_circle_true));
        mainBtn.setBackground(context.getResources().getDrawable(R.drawable.background_button));
        mainBtn.startAnimation(AnimationUtils.loadAnimation(context,R.anim.blur));
        invalidate();
        requestLayout();
    }
    public void setTvTitle(String title)
    {
        tvTitle.setText(title);
        tvTitle.invalidate();
        tvTitle.requestLayout();
    }
    public void setTvContentBtn(String content)
    {
        tvContentBtn.setText(content);
        tvContentBtn.invalidate();
        tvContentBtn.requestLayout();
    }



}
