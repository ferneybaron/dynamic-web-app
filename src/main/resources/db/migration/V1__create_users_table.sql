CREATE TABLE user (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(20) DEFAULT NULL,
    last_name VARCHAR(20) DEFAULT NULL,
    username VARCHAR(20) DEFAULT NULL,
    password VARCHAR(100) DEFAULT NULL,
    UNIQUE KEY UK_user_username (username)
);