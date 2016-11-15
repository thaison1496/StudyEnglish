package com.example.ngocsang.studyenglish.model;

/**
 * Created by Ngoc Sang on 11/6/2016.
 */

public class ItemGrammar {
private int indexGrammar,level,id;
    private String titleGrammar;

    public int getIndexGrammar() {
        return indexGrammar;
    }

    public void setIndexGrammar(int indexGrammar) {
        this.indexGrammar = indexGrammar;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTitleGrammar() {
        return titleGrammar;
    }

    public void setTitleGrammar(String titleGrammar) {
        this.titleGrammar = titleGrammar;
    }

    public String getContentGrammar() {
        return contentGrammar;
    }

    public void setContentGrammar(String contentGrammar) {
        this.contentGrammar = contentGrammar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public ItemGrammar(int id, int indexGrammar, int level, String titleGrammar, String contentGrammar) {

        this.indexGrammar = indexGrammar;
        this.id=id;

        this.level = level;
        this.titleGrammar = titleGrammar;
        this.contentGrammar = contentGrammar;
    }

    private String contentGrammar;

}
