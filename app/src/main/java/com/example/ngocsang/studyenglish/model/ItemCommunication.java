package com.example.ngocsang.studyenglish.model;

/**
 * Created by Ngoc Sang on 11/27/2016.
 */

public class ItemCommunication {
    private int id,kind;
    private String key;
    private String value;
    private String audio;
    private boolean isAdd;

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

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

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public ItemCommunication(int id, String key, String value, String audio, int kind, boolean isAdd) {
        this.id = id;
        this.key = key;
        this.value = value;
        this.audio = audio;
        this.kind = kind;
        this.isAdd = isAdd;
    }
}
