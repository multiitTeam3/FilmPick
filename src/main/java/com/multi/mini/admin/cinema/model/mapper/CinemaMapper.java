package com.multi.mini.admin.cinema.model.mapper;

import com.multi.mini.movie.model.dto.CinemaDTO;
import org.apache.ibatis.annotations.*;

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

    @Select("SELECT open_time, close_time FROM mov_cinema WHERE cinema_no = #{ cinemaNo }")
    CinemaDTO findCinemaTimeByCinemaNo(int cinemaNo) throws Exception;

    @Delete("DELETE FROM mov_cinema WHERE cinema_no = #{ cinemaNo }")
    int deleteCinema(int cinemaNo) throws Exception;

    @Update("UPDATE mov_cinema " +
            "SET " +
                "  cinema_name = #{ cinemaName }" +
                ", region_no = #{ regionNo }" +
                ", cinema_addr = #{ cinemaAddr }" +
                ", open_time = #{ openTime }" +
                ", close_time = #{ closeTime } " +
            "WHERE " +
                "cinema_no = #{ cinemaNo }")
    int updateCinema(CinemaDTO cinemaDTO);
}
