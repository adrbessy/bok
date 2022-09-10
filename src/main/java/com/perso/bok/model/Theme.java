package com.perso.bok.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    @NotEmpty(message = "name cannot be empty")
    private String name;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sort;

    private int parentId;

    private boolean showSubthemes;

}
