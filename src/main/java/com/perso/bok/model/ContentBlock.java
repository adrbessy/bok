package com.perso.bok.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int themeId;

    @NotEmpty(message = "title cannot be empty")
    private String title;

    @Column(length = 3000)
    private String content;

    private LocalDateTime createdDate;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sort;

}
