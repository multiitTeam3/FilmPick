package com.multi.mini.gpt.controller;




import com.multi.mini.gpt.service.Assistance;
import com.multi.mini.movie.model.dto.VWMovieManageDataDTO;
import com.multi.mini.movie.service.MovieService;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.spring.AiService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

import java.util.ArrayList;

@Controller
@RequestMapping("/gpt")
public class GPTController {
	
	/*@Autowired*/
	/*private Assistance assistance;*/
	
	/*@Autowired*/
	private MovieService movieService;
	

	public GPTController(/*Assistance assistance, */MovieService movieService) {
		/*this.assistance = assistance;*/
		this.movieService = movieService;
	}
	
	ChatLanguageModel model = OpenAiChatModel.withApiKey("sk-proj-TYaUQGWfZaStsphKrV4sT3BlbkFJbJYWDn4q0uXiG1pWWw0C");
	
	Assistance assistance = AiServices.create(Assistance.class, model);
	
	@PostMapping("/chatBot")
	@ResponseBody
	public String gptResponse(@RequestBody String question) {
		
		System.out.println("question : " + question);
		
		
		
		
		ArrayList<VWMovieManageDataDTO> list = null;
		try {
			list = movieService.findAllVWMovieManageDataDTO();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("ai use list : " + list);
		
		/*return assistance.chat(question, list);*/
		
		
		
		/*return chatLanguageModel.generate(question);*/
		
		/*return "whny/?";*/
		
		
		String answer = assistance.chat(question, list);
		
		return answer;
		
		
	}
	
	
}
