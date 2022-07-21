package com.perso.bok.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ContentBlock {

    @Id
    private int id;

    private int themeId;

    private String title;

    private String content;

    public ContentBlock(){}

    public ContentBlock(int id, int themeId, String title, String content) {
        this.id = id;
        this.themeId = themeId;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }
}
