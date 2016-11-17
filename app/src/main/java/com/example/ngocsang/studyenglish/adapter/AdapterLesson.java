package com.example.ngocsang.studyenglish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ngocsang.studyenglish.R;
import com.example.ngocsang.studyenglish.model.ItemLesson;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Ngoc Sang on 11/6/2016.
 */

public class AdapterLesson extends EnglishBaseAdapter<ItemLesson>{
    public AdapterLesson(List<ItemLesson> mData, Context context) {
        super(mData, context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null)
        {
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lesson,parent,false);
            holder=new ViewHolder();
            holder.imageView=(CircleImageView)convertView.findViewById(R.id.img_icon_lesson);
            holder.tvTitle=(TextView)convertView.findViewById(R.id.tv_title_lesson);
            holder.bg=(RelativeLayout)convertView.findViewById(R.id.rl_bg_item_lesson);
            convertView.setTag(holder);
        }
        else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tvTitle.setText(mData.get(position).getTitle());
        holder.imageView.setImageResource(mData.get(position).getIdImage());
        holder.bg.setBackgroundResource(mData.get(position).getBackground());
        return convertView;
    }
    public class ViewHolder{
        private CircleImageView imageView;
        private RelativeLayout bg;
        private TextView tvTitle;
    }
}
