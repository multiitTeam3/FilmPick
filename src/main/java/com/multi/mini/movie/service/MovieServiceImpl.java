package com.multi.mini.movie.service;

import com.multi.mini.movie.model.dto.CinemaDTO;
import com.multi.mini.movie.model.dto.MovieDTO;
import com.multi.mini.movie.model.dto.MovieScheduleDTO;
import com.multi.mini.movie.model.dto.RegionDTO;
import com.multi.mini.movie.model.mapper.MovieMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("movieService")
public class MovieServiceImpl implements MovieService{
	
	private final MovieMapper movieMapper;
	
	public MovieServiceImpl(MovieMapper movieMapper) {
		this.movieMapper = movieMapper;
	}
	
	@Override
	public ArrayList<MovieDTO> findMovieList() throws Exception {
		
		ArrayList<MovieDTO> list = movieMapper.findMovieList();
		
		return list;
	}
	
	@Override
	public ArrayList<RegionDTO> findRegionList() throws Exception {
		ArrayList<RegionDTO> list = movieMapper.findRegionList();
		
		return list;
	}
	
	@Override
	public ArrayList<CinemaDTO> findCinemaListByRegionNo(int regionNo) throws Exception {
		
		ArrayList<CinemaDTO> list = movieMapper.findCinemaListByRegionNo(regionNo);
		
		return list;
		
		
	}
	
	@Override
	public ArrayList<MovieScheduleDTO> findScheduleListByCinemaNo(int cinemaNo) throws Exception {
		
		ArrayList<MovieScheduleDTO> list = movieMapper.findScheduleListByCinemaNo(cinemaNo);
		
		return list;
		
	}
	
	@Override
	public MovieDTO findMovieByMovieNo(int movieNo) throws Exception {
		
		MovieDTO movieDTO = movieMapper.findMovieByMovieNo(movieNo);
		
		return movieDTO;
		
		
	}















	@Override
	public ArrayList<CinemaDTO> findCinemaList() throws Exception {
		ArrayList<CinemaDTO> list = movieMapper.findCinemaList();

		return list;
	}
}
