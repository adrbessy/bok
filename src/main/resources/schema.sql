CREATE TABLE theme
(
   id        INT PRIMARY KEY NOT NULL,
   name       VARCHAR(255) NOT NULL
);

CREATE TABLE content_block
(
   id        INT PRIMARY KEY NOT NULL,
   theme_id        INT NOT NULL,
   title       VARCHAR(255) NOT NULL,
   content       LONGTEXT,
   created_date TIMESTAMP NOT NULL
);