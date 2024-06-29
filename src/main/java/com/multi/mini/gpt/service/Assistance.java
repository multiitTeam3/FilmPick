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
	
	@SystemMessage("if user wants the movie recommendation and usermessage is not including '다양한' or diverse, you have to recommed the movie from {{list}} and add the '(database)' in the end of the sentence. you must not recommend other movie. but if user request includes '다양한', you have to select the movie from {{tmbv}} and add the '(tmdb)' in the end of the sentence.")
	String chat(@UserMessage String userMessage, @V("list") ArrayList<VWMovieManageDataDTO> list, @V("tmbv")ArrayList<MovieDTO> tmdbList);
	
	
	
}
