package com.multi.mini.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.multi.mini")
public class ContextConfiguration {
	
	
	@Bean
	public OkHttpClient okHttpClient(){
		return new OkHttpClient();
		
	}
}
