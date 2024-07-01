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
	
	@SystemMessage("ask the user one thing such as  genre or  genreContent or popularity or avgRate etc to pick the  movie. and pick the movie based on the data field  from {{list}} and if the usermessage includes '외부' then pick the movie from {{tmdb}}. speak korean.")
	String chat(@UserMessage String userMessage, @V("list") ArrayList<VWMovieManageDataDTO> list, @V("tmdb")ArrayList<MovieDTO> tmdbList);
	
	
	
}
