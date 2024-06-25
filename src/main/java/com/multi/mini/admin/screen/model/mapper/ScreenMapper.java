package com.multi.mini.admin.screen.model.mapper;

import com.multi.mini.movie.model.dto.ScreenDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface ScreenMapper {
    @Select("SELECT * FROM vw_find_screen_by_cinema WHERE cinema_no = #{ cinema_no }")
    ArrayList<ScreenDTO> findScreenByCinemaNo(int cinemaNo) throws Exception;

    @Select("SELECT screen_code, screen_name FROM vw_find_screen_by_cinema")
    ArrayList<ScreenDTO> findScreenAll() throws Exception;


    @Delete("DELETE FROM mov_cinema_and_screen WHERE cinema_no = #{ cinema_no}")
    int deleteScreenByCinemaNo(int cinemaNo);


//    @Update("UPDATE mov_screen SET is_avail = #{ isAvail } WHERE #{ cinemaNo }")
//    int updateScreen(ScreenDTO screenDTO);
}
