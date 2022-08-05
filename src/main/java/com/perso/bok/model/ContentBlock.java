package com.perso.bok.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentBlock {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int themeId;

    @Column(unique = true)
    @NotEmpty(message = "title cannot be empty or null")
    private String title;

    private String content;

    private LocalDateTime createdDate;

}
