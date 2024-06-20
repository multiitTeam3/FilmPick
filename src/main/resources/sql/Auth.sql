CREATE TABLE MEMBER (
    MEMBER_NO INT NOT NULL AUTO_INCREMENT,
    ID VARCHAR(20) NOT NULL UNIQUE,
    PW VARCHAR(100) NOT NULL,
    USERNAME VARCHAR(20) NOT NULL UNIQUE,
    TEL VARCHAR(20) NOT NULL',
    IMG VARCHAR(100),
    ROLE VARCHAR(10) NOT NULL DEFAULT 'ROLE_USER',
    CRATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    MODIFY_DATE TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (MEMBER_NO)
);