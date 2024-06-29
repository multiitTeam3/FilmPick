package com.multi.mini.config;

import com.multi.mini.gpt.service.Assistance;
import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.output.Response;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ComponentScan(basePackages = "com.multi.mini")
public class ContextConfiguration {
	
	
	
	
	@Bean
	public OkHttpClient okHttpClient() {
		return new OkHttpClient();
		
	}
	
	
	@Bean
	ChatMemory chatMemory() {
		
		return MessageWindowChatMemory.withMaxMessages(40);
		
	}
	
	
	
}
