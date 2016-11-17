package com.example.ngocsang.studyenglish.model;

/**
 * Created by Ngoc Sang on 11/6/2016.
 */

public class ItemLesson {
    private int idImage;
    private int background;
    private String title;

    public int getBackground() {
        return background;
    }

    public ItemLesson(int idImage, String title, int background) {
        this.idImage = idImage;
        this.background=background;

        this.title = title;
    }

    public int getIdImage() {

        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
