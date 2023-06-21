CREATE DATABASE club_management;
USE club_management;

GRANT ALL PRIVILEGES ON club_management.* TO 'user'@'%' WITH GRANT OPTION;

CREATE TABLE users (
    user_id int NOT NULL AUTO_INCREMENT,
    username varchar(255) NOT NULL,
    firstname varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    role varchar(50) NOT NULL,
    bio varchar(500),
    PRIMARY KEY (user_id)
);