package com.multi.mini.movie.service;

import com.multi.mini.movie.model.dto.*;

import java.util.ArrayList;

public interface MovieService {
	
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
}
