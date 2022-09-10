CREATE TABLE theme
(
   id        INT PRIMARY KEY NOT NULL,
   name       VARCHAR(255) NOT NULL,
   sort    INT NOT NULL,
   parent_id INT
   showSubthemes boolean
);

CREATE TABLE content_block
(
   id        INT PRIMARY KEY NOT NULL,
   theme_id        INT NOT NULL,
   title       VARCHAR(255) NOT NULL,
   content  TEXT,
   created_date TIMESTAMP NOT NULL,
   sort    INT NOT NULL
)