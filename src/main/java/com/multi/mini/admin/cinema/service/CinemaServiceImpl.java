package com.multi.mini.admin.cinema.service;

import com.multi.mini.admin.cinema.model.mapper.CinemaMapper;
import com.multi.mini.movie.model.dto.CinemaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CinemaServiceImpl implements CinemaService{
    private final CinemaMapper cinemaMapper;

    @Override
    public void insertCinema(CinemaDTO cinemaDTO) throws Exception{
        if(cinemaMapper.insertCinema(cinemaDTO) == 0) throw new RuntimeException("영화관 등록 실패");
    }

    @Override
    public void deleteCinema(int cinemaNo) throws Exception{
        if(cinemaMapper.deleteCinema(cinemaNo) == 0) throw new RuntimeException("영화관 삭제 실패");
    }

    @Override
    public void updateCinema(CinemaDTO cinemaDTO) {
        if(cinemaMapper.updateCinema(cinemaDTO) == 0) throw new RuntimeException("영화관 수정 실패");
    }
}
