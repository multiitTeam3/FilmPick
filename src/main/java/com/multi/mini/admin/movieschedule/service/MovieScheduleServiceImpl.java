package com.multi.mini.admin.movieschedule.service;

import com.multi.mini.admin.cinema.model.mapper.CinemaMapper;
import com.multi.mini.admin.movieschedule.model.mapper.MovieScheduleMapper;
import com.multi.mini.movie.model.dto.CinemaDTO;
import com.multi.mini.movie.model.dto.MovieDTO;
import com.multi.mini.movie.model.dto.MovieScheduleDTO;
import com.multi.mini.movie.model.mapper.MovieMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MovieScheduleServiceImpl implements MovieScheduleService {
    private final CinemaMapper cinemaMapper;
    private final MovieMapper movieMapper;
    private final MovieScheduleMapper movieScheduleMapper;

    @Override
    public void insertMovieSchedule(MovieScheduleDTO movieScheduleDTO, int breakTime) throws Exception {
        // 오픈 시간, 폐장 시간 DTO에 담기
        CinemaDTO cinemaDTO = cinemaMapper.findCinemaTimeByCinemaNo(movieScheduleDTO.getCinemaNo());
        System.out.println("폐장시간 : " + cinemaDTO.getCloseTime());

        // 영화 정보 확인
        MovieDTO movieDTO = movieMapper.findMovieByMovieNo(movieScheduleDTO.getMovieNo());
        int duration = movieDTO.getDuration();
        int result = 0;

        // 출력 형식 맞추기
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        // 개장 시간, 폐장 시간
        LocalTime cinemaOpenTime = LocalTime.parse(cinemaDTO.getOpenTime(), dateTimeFormatter);
        LocalTime cinemaCloseTime;
        if ("24:00".equals(cinemaDTO.getCloseTime())) { // 24시가 폐장 시간 일 시 00:00으로 인식
            cinemaCloseTime = LocalTime.of(23, 59);
        } else {
            cinemaCloseTime = LocalTime.parse(cinemaDTO.getCloseTime(), dateTimeFormatter);
        }

        // 영화 일정 시작시간 종료시간
        LocalTime startTime = cinemaOpenTime;
        LocalTime endTime = startTime;

        // 현재 날짜에 영화 일정이 있을 시 취소
        if (movieScheduleMapper.findMovieScheduleByDate(movieScheduleDTO)) {
            throw new Exception("DB 영화 일정 중복");
        } else {
            while (startTime.isBefore(cinemaCloseTime)) {
                // 종료 시간
                endTime = startTime.plusHours(duration);

                // 종료 시간이 폐장 시간보다 과거(True)고 시작시간보다 이전(True)이면 종료
                if (endTime.isBefore(cinemaCloseTime) && endTime.isBefore(cinemaOpenTime)) break;

                // movieScheduleDTO에 시작시간 set
                movieScheduleDTO.setStartTime(startTime.format(dateTimeFormatter));
                movieScheduleDTO.setEndTime(endTime.format(dateTimeFormatter));

                result = movieScheduleMapper.insertMovieSchedule(movieScheduleDTO);
                if (result == 0) {
                    throw new RuntimeException("DB 영화 일정 insert 실패");
                }

                // 상영관 휴게시간 계산
                startTime = endTime.plusMinutes(breakTime);
            }
        }
    }
}
