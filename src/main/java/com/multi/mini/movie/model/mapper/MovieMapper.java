package com.multi.mini.movie.model.mapper;

import com.multi.mini.movie.model.dto.MovieDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface MovieMapper {
	
	
	ArrayList<MovieDTO> findMovieList() throws Exception;
}
