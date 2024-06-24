package com.multi.mini.admin.movie.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movie")
public class MovieControllerAdmin {
	
	
	
	@GetMapping("/moviemanage")
	public String movieManage() {
		
		return "/admin/movie/moviemanage";
	}
}
