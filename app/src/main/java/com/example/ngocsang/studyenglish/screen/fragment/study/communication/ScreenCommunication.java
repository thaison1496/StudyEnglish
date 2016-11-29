package com.example.ngocsang.studyenglish.screen.fragment.study.communication;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.ngocsang.studyenglish.R;
import com.example.ngocsang.studyenglish.adapter.TopicAdapter;
import com.example.ngocsang.studyenglish.model.ItemTopicWord;
import com.example.ngocsang.studyenglish.screen.fragment.base.BaseFullScreenFragment;
import com.example.ngocsang.studyenglish.widget.ItemDecorationAlbumColumns;

import java.util.ArrayList;

/**
 * Created by Ngoc Sang on 11/29/2016.
 */

public class ScreenCommunication extends BaseFullScreenFragment {
    private RecyclerView lvTopic;
    private TopicAdapter topicAdapter;
    private ArrayList<ItemTopicWord> arr;

    @Override
    protected void addView(LayoutInflater inflater, ViewGroup container) {
        contentView = inflater.inflate(R.layout.screen_communication, container, false);
        containerView.addView(contentView);
    }

    @Override
    protected void declareClick() {
        super.declareClick();

    }

    @Override
    protected void init() {
        super.init();
        arr = new ArrayList<>();
        arr.add(new ItemTopicWord("First Meeting", "Gặp mặt lần đầu", R.drawable.item_communicate, 1));
        arr.add(new ItemTopicWord("Visiting friend", "Thăm hỏi bạn bè", R.drawable.item_communicate, 2));
        arr.add(new ItemTopicWord("Meet by chance", "Gặp gỡ tình cờ",R.drawable.item_communicate, 3));
        arr.add(new ItemTopicWord("Introduce Friend", "Giới thiệu người thân, bạn bè",R.drawable.item_communicate, 4));
        arr.add(new ItemTopicWord("Happy", "Vui mừng hạnh phúc",R.drawable.item_communicate, 5));
        arr.add(new ItemTopicWord("Worry", "Lo lắng buồn chán",R.drawable.item_communicate, 6));
        arr.add(new ItemTopicWord("Apologize", "Xin lỗi",R.drawable.item_communicate, 7));
        arr.add(new ItemTopicWord("Asking for direction", "Hỏi đường",R.drawable.item_communicate, 8));
        arr.add(new ItemTopicWord("Order Room", "Đặt phòng",R.drawable.item_communicate, 9));
        arr.add(new ItemTopicWord("Cinema", "Rạp chiếu phim", R.drawable.item_communicate, 10));
        topicAdapter = new TopicAdapter(arr, mActivity);
        topicAdapter.setWord(false);
        lvTopic.setLayoutManager(new LinearLayoutManager(mActivity));
        lvTopic.addItemDecoration(new ItemDecorationAlbumColumns(3, 1));
        lvTopic.setHasFixedSize(true);
        lvTopic.setAdapter(topicAdapter);
    }

    @Override
    protected void findViews() {
        super.findViews();
        lvTopic = (RecyclerView) contentView.findViewById(R.id.recycler_topic_communication);
    }

    @Override
    protected void setUpScreen() {
        super.setUpScreen();
        setTitle("Giao Tiếp");
    }
}
