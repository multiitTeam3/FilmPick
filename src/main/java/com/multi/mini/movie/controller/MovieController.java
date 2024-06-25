package com.multi.mini.movie.controller;

import com.multi.mini.movie.model.dto.CinemaDTO;
import com.multi.mini.movie.model.dto.MovieDTO;
import com.multi.mini.movie.model.dto.MovieScheduleDTO;
import com.multi.mini.movie.model.dto.RegionDTO;
import com.multi.mini.movie.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	
	
	@RequestMapping("/findRegionList")
	@ResponseBody
	public ArrayList<RegionDTO> findRegionList(){
		
		ArrayList<RegionDTO> list = null;
		try {
			list = movieService.findRegionList();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("list : "+list);
		
		return list;
		
	}
	
	
	@RequestMapping("/findCinemaListByRegionNo")
	@ResponseBody
	public ArrayList<CinemaDTO> findCinemaListByRegionNo(@RequestBody RegionDTO regionDTO){
		
		ArrayList<CinemaDTO> list = null;
		try {
			list = movieService.findCinemaListByRegionNo(regionDTO.getRegionNo());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("list : "+list);
		
		return list;
		
	}
	
	
	@RequestMapping("/findScheduleListByCinemaNo")
	@ResponseBody
	public ArrayList<MovieScheduleDTO> findScheduleListByCinemaNo(@RequestBody CinemaDTO cinemaDTO){
		
		ArrayList<MovieScheduleDTO> list = null;
		try {
			list = movieService.findScheduleListByCinemaNo(cinemaDTO.getCinemaNo());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("list : "+list);
		
		return list;
		
	}
	
	
	@RequestMapping("/findMovieByMovieNo")
	@ResponseBody
	public MovieDTO findMovieByMovieNo(@RequestBody MovieDTO movieDTO){
		
		MovieDTO movieDTO2  = null;
		try {
			movieDTO2 = movieService.findMovieByMovieNo(movieDTO.getMovieNo());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("moviedto : "+ movieDTO2);
		
		return movieDTO2;
		
	}
	
	
	
	
}
