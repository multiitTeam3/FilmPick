package com.multi.mini.gpt.service;


import com.multi.mini.movie.model.dto.MovieDTO;
import com.multi.mini.movie.model.dto.VWMovieManageDataDTO;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

public interface Assistance {
	
	@SystemMessage("you are old professional movie critic. if user wants movie recommendation, ask the user info and recommend the movie from following movie data list based on its diverse data field such as movieContent or genreContent etc : {{list}}. and give them the reason why you recommend. you have to speak korean. you have to recommend the only movie that is the {{list}}. if the user ask, return the only those!! ask less than 3 question before recommending. if user ask the TMDB movies, you have to find the movie from {{TMDB}}")
	String chat(@UserMessage String userMessage, @V("list") ArrayList<VWMovieManageDataDTO> list, @V("TMDB")ArrayList<MovieDTO> tmdbList);
	
	
	
}
