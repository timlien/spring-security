CREATE TABLE
    users
(
    id       BIGINT       NOT NULL AUTO_INCREMENT,
    username VARCHAR(50)  NOT NULL,
    password VARCHAR(500) NOT NULL,
    PRIMARY KEY (id),
    KEY (username)
);

CREATE TABLE
    authorities
(
    id        BIGINT      NOT NULL AUTO_INCREMENT,
    username  VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    KEY (username),
    FOREIGN KEY (username) REFERENCES users (username)
);