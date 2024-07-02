package com.multi.mini.admin.cinema.service;

import com.multi.mini.movie.model.dto.CinemaDTO;

public interface CinemaService {

    void insertCinema(CinemaDTO cinemaDTO) throws Exception;

    void deleteCinema(int cinemaNo) throws Exception;

    void updateCinema(CinemaDTO cinemaDTO);
}
