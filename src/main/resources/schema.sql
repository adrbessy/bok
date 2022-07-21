CREATE TABLE theme
(
   id        INT PRIMARY KEY,
   title       VARCHAR(255) NOT NULL
);

CREATE TABLE content_block
(
   id        INT PRIMARY KEY,
   theme_id        INT NOT NULL,
   title       VARCHAR(255) NOT NULL,
   content       VARCHAR(1000) NOT NULL
);