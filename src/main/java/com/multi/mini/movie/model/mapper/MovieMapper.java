package com.multi.mini.movie.model.mapper;

import com.multi.mini.movie.model.dto.CinemaDTO;
import com.multi.mini.movie.model.dto.MovieDTO;
import com.multi.mini.movie.model.dto.MovieScheduleDTO;
import com.multi.mini.movie.model.dto.RegionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface MovieMapper {
	
	
	ArrayList<MovieDTO> findMovieList() throws Exception;
	
	ArrayList<RegionDTO> findRegionList() throws Exception;
	
	ArrayList<CinemaDTO> findCinemaListByRegionNo(int regionNo) throws Exception;
	
	ArrayList<MovieScheduleDTO> findScheduleListByCinemaNo(int cinemaNo) throws  Exception;
	
	MovieDTO findMovieByMovieNo(int movieNo) throws Exception;
}
