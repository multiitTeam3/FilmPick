회원 테이블
CREATE TABLE mem_member (
    member_no INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(254) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    user_name VARCHAR(50) NOT NULL UNIQUE,
    tel VARCHAR(15) NOT NULL,
    point INT NOT NULL DEFAULT 0,
    address VARCHAR(255) NULL,
    crate_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modify_date TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (member_no)
);

권한 테이블
CREATE TABLE mem_role (
    role_no INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(15) NOT NULL,
    description VARCHAR(100) NOT NULL,
    PRIMARY KEY (role_no)
);

INSERT INTO mem_role VALUES (null, 'ROLE_ADMIN', '관리자 권한');
INSERT INTO mem_role VALUES (null, 'ROLE_STAFF', '제한된 관리자 권한');
INSERT INTO mem_role VALUES (null, 'ROLE_USER', '일반유저 권한');

회원, 권한 간 매핑 테이블
CREATE TABLE mem_member_and_role (
    member_no INT NOT NULL,
    role_no INT NOT NULL,
    PRIMARY KEY (member_no, role_no),
    FOREIGN KEY (member_no) REFERENCES mem_member(member_no),
    FOREIGN KEY (role_no) REFERENCES mem_role(role_no)
);

임시 비밀번호 테이블
CREATE TABLE mem_temp_password (
    temp_pw_no INT NOT NULL AUTO_INCREMENT,
    member_no INT NOT NULL,
    temp_pw VARCHAR(100) NOT NULL,
    create_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    exp_date TIMESTAMP NOT NULL,
    PRIMARY KEY (temp_pw_no),
    FOREIGN KEY (member_no) REFERENCES mem_member(member_no)
);

포인트 사용 내역 테이블
CREATE TABLE mem_point_transaction (
    point_tx_no INT NOT NULL AUTO_INCREMENT,
    member_no INT NOT NULL,
    point_change INT NOT NULL,
    point_description VARCHAR(255) NOT NULL,
    PRIMARY KEY (point_tx_no),
    FOREIGN KEY (member_no) REFERENCES mem_member(member_no)
);

쿠폰 테이블
CREATE TABLE mem_coupon (
    coupon_no INT NOT NULL AUTO_INCREMENT,
    member_no INT NOT NULL,
    coupon_code VARCHAR(50) NOT NULL,
    description VARCHAR(255) NOT NULL,
    discount INT NOT NULL,
    use_date TIMESTAMP NULL,
    create_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    exp_date TIMESTAMP NOT NULL,
    PRIMARY KEY (coupon_no),
    FOREIGN KEY (member_no) REFERENCES mem_member(member_no)
);