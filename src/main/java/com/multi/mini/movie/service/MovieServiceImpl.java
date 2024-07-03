package com.multi.mini.movie.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.multi.mini.common.model.dto.PageDTO;
import com.multi.mini.movie.model.dto.*;
import com.multi.mini.movie.model.mapper.MovieMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

@Service("movieService")
public class MovieServiceImpl implements MovieService {

    private final MovieMapper movieMapper;
    private final OkHttpClient client;
    private final ObjectMapper objectMapper;
    private final Gson gson;
    
    ArrayList<MovieDTO> list = new ArrayList<>();

    public MovieServiceImpl(MovieMapper movieMapper, OkHttpClient client, ObjectMapper objectMapper, Gson gson) {
        this.movieMapper = movieMapper;
        this.client = client;
        this.objectMapper = objectMapper;
        this.gson = gson;
    }

    @Override
    public ArrayList<MovieDTO> findMovieList() throws Exception {

        ArrayList<MovieDTO> list = movieMapper.findMovieList();

        return list;
    }

    @Override
    public ArrayList<RegionDTO> findRegionList() throws Exception {
        ArrayList<RegionDTO> list = movieMapper.findRegionList();

        return list;
    }

    @Override
    public ArrayList<CinemaDTO> findCinemaListByRegionNo(int regionNo) throws Exception {

        ArrayList<CinemaDTO> list = movieMapper.findCinemaListByRegionNo(regionNo);

        return list;


    }

    @Override
    public ArrayList<MovieScheduleDTO> findScheduleListByCinemaNo(int cinemaNo) throws Exception {

        ArrayList<MovieScheduleDTO> list = movieMapper.findScheduleListByCinemaNo(cinemaNo);

        return list;

    }

    @Override
    public MovieDTO findMovieByMovieNo(int movieNo) throws Exception {

        MovieDTO movieDTO = movieMapper.findMovieByMovieNo(movieNo);

        return movieDTO;


    }

    @Override
    public ArrayList<SeatDTO> findAllSeat() throws Exception {
        ArrayList<SeatDTO> list = movieMapper.findAllSeat();

        return list;
    }

    @Override
    public ArrayList<SeatDTO> findReservedSeat(MovieScheduleDTO movieScheduleDTO) throws Exception {
        ArrayList<SeatDTO> list = movieMapper.findReservedSeat(movieScheduleDTO);

        return list;
    }

    @Override
    public ArrayList<SeatDTO> findSeatListByScreen(String screenCode) throws Exception {
        ArrayList<SeatDTO> list = movieMapper.findSeatListByScreen(screenCode);

        return list;
    }

    @Override
    public int insertReservation(ReservationDTO reservationDTO) throws Exception {
        int result = movieMapper.insertReservation(reservationDTO);

        return result;
    }

    @Override
    public int findReservationNo(ReservationDTO reservationDTO) throws Exception {
        int rsvNo = movieMapper.findReservationNo(reservationDTO);

        return rsvNo;
    }

    @Override
    public ArrayList<VWResDataGroupDTO> findAllReservationByMemberNo(Map<String, Object> params) throws Exception {

        ArrayList<VWResDataGroupDTO> list = movieMapper.findAllReservationByMemberNo(params);

        return list;


    }

    @Override
    public int findAllReservationCountByMemberNo(int memberNO) throws Exception {
        int count = movieMapper.findAllReservationCountByMemberNo(memberNO);
        return count;
    }

    @Override
    public ArrayList<VWResDataGroupDTO> findAllNotPaidReservationByMemberNo(Map<String, Object> params) throws Exception {
        ArrayList<VWResDataGroupDTO> list = movieMapper.findAllNotPaidReservationByMemberNo(params);

        return list;
    }


    @Override
    public int findAllNotPaidReservationCountByMemberNo(int memberNO) throws Exception {
        int count = movieMapper.findAllNotPaidReservationCountByMemberNo(memberNO);
        return count;
    }


    @Override
    public ArrayList<Integer> findRsvNoListByVWGroupDTO(VWResDataGroupDTO vwResDataGroupDTO) throws Exception {

        ArrayList<Integer> list = movieMapper.findRsvNoListByVWGroupDTO(vwResDataGroupDTO);

        return list;
    }

    @Override
    public ArrayList<VWRewDataDTO> findVWReviewListByMovieNo(Map<String, Object> params) throws Exception {
        ArrayList<VWRewDataDTO> list = movieMapper.findVWReviewListByMovieNo(params);

        return list;
    }


    @Override
    public int findAllReviewCountByMovieNo(int movieNo) throws Exception {
        int count = movieMapper.findAllReviewCountByMovieNo(movieNo);
        return count;
    }


    @Override
    public int insertReview(ReviewDTO reviewDTO) throws Exception {
        int result = movieMapper.insertReview(reviewDTO);

        return result;
    }

    @Override
    public ArrayList<CinemaDTO> findCinemaList(String type, String keyword, PageDTO pageDTO) throws Exception {
        ArrayList<CinemaDTO> list = movieMapper.findCinemaList(type, keyword, pageDTO.getStart(), pageDTO.getEnd());

        return list;


    }

	@Override
	public VWResDataDTO getResNo(int rsvNo) throws Exception {
		return movieMapper.vwResDataNo(rsvNo);
	}
    
    @Override
    public int deleteReservation(ReservationDTO reservationDTO) throws Exception {
        return movieMapper.deleteReservation(reservationDTO);
    }
    
    @Override
    public VWResDataGroupDTO findVWResDataGroupDTO(VWResDataGroupDTO vwResDataGroupDTO) throws Exception {
        return movieMapper.findVWResDataGroupDTO(vwResDataGroupDTO);
    }
    
    @Override
    public ArrayList<VWMovieManageDataDTO> findAllVWMovieManageDataDTO() throws Exception {
        return movieMapper.findAllVWMovieManageDataDTO();
    }
    
    @Override
    public ArrayList<MovieDTO> findAllAPIMovieList() throws Exception {
        
        Random random = new Random();
        int page = random.nextInt(1,20);
        
        
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
            
            Random random2 = new Random();
            int randNum = random2.nextBoolean()? 1:2;
            
            
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
    public ReviewDTO findReviewByMemberNoAndMovieNo(ReviewDTO reviewDTO) throws Exception {
        ReviewDTO review = movieMapper.findReviewByMemberNo(reviewDTO);
        return review;
    }
}

