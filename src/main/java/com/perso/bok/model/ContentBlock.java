package com.perso.bok.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ContentBlock {

    @Id
    private int id;

    private int themeId;

    private String title;

    private String content;

    private Date createdDate;

    public ContentBlock(){}

    public ContentBlock(int id, int themeId, String title, String content, Date createdDate) {
        this.id = id;
        this.themeId = themeId;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
