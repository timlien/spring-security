CREATE TABLE
    user
(
    id          INT                NOT NULL AUTO_INCREMENT,
    email       VARCHAR(50) UNIQUE NOT NULL,
    password    VARCHAR(500)       NOT NULL,
    create_date DATETIME           NOT NULL,
    PRIMARY KEY (id),
    KEY (email)
);

CREATE TABLE
    authority
(
    id        INT         NOT NULL AUTO_INCREMENT,
    user_id   INT         NOT NULL,
    authority VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES user (id)
);