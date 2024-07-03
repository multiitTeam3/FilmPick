package com.multi.mini.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.multi.mini")
public class ContextConfiguration {
	
	
	
	
	@Bean
	public OkHttpClient okHttpClient() {
		return new OkHttpClient();
		
	}
	
	@Bean
	public ObjectMapper objectMapper(){
		return new ObjectMapper();
		
	}
	
	@Bean
	public Gson gson(){
		
		return new Gson();
		
	}
	
	
	@Bean
	ChatMemory chatMemory() {
		
		return MessageWindowChatMemory.withMaxMessages(40);
		
	}
	

	
	
	
}
