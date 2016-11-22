package com.example.ngocsang.studyenglish.model;

/**
 * Created by Ngoc Sang on 11/22/2016.
 */

public class ItemCollection {
    private String title;
    private int idImage;
    private String idLisVideo;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getIdLisVideo() {
        return idLisVideo;
    }

    public void setIdLisVideo(String idLisVideo) {
        this.idLisVideo = idLisVideo;
    }

    public ItemCollection(String title, int idImage, String idLisVideo) {

        this.title = title;
        this.idImage = idImage;
        this.idLisVideo = idLisVideo;
    }
}
