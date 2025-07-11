CREATE TABLE user (
    id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(20) DEFAULT NULL,
    last_name VARCHAR(20) DEFAULT NULL,
    username VARCHAR(20) DEFAULT NULL,
    password VARCHAR(100) DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_user_username (username)
);

INSERT INTO fbank.`user`
(first_name, last_name, username, password)
VALUES('Ferney', 'Baron', 'fbaron', '$2a$12$TCEaXfvjZmCMPUsiUNPYVuUJ/.Iq6GuwgTGT49Qvs.WUcQWPI/oW6');