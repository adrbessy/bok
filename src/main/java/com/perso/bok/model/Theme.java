package com.perso.bok.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Theme {

    @Id
    private int id;

    private String name;

    public Theme() {
    }

    public Theme(int id, String name) {
        this.id = id;
        this.name = name;
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
}
