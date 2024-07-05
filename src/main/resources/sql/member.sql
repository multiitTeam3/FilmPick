CREATE TABLE mem_member (
    member_no INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(254) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    user_name VARCHAR(50) NOT NULL UNIQUE,
    tel VARCHAR(15) NOT NULL,
    point INT NOT NULL DEFAULT 0,
    address VARCHAR(255) NULL,
    crate_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,  -- 생성일자
    modify_date TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,   -- 수정일자
    PRIMARY KEY (member_no)
);


CREATE TABLE mem_role (
    role_no INT NOT NULL AUTO_INCREMENT,              -- 권한 번호 (기본 키)
    name VARCHAR(15) NOT NULL,                        -- 권한 명
    description VARCHAR(100) NOT NULL,                -- 권한 내용
    PRIMARY KEY (role_no)
);

CREATE TABLE mem_member_and_role (
    member_no INT NOT NULL,                       -- 회원번호
    role_no INT NOT NULL,                         -- 권한 번호
    PRIMARY KEY (member_no, role_no),             -- 복합 기본 키
    FOREIGN KEY (member_no) REFERENCES mem_member(member_no), -- 회원번호 외래 키
    FOREIGN KEY (role_no) REFERENCES mem_role(role_no)        -- 권한 번호 외래 키
);

CREATE TABLE admin_notice (
    notice_no INT NOT NULL AUTO_INCREMENT,
    member_no INT NOT NULL,
    type_no INT NOT NULL,
    title VARCHAR(50) NOT NULL,
    content VARCHAR(3000) NOT NULL,
    img VARCHAR(100),
    crate_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modify_date TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (notice_no),
    FOREIGN KEY (member_no) REFERENCES mem_member(member_no),
    FOREIGN KEY (type_no) REFERENCES admin_notice_type(type_no)
);


CREATE VIEW find_pay_and_movie AS -- 마이페이지 예약 내역 조회 뷰
SELECT
	  ROW_NUMBER() OVER (ORDER BY movie_payments_no DESC) AS row_num -- 번호
	, mr.member_no -- 회원 번호
	, pmap.movie_title -- 영화 제목
	, mc.cinema_name -- 영화관
	, ms.screen_name -- 상영관
	, seat.seat_name -- 좌석 번호
	, pp.total_price -- 결제 금액
	, mms.start_time -- 시작 시간
	, mms.end_time -- 종료시간
	, mms.`date` -- 상영 일자
	, pp.day_date -- 예매 일자
FROM
	pay_movie_and_payments pmap
JOIN
	pay_payments pp ON pp.payments_no = pmap.payments_no
JOIN
	mov_reservation mr ON mr.rsv_no = pmap.rsv_no
JOIN
	mov_movie_schedule mms ON mms.schedule_no = mr.schedule_no
JOIN
	mov_cinema mc ON mc.cinema_no = mms.cinema_no
JOIN
	mov_screen ms ON ms.screen_code = mms.screen_code
JOIN
	mov_seat seat ON seat.seat_no = mr.seat_no


CREATE TABLE admin_notice_type (
type_no INT NOT NULL AUTO_INCREMENT,
description VARCHAR(20) NOT NULL,
PRIMARY KEY (type_no)
);

CREATE VIEW vw_find_screen_by_cinema AS -- 스크린 정보를 조회하기 위한 VIEW
SELECT
      cs.cinema_no -- 영화관 번호
    , cs.screen_code -- 스크린 코드
    , s.screen_name -- 스크린 네임
    , COUNT(sas.screen_code) AS total_seat -- 시트 개수
FROM
    mov_cinema_and_screen cs
JOIN
    mov_screen s ON cs.screen_code = s.screen_code
JOIN
    mov_screen_and_seat sas ON s.screen_code = sas.screen_code
GROUP BY
      cs.cinema_no
    , s.screen_name
    , s.is_avail;


    REATE VIEW vw_res_data2 AS
    SELECT
          r.rsv_no
        , r.schedule_no
        , r.seat_no
        , r.member_no
        , r.rsv_is_paid
        , m.poster_path
        , m.movie_title
        , sch.date
        , sch.start_time
        , sch.end_time
        , c.cinema_name
        , s.screen_name
        , m.adult
        , seat.seat_name
        , r.rsv_movie_price
    FROM
        mov_reservation r
    JOIN
        mov_movie_schedule sch ON r.schedule_no = sch.schedule_no
    JOIN
        mov_movie m ON sch.movie_no = m.movie_no
    JOIN
        mov_cinema c ON sch.cinema_no = c.cinema_no
    JOIN
        mov_screen s ON sch.screen_code = s.screen_code
    JOIN
        mov_screen_and_seat sas ON s.screen_code = sas.screen_code
    JOIN
        mov_seat seat ON sas.seat_no = seat.seat_no