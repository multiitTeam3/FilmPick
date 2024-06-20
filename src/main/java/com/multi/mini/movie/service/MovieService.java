package com.multi.mini.movie.service;

import com.multi.mini.movie.model.dto.MovieDTO;

import java.util.ArrayList;

public interface MovieService {
	
	ArrayList<MovieDTO> findMovieList() throws Exception;
	
	
	
}
