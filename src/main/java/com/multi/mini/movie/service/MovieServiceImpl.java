package com.multi.mini.movie.service;

import com.multi.mini.movie.model.dto.MovieDTO;
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
}
