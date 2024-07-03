-- New script in cafe.
-- Date: 2024. 6. 20.
-- Time: 오후 7:34:42
--mini.cxwee6aeygsa.ap-northeast-2.rds.amazonaws.com                - target database host
--mini.cxwee6aeygsa.ap-northeast-2.rds.amazonaws.com         - tunnel host name
--3306                - target database port
--${server}              - target server name
--cafe            - target database name
--dkswl                - database user name
--jdbc:mysql://mini.cxwee6aeygsa.ap-northeast-2.rds.amazonaws.com:3306/cafe                 - connection URL
--dev     - connection type
--cafe          - datasource
--C:\Users\dkswl\OneDrive\Documents\code_upload\Auto_window\multi_it\backend\db\db1\General        - project path
--General        - project name
--2024. 6. 20.                - current date
--C:\Users\dkswl\OneDrive\Documents\code_upload\Auto_window\multi_it\backend\db\db1           - workspace path
--C:\Users\dkswl                - OS user home path
--C:\Users\dkswl\AppData\Local\DBeaver        - application install path
--C:\Users\dkswl\AppData\Local\DBeaver    - application install path
--DBeaver    - application name
--24.0.3.202404211624 - application version
--220.78.96.123            - local IP address
--2024. 6. 20.                - current date
--오후 7:34:42                - current time
--dkswl                - OS user name



DROP TABLE MEMBER CASCADE;



CREATE TABLE `mov_region` (
	`region_no`	INT	AUTO_INCREMENT PRIMARY KEY,
	`region_name`	VARCHAR(30)	NOT NULL,
	`regioni_code`	VARCHAR(30)	NOT NULL
);


CREATE TABLE `mov_cienma` (
	`cinema_no`	INT	AUTO_INCREMENT PRIMARY KEY,
	`cinema_name`	VARCHAR(30)	NOT NULL,
	`region_no`	INT	NOT NULL,
	`cinema_addr`	VARCHAR(50)	NOT NULL,
	`open_time`	VARCHAR(50)	NOT NULL,
	`close_time`	VARCHAR(50)	NOT NULL
);

CREATE TABLE `mov_genre` (
	`genre_no`	INT AUTO_INCREMENT PRIMARY KEY,
	`genre_content`	VARCHAR(30)	NOT NULL
);

CREATE TABLE `mov_movie` (
	`movie_no`	INT	AUTO_INCREMENT PRIMARY KEY,
	`movie_title`	VARCHAR(30)	NOT NULL UNIQUE,
	`genre_no`	INT	NOT NULL,
	`popularity`	DOUBLE,
	`duration`	INT	NOT NULL,
	`original_language`	VARCHAR(30),
	`movie_content`	VARCHAR(255)	NOT NULL,
	`adult`	BOOLEAN	DEFAULT FALSE,
	`poster_path`	VARCHAR(255),
	`create_date`	TIMESTAMP	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`modify_date`	TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP
);




DROP TABLE `SCREEN`;

CREATE TABLE `mov_screen` (
	`screen_code`	VARCHAR(30)	PRIMARY KEY,
	`screen_name`	VARCHAR(30)	NOT NULL,
	`total_seat_num`	INT	NOT NULL,
	`is_avail`	VARCHAR(30)	NOT NULL DEFAULT  'Y'
);


CREATE TABLE `mov_seat` (
	`seat_no`	INT	AUTO_INCREMENT PRIMARY KEY,
	`seat_name`	VARCHAR(30)	NOT NULL
);



CREATE TABLE `mov_movie_schedule` (
	`schedule_no`	INT	AUTO_INCREMENT PRIMARY KEY,
	`movie_no`	INT	NOT NULL,
	`cinema_no`	INT	NOT NULL,
	`screen_no`	VARCHAR(30)	NOT NULL,
	`date`	VARCHAR(30)	NOT NULL,
	`start_time`	VARCHAR(30)	NOT NULL,
	`end_time`	VARCHAR(30)	NOT NULL
);

DROP TABLE `MOVIE_REVIEW`;

