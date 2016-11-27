package com.example.ngocsang.studyenglish.adapter;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ngocsang.studyenglish.R;
import com.example.ngocsang.studyenglish.model.ItemWord;

import java.io.IOException;
import java.util.List;

/**
 * Created by Ngoc Sang on 11/27/2016.
 */

public class WordAdapter extends EnglishBaseAdapter<ItemWord>{
    private MediaPlayer mediaPlayer;
    public WordAdapter(List<ItemWord> mData, Context context) {
        super(mData, context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null)
        {
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_word,parent,false);
            holder=new ViewHolder();
            holder.sound=(ImageView)convertView.findViewById(R.id.img_sound);
            holder.btnStar=(ImageView)convertView.findViewById(R.id.img_btn_star);
            holder.tvKey=(TextView)convertView.findViewById(R.id.tv_key);
            holder.tvValue=(TextView)convertView.findViewById(R.id.tv_value);
            convertView.setTag(holder);


        }
        holder= (ViewHolder) convertView.getTag();

         holder.sound.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 mediaPlayer=MediaPlayer.create(mContext,Uri.parse(mData.get(position).getAudio()));
                 mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                     @Override
                     public void onCompletion(MediaPlayer mp) {
                         mp.start();
                     }
                 });

             }
         });
        holder.tvKey.setText(mData.get(position).getName());
        holder.tvValue.setText(mData.get(position).getContain());
        if(mData.get(position).isSelect()==1)
        {
            holder.btnStar.setImageResource(R.drawable.btn_star_active);
        }

        return convertView;
    }
    public class ViewHolder{
        private ImageView sound,btnStar;
        private TextView tvKey,tvValue;
    }
}
