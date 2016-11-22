package com.example.ngocsang.studyenglish.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ngocsang.studyenglish.R;
import com.example.ngocsang.studyenglish.model.ItemCollection;

import java.util.List;

/**
 * Created by Ngoc Sang on 11/22/2016.
 */

public class CollectionAdapter extends EnglishBaseAdapter<ItemCollection>{
    public CollectionAdapter(List<ItemCollection> mData, Context context) {
        super(mData, context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder viewholder;
        if(convertView==null)
        {
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collection,parent,false);
            viewholder=new Viewholder();
            viewholder.imgBackground=(FrameLayout) convertView.findViewById(R.id.img_item_collection);
            viewholder.tvTitle=(TextView)convertView.findViewById(R.id.tv_item_collection);
            convertView.setTag(viewholder);
        }
        viewholder= (Viewholder) convertView.getTag();
        viewholder.tvTitle.setText(mData.get(position).getTitle());
        viewholder.imgBackground.setBackgroundResource(mData.get(position).getIdImage());
        return convertView;
    }
    public class Viewholder{
        private FrameLayout imgBackground;
        private TextView tvTitle;
    }

}
