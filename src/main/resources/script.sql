-- Execute SQL script (Alt + X)

-- fbank.user definition

CREATE TABLE user (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(20) DEFAULT NULL,
    last_name VARCHAR(20) DEFAULT NULL,
    username VARCHAR(20) DEFAULT NULL,
    password VARCHAR(100) DEFAULT NULL,
    UNIQUE KEY UK_sb8bbouer5wak8vyiiy4pf2bx (username)
);

INSERT INTO fbank.user (first_name, last_name, username, password)
VALUES('Ferney', 'Baron', 'fbaron', '1234');

-- fbank.employee definition

CREATE TABLE employee (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    department VARCHAR(20) NOT NULL,
    job_title VARCHAR(20) NOT NULL,
    user_id INT DEFAULT NULL,
    UNIQUE KEY UK_j7ja2xvrxudhvssosd4nu1o92 (user_id),
    CONSTRAINT FKj8dlm21j202cadsbfkoem0s58 FOREIGN KEY (user_id) REFERENCES user (id)
);

INSERT INTO fbank.employee (department, job_title, user_id)
VALUES ('IT', 'Software Engineer', 1);

-- fbank.employee definition

DELIMITER //

CREATE PROCEDURE GetUserInformation(
    IN userID INT,
    OUT fullName VARCHAR(50),
    OUT job VARCHAR(50)
)
BEGIN

    SELECT CONCAT(first_name, ' ', last_name) INTO fullName FROM user WHERE id = userID;
    SELECT job_title INTO job FROM employee JOIN user ON user.id = employee.user_id;

END //

DELIMITER ;