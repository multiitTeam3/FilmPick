package com.multi.mini.gpt.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.multi.mini.gpt.service.Assistance;
import com.multi.mini.gpt.service.GPTToolContainer;
import com.multi.mini.movie.model.dto.MovieDTO;
import com.multi.mini.movie.model.dto.VWMovieManageDataDTO;
import com.multi.mini.movie.service.MovieService;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.ServiceOutputParser;
import lombok.AllArgsConstructor;
import okhttp3.OkHttpClient;
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
	
	
	
	private static final int MAX_TOKENS = 4000;
	
	
	public GPTController(MovieService movieService) {
		this.movieService = movieService;

		
	}
	
	/*ChatLanguageModel model = OpenAiChatModel.withApiKey("sk-proj-TYaUQGWfZaStsphKrV4sT3BlbkFJbJYWDn4q0uXiG1pWWw0C");
	
	Assistance assistance = AiServices.create(Assistance.class, model);*/
	
	
	
/*	Assistance assistance = AiServices.builder(Assistance.class)
			.chatLanguageModel(OpenAiChatModel.withApiKey("sk-proj-TYaUQGWfZaStsphKrV4sT3BlbkFJbJYWDn4q0uXiG1pWWw0C"))
			.chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(20))
			.build();*/
	
	
	OpenAiChatModel model = OpenAiChatModel.builder()
			.apiKey("sk-proj-TYaUQGWfZaStsphKrV4sT3BlbkFJbJYWDn4q0uXiG1pWWw0C")
			.modelName("gpt-3.5-turbo")
			.temperature(0.7)
			.build();
	
	/*Assistance assistance = AiServices.create(Assistance.class, model);*/
	
	OkHttpClient client = new OkHttpClient();
	ObjectMapper objectMapper = new ObjectMapper();
	Gson gson = new Gson();
	
	
	GPTToolContainer gptToolContainer = new GPTToolContainer(client, objectMapper, gson);
	
	Assistance assistance = AiServices.builder(Assistance.class)
			.chatLanguageModel(model)
			.chatMemory(MessageWindowChatMemory.withMaxMessages(30))
			.tools(gptToolContainer)
			.build();
	
	
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
		
		ArrayList<MovieDTO> tmdbList = null;
		try {
			tmdbList = movieService.findAllAPIMovieList();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("tmdbList : " + tmdbList);
		
		
		String answer = assistance.chat(question, list, tmdbList);
		
		return answer;
		
		
	}
	
	
}
