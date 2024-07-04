package com.multi.mini.admin.movieschedule.service;

import com.multi.mini.movie.model.dto.MovieScheduleDTO;

public interface MovieScheduleService {
    void insertMovieSchedule(MovieScheduleDTO movieScheduleDTO, int breakTime) throws Exception;
}
