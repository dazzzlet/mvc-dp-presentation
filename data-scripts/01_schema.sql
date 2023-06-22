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

CREATE TABLE activities (
    activity_id int NOT NULL AUTO_INCREMENT,
    title varchar(255) NOT NULL,
    description varchar(500) NOT NULL,
    date BIGINT NOT NULL,
    created_on BIGINT NOT NULL,
    created_by int NOT NULL,
    updated_on BIGINT,
    updated_by int,
    status TINYINT NOT NULL,
    PRIMARY KEY (activity_id)
);

CREATE TABLE registers (
    user_id int NOT NULL,
    activity_id int NOT NULL,
    register_on BIGINT NOT NULL,
    PRIMARY KEY (user_id, activity_id)
);