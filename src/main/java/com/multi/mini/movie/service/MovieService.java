package com.multi.mini.movie.service;

import com.multi.mini.movie.model.dto.CinemaDTO;
import com.multi.mini.movie.model.dto.MovieDTO;
import com.multi.mini.movie.model.dto.MovieScheduleDTO;
import com.multi.mini.movie.model.dto.RegionDTO;

import java.util.ArrayList;

public interface MovieService {
	
	ArrayList<MovieDTO> findMovieList() throws Exception;
	
	
	ArrayList<RegionDTO> findRegionList() throws Exception;
	
	ArrayList<CinemaDTO> findCinemaListByRegionNo(int regionNo) throws Exception;
	
	ArrayList<MovieScheduleDTO> findScheduleListByCinemaNo(int cinemaNo) throws  Exception;
	
	MovieDTO findMovieByMovieNo(int movieNo) throws Exception;









	ArrayList<CinemaDTO> findCinemaList() throws Exception;
}
