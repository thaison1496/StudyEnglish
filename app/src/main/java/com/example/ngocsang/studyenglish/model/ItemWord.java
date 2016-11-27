package com.example.ngocsang.studyenglish.model;

/**
 * Created by Ngoc Sang on 11/27/2016.
 */

public class ItemWord {
private int id;
    private String name,spelling,contain,topicName,audio;
    private int topicId;
    private int isSelect;

    public ItemWord(int id, String name, String spelling, String contain, String audio,int isSelect,String topicName) {
        this.id = id;
        this.topicName=topicName;
        this.name = name;
        this.spelling = spelling;
        this.contain = contain;
        this.audio = audio;
        this.isSelect = isSelect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpelling() {
        return spelling;
    }

    public void setSpelling(String spelling) {
        this.spelling = spelling;
    }

    public String getContain() {
        return contain;
    }

    public void setContain(String contain) {
        this.contain = contain;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int isSelect() {
        return isSelect;
    }

    public void setSelect(int select) {
        isSelect = select;
    }
}
