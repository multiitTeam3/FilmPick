package com.multi.mini.admin.movieschedule.model.mapper;

import com.multi.mini.movie.model.dto.MovieScheduleDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MovieScheduleMapper {
    @Insert("INSERT INTO mov_movie_schedule (" +
                  "movie_no" +
                ", cinema_no" +
                ", screen_code" +
                ", date" +
                ", start_time" +
                ", end_time" +
            ")" +
            "VALUES" +
            "(" +
                  "#{ movieNo }" +
                ", #{ cinemaNo }" +
                ", #{ screenCode }" +
                ", #{ date }" +
                ", #{ startTime }" +
                ", #{ endTime })")
    int insertMovieSchedule(MovieScheduleDTO movieScheduleDTO);

    @Select("SELECT COUNT(*) > 0 FROM mov_movie_schedule WHERE cinema_no = #{ cinemaNo } AND screen_code = #{ screenCode } AND date = #{ date }")
    boolean findMovieScheduleByDate(MovieScheduleDTO movieScheduleDTO);
}
