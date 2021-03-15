create table Post (id LONG AUTO_INCREMENT PRIMARY KEY, title VARCHAR (200) NOT NULL, content VARCHAR(1000));

create table comment (id BIGINT AUTO_INCREMENT PRIMARY KEY, post_id BIGINT NOT NULL, content VARCHAR(1000), created timestamp);

Alter table comment ADD CONSTRAINT comment_post_id FOREIGN KEY (post_id) REFERENCES post(id)