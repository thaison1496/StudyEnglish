package com.example.ngocsang.studyenglish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Ngoc Sang on 10/5/2016.
 */
public abstract class EnglishBaseAdapter<T> extends BaseAdapter{
    public List<T> mData;
    public Context mContext;
    private LayoutInflater inflater;
    public EnglishBaseAdapter(List<T> mData,Context context)
    {
          this.mContext=context;
        this.mData=mData;
        inflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        if(mData==null||mData.isEmpty())
        {
            return 0;
        }
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        if(mData==null||mData.isEmpty())
        {
            return null;
        }
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public void updateData(List<T> data)
    {
        if(data!=null&&!data.isEmpty())
        {
            mData=data;
            this.notifyDataSetChanged();
        }
    }
    public void clearData()
    {
       mData.clear();
        this.notifyDataSetChanged();
    }

}
