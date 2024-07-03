package com.multi.mini.gpt.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.langchain4j.agent.tool.Tool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;

@Component
public class GPTToolContainer {
	
	
	private final OkHttpClient client;
	private final ObjectMapper objectMapper;
	private final Gson gson;
	
	
	public GPTToolContainer(OkHttpClient client, ObjectMapper objectMapper, Gson gson) {
		
		this.client = client;
		this.objectMapper = objectMapper;
		this.gson = gson;
	}
	
	@Tool("내가 말하는 주소의 날씨에 따라 영화 추천해줘")
	public String weatherGet(String location) {
		
		System.out.println("weatherGet activated");
		Request request = new Request.Builder()
				.url("https://dapi.kakao.com/v2/local/search/address.json?query=" + location)
				.get()
				.addHeader("accept", "application/json")
				.addHeader("Authorization", "KakaoAK 06f586e59d41aa7806785d4c625887ee")
				.build();
		
		Response response = null;
		ArrayList<Double> list = new ArrayList<>();
		
		
		try {
			response = client.newCall(request).execute();
			String responseBody = response.body().string();
			JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
			JsonArray documents = jsonObject.getAsJsonArray("documents");
			
			
			
			JsonObject add1 = documents.get(0).getAsJsonObject();
			
			
			double lat = add1.get("y").getAsDouble();
			double lon = add1.get("x").getAsDouble();
			
			
			list.add(lat);
			list.add(lon);
			
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("weather x,y : " + list);
		
		
		Request request2 = new Request.Builder()
				.url("https://api.openweathermap.org/data/2.5/weather?lat=" + list.get(0) + "&lon=" + list.get(1) + "&appid=db829ead36ad5599e41574163a3b1549")
				.get()
				.build();
		
		
		Response response2 = null;
		String main = "";
		
		try {
			response2 = client.newCall(request2).execute();
			String responseBody = response2.body().string();
			JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
			JsonArray weathers = jsonObject.getAsJsonArray("weather");
			JsonObject weather = weathers.get(0).getAsJsonObject();
			
			main = weather.get("main").getAsString();
			
			
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("weather main : " + main);
		
		return main;
		
	}
	
}
