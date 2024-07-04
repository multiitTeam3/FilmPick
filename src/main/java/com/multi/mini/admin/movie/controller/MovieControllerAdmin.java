package com.multi.mini.admin.movie.controller;


import com.multi.mini.admin.movie.service.MovieServiceAdmin;
import com.multi.mini.movie.model.dto.EnrolledMovieResponseDTO;
import com.multi.mini.movie.model.dto.MovieDTO;
import com.multi.mini.movie.model.dto.MoviePageDTO;
import com.multi.mini.movie.model.dto.VWMovieManageDataDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/admin/movie")
public class MovieControllerAdmin {
	
	private final MovieServiceAdmin movieServiceAdmin;
	private final ArrayList<MovieDTO> list;
	
	
	public MovieControllerAdmin(MovieServiceAdmin movieServiceAdmin, ArrayList<MovieDTO> list) {
		super();
		this.movieServiceAdmin = movieServiceAdmin;
		this.list = list;
	}
	
	@PostMapping("/findAllEnrolledMovie")
	@ResponseBody
	public EnrolledMovieResponseDTO findAllEnrolledMovie(@RequestParam("page") int page ) {
		
		MoviePageDTO moviePageDTO = new MoviePageDTO();
		moviePageDTO.setPage(page);
		moviePageDTO.setStartEnd(moviePageDTO.getPage());
		System.out.println("moviePageDTO.getPage()" + moviePageDTO.getPage());
		
		
		ArrayList<VWMovieManageDataDTO> list = null;
		
		EnrolledMovieResponseDTO enrolledMovieResponseDTO = null;
		
		try {
			list = movieServiceAdmin.findAllEnrolledMovie(moviePageDTO);
			
			int count = movieServiceAdmin.findAllEnrolledMovieCount();
			
			System.out.println("count : " + count);
			
			int pages =0;
			
			if (count%3==0){
				
				pages =(int) (count / 3);
				
				
			}else {
				
				pages =(int) (count / 3) + 1 ;
				
			}
			
			System.out.println(pages);
			
			enrolledMovieResponseDTO = new EnrolledMovieResponseDTO();
			
			enrolledMovieResponseDTO.setList(list);
			enrolledMovieResponseDTO.setPages(pages);
			enrolledMovieResponseDTO.setCount(count);
			
			
			
			
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return enrolledMovieResponseDTO;
		
		
	}
	
	
	@GetMapping("/moviemanage")
	public String movieManage() {
		
		return "/admin/movie/moviemanage";
	}
	
	
	
	@PostMapping("/insertGenre")
	@ResponseBody
	public String insertGenre(){
		
		String result="";
		
		
		try {
			movieServiceAdmin.insertGenre();
			result= "장르 삽입 성공~";
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
		return result;
		
	}
	

	@PostMapping("/insertAPIMovie")
	@ResponseBody
	public String insertAPIMovie(@RequestParam("id") String id){
		
		String result="";
		
		
		try {
			movieServiceAdmin.insertAPIMovie(Integer.parseInt(id));
			result= "영화 삽입 성공~";
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
		return result;
		
	}
	
	
	@PostMapping("/findAPIMovieList")
	@ResponseBody
	public ArrayList<MovieDTO> findAPIMovieList(@RequestParam("page") int page){
		
		ArrayList<MovieDTO> list = null;
		
		
		try {
			list = movieServiceAdmin.findAPIMovieList(page);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
		return list;
		
	}
}
