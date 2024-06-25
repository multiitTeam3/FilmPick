package com.multi.mini.admin.movie.service;

import com.multi.mini.movie.model.dto.MovieDTO;
import com.multi.mini.movie.model.dto.MoviePageDTO;
import com.multi.mini.movie.model.dto.VWMovieManageDataDTO;

import java.util.ArrayList;

public interface MovieServiceAdmin {
	ArrayList<VWMovieManageDataDTO> findAllEnrolledMovie(MoviePageDTO moviePageDTO) throws Exception;
	
	int findAllEnrolledMovieCount() throws Exception;
	
	void insertGenre() throws Exception;
	
	ArrayList<MovieDTO> findAPIMovieList(int page) throws Exception;
	
	void insertAPIMovie(int id) throws Exception;
}
