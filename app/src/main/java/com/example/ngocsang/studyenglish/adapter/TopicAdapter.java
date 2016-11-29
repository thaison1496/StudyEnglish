package com.example.ngocsang.studyenglish.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ngocsang.studyenglish.R;
import com.example.ngocsang.studyenglish.model.ItemTopicWord;
import com.example.ngocsang.studyenglish.screen.activity.MainActivity;
import com.example.ngocsang.studyenglish.screen.fragment.study.communication.ScreenDetailCommucation;
import com.example.ngocsang.studyenglish.screen.fragment.study.words.ScreenDetailWord;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Ngoc Sang on 11/27/2016.
 */

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {
    private ArrayList<ItemTopicWord> arr;
    private MainActivity context;
    private boolean word=true;
    public TopicAdapter(ArrayList<ItemTopicWord> arr,MainActivity context)
    {
        this.arr=arr;
        this.context=context;
    }

    public boolean isWord() {
        return word;
    }

    public void setWord(boolean word) {
        this.word = word;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.img.setImageResource(arr.get(position).getIdImage());
        holder.tvtopic.setText(arr.get(position).getKey());
        holder.tvSub.setText(arr.get(position).getValue());
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(word)
                {
                    ScreenDetailWord detailWord=new ScreenDetailWord();
                    detailWord.setTitleScreen(arr.get(position).getKey());
                    detailWord.setIdTopic(arr.get(position).getTopicId());
                    context.replaceFullScreen(detailWord,true,"detail_screen_word");

                }
                else {
                    ScreenDetailCommucation detailCommucation=new ScreenDetailCommucation();
                    detailCommucation.setIdTopic(arr.get(position).getTopicId());
                    detailCommucation.setTitleScreen(arr.get(position).getKey());
                    context.replaceFullScreen(detailCommucation,true,"detail_screen_commuication");
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       private CircleImageView img;
        private TextView tvtopic,tvSub;
        private RelativeLayout root;
        public ViewHolder(View itemView) {
            super(itemView);
            img=(CircleImageView)itemView.findViewById(R.id.img_topic);
            tvtopic=(TextView)itemView.findViewById(R.id.tv_topic);
            tvSub=(TextView)itemView.findViewById(R.id.tv_sub_title_topic);
            root=(RelativeLayout)itemView.findViewById(R.id.rl_root_topic);
        }
    }
}
