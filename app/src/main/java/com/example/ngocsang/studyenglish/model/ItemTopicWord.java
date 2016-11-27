package com.example.ngocsang.studyenglish.model;

/**
 * Created by Ngoc Sang on 11/27/2016.
 */

public class ItemTopicWord {
    private String key,value;
    private int idImage;

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    private int topicId;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public ItemTopicWord(String key, String value, int idImage,int topicId) {
        this.topicId=topicId;
        this.key = key;
        this.value = value;
        this.idImage = idImage;
    }
}
