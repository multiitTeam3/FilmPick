package com.multi.mini.admin.cinema.model.mapper;

import com.multi.mini.movie.model.dto.CinemaDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CinemaMapper {

    @Insert("INSERT INTO mov_cinema " +
            "(" +
              "cinema_name" +
            ", region_no" +
            ", cinema_addr" +
            ", open_time" +
            ", close_time) " +
            "VALUES (" +
             " #{ cinemaName }" +
            ", #{ regionNo }" +
            ", #{ cinemaAddr }" +
            ", #{ openTime }" +
            ", #{ closeTime })")
    int insertCinema(CinemaDTO cinemaDTO) throws Exception;

    @Delete("DELETE FROM mov_cinema WHERE cinema_no = #{ cinemaNo }")
    int deleteCinema(int cinemaNo) throws Exception;
}
