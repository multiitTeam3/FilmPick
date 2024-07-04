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

