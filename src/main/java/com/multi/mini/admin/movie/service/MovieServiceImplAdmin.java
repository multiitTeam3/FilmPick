package com.multi.mini.admin.movie.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.multi.mini.movie.model.dto.GenreDTO;
import com.multi.mini.movie.model.dto.MovieDTO;
import com.multi.mini.movie.model.dto.MoviePageDTO;
import com.multi.mini.movie.model.dto.VWMovieManageDataDTO;
import com.multi.mini.movie.model.mapper.MovieMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service("movieServiceAdmin")
public class MovieServiceImplAdmin implements MovieServiceAdmin{
	
	private final MovieMapper movieMapper;
	private final OkHttpClient client;
	private final ObjectMapper objectMapper;
	private final Gson gson;
	
	ArrayList<MovieDTO> list = new ArrayList<>();
	
	@Autowired
	public MovieServiceImplAdmin(MovieMapper movieMapper, OkHttpClient client, ObjectMapper objectMapper, Gson gson) {
		this.movieMapper = movieMapper;
		
		this.client = client;
		this.objectMapper = objectMapper;
		this.gson = gson;
	}
	
	@Override
	public ArrayList<VWMovieManageDataDTO> findAllEnrolledMovie(MoviePageDTO moviePageDTO) throws Exception {
		
		ArrayList<VWMovieManageDataDTO> list = movieMapper.findAllEnrolledMovie(moviePageDTO);
		
		return list;
		
		
	}
	
	@Override
	public int findAllEnrolledMovieCount() throws Exception {
		int count = movieMapper.findAllEnrolledMovieCount();
		
		return count;
	}
	
	@Override
	public void insertGenre() throws Exception {
		
		Request request = new Request.Builder()
				.url("https://api.themoviedb.org/3/genre/movie/list?language=ko-KR")
				.get()
				.addHeader("accept", "application/json")
				.addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2ODgwMjJmOTRmZmJlMDlkNzczZGQwYTk4MDhhMGQ2MyIsIm5iZiI6MTcxOTI0NzcxMi4xMDU5NjYsInN1YiI6IjY2NmNjYTcyYWEwODdmOWFlNjk5N2QyMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.WHyQGfhSMzkFXRk3ixPoWU4NLeaoFApgKOXxUSMVBm0")
				.build();
		
		Response response = client.newCall(request).execute();
		
		String jsonResponse = response.body().string();
		
		JsonNode genresNode = objectMapper.readTree(jsonResponse).get("genres");
		
		
		for (JsonNode genreNode : genresNode){
			
			GenreDTO genreDTO = new GenreDTO();
			genreDTO.setGenreNo(genreNode.get("id").asInt());
			genreDTO.setGenreContent(genreNode.get("name").asText());
			
			movieMapper.insertGenre(genreDTO);
			
		}
		
		
		
		
		
		
		
	}
	
	@Override
	public ArrayList<MovieDTO> findAPIMovieList(int page) throws Exception {
		Request request = new Request.Builder()
				.url("https://api.themoviedb.org/3/movie/popular?api_key=df334807a5a12460d7c3e764b1b510d6&language=ko-KR&page=" + page)
				.get()
				.addHeader("accept", "application/json")
				.addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2ODgwMjJmOTRmZmJlMDlkNzczZGQwYTk4MDhhMGQ2MyIsIm5iZiI6MTcxOTI0NzcxMi4xMDU5NjYsInN1YiI6IjY2NmNjYTcyYWEwODdmOWFlNjk5N2QyMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.WHyQGfhSMzkFXRk3ixPoWU4NLeaoFApgKOXxUSMVBm0")
				.build();
		
		Response response = client.newCall(request).execute();
		
		String responseBody = response.body().string();
		JsonObject json = gson.fromJson(responseBody, JsonObject.class);
		JsonArray results = json.getAsJsonArray("results");
		
		
		ArrayList<MovieDTO> list = new ArrayList<>();
		
		
		for (int i=0; i<results.size(); i++){
			
			JsonObject movieJson = results.get(i).getAsJsonObject();
			MovieDTO movieDTO = new MovieDTO();
			
			
			int adult =0;
			if (movieJson.get("adult").getAsString().equals("false")){
				adult=0;
				
			}else {
				adult=1;
			}
			
			JsonArray genreNos = movieJson.getAsJsonArray("genre_ids");
			if (genreNos !=null && genreNos.size()>0){
				int genreNo = genreNos.get(0).getAsInt();
				movieDTO.setGenreNo(genreNo);
				
			}
			
			Random random = new Random();
			int randNum = random.nextBoolean()? 1:2;
			
			
			movieDTO.setAdult(adult);
			movieDTO.setId(movieJson.get("id").getAsInt());
			movieDTO.setPosterPath("https://image.tmdb.org/t/p/w500"+movieJson.get("poster_path").getAsString());
			movieDTO.setMovieTitle(movieJson.get("title").getAsString());
			movieDTO.setPopularity(movieJson.get("popularity").getAsInt());
			movieDTO.setDuration(randNum);
			movieDTO.setOriginalLanguage(movieJson.get("original_language").getAsString());
			movieDTO.setMovieContent(movieJson.get("overview").getAsString());
			
			list.add(movieDTO);
			
		}
		
		
		this.list= list;
		
		
		return list;
		
	}
	
	@Override
	public void insertAPIMovie(int id) throws Exception {
	
		MovieDTO movieDTO = new MovieDTO();
		
		for (MovieDTO movie : this.list){
			
			if (movie.getId()==id){
				
				movieDTO = movie;
				
			}
			
		}
		
		movieMapper.insertAPIMovie(movieDTO);
	
	
	}
}
