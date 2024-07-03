package com.multi.mini.admin.screen.service;

import com.multi.mini.movie.model.dto.ScreenDTO;

import java.util.ArrayList;

public interface ScreenService {

    ArrayList<ScreenDTO> getScreenByCinemaNo(int cinemaNo) throws Exception;

//    void updateScreen(ScreenDTO screenDTO) throws Exception;

    void deleteScreen(int cinemaNo) throws Exception;

    ArrayList<ScreenDTO> getScreenList() throws Exception;
    
    ArrayList<ScreenDTO> findNotEnrolledScreenByCinemaNo(int cinemaNo) throws Exception;
}
