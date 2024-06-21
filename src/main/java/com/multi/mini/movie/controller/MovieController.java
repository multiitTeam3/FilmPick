package com.multi.mini.movie.controller;

import com.multi.mini.movie.model.dto.MovieDTO;
import com.multi.mini.movie.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("/movie")
public class MovieController {
	
	private final MovieService movieService;
	
	
	public MovieController(MovieService movieService){
		super();
		this.movieService = movieService;
		
	}
	
	
	@RequestMapping("/findMovieList")
	@ResponseBody
	public ArrayList<MovieDTO> findImgList(){
		
		ArrayList<MovieDTO> list = null;
		try {
			list = movieService.findMovieList();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return list;
		
	}
	
	@GetMapping("/reservationtime")
	public void movieReservationTime(){
	
	
	
	}
	
	
	
}
