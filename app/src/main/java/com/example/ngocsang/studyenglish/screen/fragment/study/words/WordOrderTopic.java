package com.example.ngocsang.studyenglish.screen.fragment.study.words;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ngocsang.studyenglish.R;
import com.example.ngocsang.studyenglish.adapter.TopicAdapter;
import com.example.ngocsang.studyenglish.model.ItemTopicWord;
import com.example.ngocsang.studyenglish.screen.fragment.base.BaseFragment;
import com.example.ngocsang.studyenglish.widget.ItemDecorationAlbumColumns;

import java.util.ArrayList;

/**
 * Created by Ngoc Sang on 11/26/2016.
 */

public class WordOrderTopic extends BaseFragment{
    private RecyclerView lvTopic;
    private TopicAdapter topicAdapter;
    private ArrayList<ItemTopicWord> arr;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView=inflater.inflate(R.layout.screen_word_order_topic,container,false);
        return contentView;
    }

    @Override
    protected void findViews() {
        super.findViews();
        lvTopic=(RecyclerView)contentView.findViewById(R.id.recycler_topic_word);
    }

    @Override
    protected void init() {
        super.init();
        arr=new ArrayList<>();
        arr.add(new ItemTopicWord("Autumn","Mùa Thu",R.drawable.leaves,1));
        arr.add(new ItemTopicWord("Buildings","Tòa Nhà",R.drawable.bulding,2));
        arr.add(new ItemTopicWord("Office,Business,Workplace ","Văn phòng,kinh doanh,và nơi làm việc",R.drawable.folder,3));
        arr.add(new ItemTopicWord("Carnivals and Fairs ","Lễ hội và hội chợ",R.drawable.balloon,4));
        arr.add(new ItemTopicWord("Clothes ","Quần Áo",R.drawable.suit,5));
        arr.add(new ItemTopicWord("Colors ","Màu Sắc",R.drawable.rgb,6));
        arr.add(new ItemTopicWord("Shapes","Hình Dạng",R.drawable.networking,7));
        arr.add(new ItemTopicWord("House","Nhà",R.drawable.house,8));
        topicAdapter=new TopicAdapter(arr,mActivity);
        lvTopic.setLayoutManager(new LinearLayoutManager(getContext()));
        lvTopic.addItemDecoration(new ItemDecorationAlbumColumns(3,1));
        lvTopic.setHasFixedSize(true);
        lvTopic.setAdapter(topicAdapter);
    }

    @Override
    protected void declareClick() {
        super.declareClick();
    }
}