CREATE TABLE `mov_movie_review` (
	`review_no`	INT	AUTO_INCREMENT PRIMARY KEY,
	`movie_no`	INT	NOT NULL,
	`member_no`	INT	NOT NULL,
	`content`	VARCHAR(255)	NOT NULL,
	`rate`	INT	NOT NULL,
	`create_date` TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `mov_cinema_and_screen` (
	`cinema_no`	INT	 AUTO_INCREMENT,
	`screen_code`	VARCHAR(30)	NOT NULL,
	PRIMARY KEY(`cinema_no`, `screen_code`)
);

DROP TABLE `CINEMA_AND_SCREEN`;


DROP TABLE `mov_screen_and_seat`;

CREATE TABLE `mov_screen_and_seat` (
	`screen_code`	VARCHAR(30),
	`seat_no`	INT	NOT NULL,
	PRIMARY KEY(`screen_code`, `seat_no`)
);


CREATE TABLE `mov_reservation` (
	`rsv_no`	INT	AUTO_INCREMENT PRIMARY KEY,
	`schedule_no`	INT	NOT NULL,
	`seat_no`	INT	NOT NULL,
	`member_no`	INT	NOT NULL,
	`rsv_movie_price`	INT	NOT NULL,
	`rsv_is_paid`	VARCHAR(2)	NOT NULL,
	`create_date`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`modify_date`	TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);



ALTER TABLE `mov_cinema` ADD CONSTRAINT `FK_REGION_TO_CINEMA_1` FOREIGN KEY (
	`region_no`
)
REFERENCES `mov_region` (
	`region_no`
);

ALTER TABLE `mov_movie` ADD CONSTRAINT `FK_GENRE_TO_MOVIE_1` FOREIGN KEY (
	`genre_no`
)
REFERENCES `mov_genre` (
	`genre_no`
);

ALTER TABLE `mov_movie_schedule` ADD CONSTRAINT `FK_MOVIE_TO_MOVIE_SCHEDULE_1` FOREIGN KEY (
	`movie_no`
)
REFERENCES `mov_movie` (
	`movie_no`
);

ALTER TABLE `mov_movie_schedule` ADD CONSTRAINT `FK_CINEMA_TO_MOVIE_SCHEDULE_1` FOREIGN KEY (
	`cinema_no`
)
REFERENCES `mov_cinema` (
	`cinema_no`
);

ALTER TABLE `mov_movie_schedule` ADD CONSTRAINT `FK_SCREEN_TO_MOVIE_SCHEDULE_1` FOREIGN KEY (
	`screen_code`
)
REFERENCES `mov_screen` (
	`screen_code`
);

ALTER TABLE `mov_movie_review` ADD CONSTRAINT `FK_MOVIE_TO_MOVIE_REVIEW_1` FOREIGN KEY (
	`movie_no`
)
REFERENCES `mov_movie` (
	`movie_no`
);

ALTER TABLE `mov_movie_review` ADD CONSTRAINT `FK_MEMBER_TO_MOVIE_REVIEW_1` FOREIGN KEY (
	`member_no`
)
REFERENCES `mem_member` (
	`member_no`
);

ALTER TABLE `mov_cinema_and_screen` ADD CONSTRAINT `FK_CINEMA_TO_CINEMA_AND_SCREEN_1` FOREIGN KEY (
	`cinema_no`
)
REFERENCES `mov_cinema` (
	`cinema_no`
);

ALTER TABLE `mov_cinema_and_screen` ADD CONSTRAINT `FK_SCREEN_TO_CINEMA_AND_SCREEN_1` FOREIGN KEY (
	`screen_code`
)
REFERENCES `mov_screen` (
	`screen_code`
);

ALTER TABLE `mov_screen_and_seat` ADD CONSTRAINT `FK_SCREEN_TO_SCREEN_AND_SEAT_1` FOREIGN KEY (
	`screen_code`
)
REFERENCES `mov_screen` (
	`screen_code`
);

ALTER TABLE `mov_screen_and_seat` ADD CONSTRAINT `FK_SEAT_TO_SCREEN_AND_SEAT_1` FOREIGN KEY (
	`seat_no`
)
REFERENCES `mov_seat` (
	`seat_no`
);

ALTER TABLE `mov_reservation` ADD CONSTRAINT `FK_MOVIE_SCHEDULE_TO_RESERVATION_1` FOREIGN KEY (
	`schedule_no`
)
REFERENCES `mov_movie_schedule` (
	`schedule_no`
);

ALTER TABLE `mov_reservation` ADD CONSTRAINT `FK_SEAT_TO_RESERVATION_1` FOREIGN KEY (
	`seat_no`
)
REFERENCES `mov_seat` (
	`seat_no`
);

ALTER TABLE `mov_reservation` ADD CONSTRAINT `FK_MEMBER_TO_RESERVATION_1` FOREIGN KEY (
	`member_no`
)
REFERENCES `mem_member` (
	`member_no`
);


CREATE VIEW vw_res_data AS
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

WHERE
	r.seat_no = seat.seat_no;


SELECT * FROM vw_res_data WHERE member_no = 5;



CREATE VIEW vw_res_group_data AS
SELECT
	r.schedule_no
	, r.member_no
	, m.poster_path
	, m.movie_no
    , m.movie_title
    , r.rsv_is_paid
    , sch.date
    , sch.start_time
    , sch.end_time
    , c.cinema_name
    , s.screen_name
    , m.adult
    , SUM(CASE WHEN r.rsv_movie_price = 12000 THEN 1 ELSE 0 END) AS adult_num
    , SUM(CASE WHEN r.rsv_movie_price = 8000 THEN 1 ELSE 0 END) AS teen_num
    , GROUP_CONCAT(seat.seat_name ORDER BY seat.seat_name SEPARATOR ',') AS seat_names
    , SUM(r.rsv_movie_price) AS total_price

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
	WHERE
	    r.seat_no = seat.seat_no
	GROUP BY
	    r.schedule_no
	    , r.member_no
	    , m.poster_path
	    , m.movie_title
	    , sch.date
	    , sch.start_time
	    , sch.end_time
	    , c.cinema_name
	    , s.screen_name;




CREATE VIEW vw_rew_data AS
SELECT
	r.*

	, m.movie_title
	, m.poster_path
    , mem.user_name

FROM mov_reservation r

JOIN mov_movie m ON r.movie_no = m.movie_no
JOIN mem_member mem ON r.member_no = mem.member_no

WHERE
	r.movie_no = m.movie_no;



CREATE VIEW vw_reser2_data AS
SELECT
	r.*

	, m.movie_title
	, m.poster_path
    , mem.user_name
    , sch.start_time
    , sch.end_time
    , s.seat_name

FROM mov_reservation r
JOIN mov_movie_schedule sch on r.schedule_no = sch.schedule_no
JOIN mov_movie m ON sch.movie_no = m.movie_no
JOIN mem_member mem ON r.member_no = mem.member_no
JOIN mov_seat s ON s.seat_no = r.seat_no

WHERE
	sch.schedule_no = r.schedule_no
AND
	s.seat_no = r.seat_no;





CREATE VIEW vw_movie_manage_data AS
SELECT
	movie.movie_no,
	movie.movie_title,
	movie.genre_no,
	movie.popularity,
	movie.duration,
	movie.original_language,
	movie.movie_content,
	movie.adult,
	movie.poster_path
	, genre.genre_content
	, AVG(review.rate) avg_rate
	, COUNT(reservation.rsv_no) ticket_sold

FROM
	mov_movie movie

JOIN
	mov_genre genre on movie.genre_no = genre.genre_no

JOIN
	mov_movie_review review ON movie.movie_no = review.movie_no

JOIN
	mov_movie_schedule sch ON movie.movie_no = sch.movie_no

JOIN
	mov_reservation reservation ON reservation.schedule_no = sch.schedule_no



GROUP BY sch.movie_no,
	movie.movie_title,
	movie.genre_no,
	movie.popularity,
	movie.duration,
	movie.original_language,
	movie.movie_content,
	movie.adult,
	movie.poster_path
	, genre.genre_content;




select count(*) from mov_reservation m
join mov_movie_schedule mms  on m.schedule_no =mms.schedule_no
group by mms.movie_no  ;


CREATE VIEW vw_agg_reviews AS

SELECT
	movie_no,
	AVG(rate) AS avg_rate
FROM
	mov_movie_review review
GROUP BY
	movie_no;




CREATE VIEW vw_movie_manage_data AS
SELECT
	movie.movie_no,
	movie.movie_title,
	movie.genre_no,
	movie.popularity,
	movie.duration,
	movie.original_language,
	movie.movie_content,
	movie.adult,
	movie.poster_path
	, genre.genre_content
	, review.avg_rate
	, COUNT(reservation.rsv_no) ticket_sold

FROM
	mov_movie movie

JOIN
	mov_genre genre ON movie.genre_no = genre.genre_no

LEFT join
	mov_movie_schedule sch ON sch.movie_no = movie.movie_no

LEFT join
	mov_reservation reservation ON sch.schedule_no = reservation.schedule_no

LEFT JOIN
	vw_agg_reviews review ON movie.movie_no = review.movie_no

GROUP BY movie.movie_no,
		review.avg_rate;



// is_avail 추가함
CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `movie`.`vw_find_screen_by_cinema` AS
SELECT
    `cs`.`cinema_no` AS `cinema_no`
    , `cs`.`screen_code` AS `screen_code`
    , `cs`.`is_avail` AS `is_avail`
    , `s`.`screen_name` AS `screen_name`
    , count(`sas`.`screen_code`) AS `total_seat`
FROM
    (
        (
            `movie`.`mov_cinema_and_screen` `cs`
        JOIN `movie`.`mov_screen` `s` ON
            (
                (
                    `cs`.`screen_code` = `s`.`screen_code`
                )
            )
        )
    JOIN `movie`.`mov_screen_and_seat` `sas` ON
        (
            (
                `s`.`screen_code` = `sas`.`screen_code`
            )
        )
    )
GROUP BY
    `cs`.`cinema_no`
    , `s`.`screen_name`;

















