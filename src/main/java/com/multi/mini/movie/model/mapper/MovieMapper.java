package com.multi.mini.movie.model.mapper;

import com.multi.mini.movie.model.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.Map;

@Mapper
public interface MovieMapper {


	ArrayList<MovieDTO> findMovieList() throws Exception;

	ArrayList<RegionDTO> findRegionList() throws Exception;

	ArrayList<CinemaDTO> findCinemaListByRegionNo(int regionNo) throws Exception;

	ArrayList<MovieScheduleDTO> findScheduleListByCinemaNo(int cinemaNo) throws  Exception;

	MovieDTO findMovieByMovieNo(int movieNo) throws Exception;
	
	ArrayList<SeatDTO> findAllSeat() throws Exception;
	
	
	ArrayList<SeatDTO> findReservedSeat(MovieScheduleDTO movieScheduleDTO) throws Exception;
	
	ArrayList<SeatDTO> findSeatListByScreen(String screenCode) throws Exception;
	
	int insertReservation(ReservationDTO reservationDTO) throws Exception;
	
	int findReservationNo(ReservationDTO reservationDTO) throws Exception;
	
	ArrayList<VWResDataGroupDTO> findAllReservationByMemberNo(Map<String, Object> params) throws Exception;
	
	int findAllReservationCountByMemberNo(int memberNO) throws Exception;
	
	ArrayList<VWResDataGroupDTO> findAllNotPaidReservationByMemberNo(Map<String, Object> params);
	
	int findAllNotPaidReservationCountByMemberNo(int memberNO) throws Exception;
	
	ArrayList<Integer> findRsvNoListByVWGroupDTO(VWResDataGroupDTO vwResDataGroupDTO) throws Exception;
	
	ArrayList<VWRewDataDTO> findVWReviewListByMovieNo(Map<String, Object> params) throws Exception;
	
	int findAllReviewCountByMovieNo(int movieNo) throws Exception;
	
	int insertReview(ReviewDTO reviewDTO) throws Exception;
	
	
	
	ArrayList<VWMovieManageDataDTO> findAllEnrolledMovie(MoviePageDTO moviePageDTO) throws Exception;
	
	int findAllEnrolledMovieCount() throws Exception;
	
	void insertGenre(GenreDTO genreDTO) throws Exception;
	
	void insertAPIMovie(MovieDTO movieDTO) throws Exception;

	ArrayList<CinemaDTO> findCinemaList(@Param("type") String type, @Param("keyword") String keyword, @Param("start") int start, @Param("end") int end) throws Exception;
	
	ArrayList<VWResDataDTO> reservationPayment(int rsvNo) throws Exception;

	VWResDataDTO vwResDataNo(int rsvNo) throws Exception;
	
	
	int deleteReservation(ReservationDTO reservationDTO) throws Exception;
	
	VWResDataGroupDTO findVWResDataGroupDTO(VWResDataGroupDTO vwResDataGroupDTO) throws Exception;
	
	ArrayList<VWMovieManageDataDTO> findAllVWMovieManageDataDTO() throws Exception;

	ArrayList<CinemaDTO> findCinemaList() throws Exception;

	@Select("SELECT review_no FROM mov_movie_review WHERE member_no = #{ memberNo } AND movie_no = #{ movieNo } LIMIT 1")
    ReviewDTO findReviewByMemberNo(ReviewDTO reviewDTO);

	@Select("SELECT * FROM find_pay_and_movie WHERE member_no = #{ memberNo } LIMIT #{ start }, #{ end }")
    ArrayList<MyPageReservationDetailsDTO> findMyMovieReservationsByMemberNo(@Param("memberNo") int memberNo, @Param("start") int start, @Param("end") int end);
}
