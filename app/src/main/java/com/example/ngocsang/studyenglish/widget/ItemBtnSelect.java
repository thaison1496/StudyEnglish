package com.example.ngocsang.studyenglish.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ngocsang.studyenglish.R;

/**
 * Created by PhamVanLong on 11/8/2016.
 */

public class ItemBtnSelect extends RelativeLayout{
    private TextView tvContentBtn,tvTitle;
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

    }
    public void initData(String title,String content)
    {
        tvTitle.setText(title);
        tvContentBtn.setText(content);
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
    public  void changeBackGroundTitle(int type)
    {
        if(type==0)
        {
            Drawable drawable=context.getResources().getDrawable(R.drawable.background_circle);
            tvTitle.setBackground(drawable);
            requestLayout();
        }
        else {
            Drawable drawable=context.getResources().getDrawable(R.drawable.background_cirrcle_wrong);
            tvTitle.setBackground(drawable);
            requestLayout();
        }
    }
   public void setBackGroundTitle(int type)
   {
       switch (type)
       {
           case 0://dèaul
               tvTitle.setBackground(context.getResources().getDrawable(R.drawable.background_circle));
               break;
           case 1://true
               tvTitle.setBackground(context.getResources().getDrawable(R.drawable.background_circle_true));
               break;
           case 2:
               tvTitle.setBackground(context.getResources().getDrawable(R.drawable.background_cirrcle_wrong));
               break;

       }
   }
    public void changeBackGround(int id)
    {
        setBackground(context.getResources().getDrawable(id));
    }
    public void setSelected(boolean selected)
    {
        if(selected)
        {
            setBackground(context.getResources().getDrawable(R.drawable.state_select));
        }
        else {
            setBackground(context.getResources().getDrawable(R.drawable.state_default));
        }
    }


}
