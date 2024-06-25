package com.multi.mini.admin.movieschedule.controller;

import com.multi.mini.admin.movieschedule.service.MovieScheduleService;
import com.multi.mini.movie.model.dto.MovieScheduleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping
@RequiredArgsConstructor
@Controller
public class MovieScheduleContoller {
    private final MovieScheduleService movieScheduleService;

    @PostMapping("/admin/cinemamanage/schedule/insert")
    public String createMovieSchedule(MovieScheduleDTO movieScheduleDTO, @RequestParam("breaktime") int breakTime, RedirectAttributes redirectAttributes) {
        try {
            movieScheduleService.insertMovieSchedule(movieScheduleDTO, breakTime);
            redirectAttributes.addFlashAttribute("msg", "영화 일정 등록 성공");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "영화 일정 등록 실패");
        }
        return "redirect:/admin/cinemamanage/screen?no=" + movieScheduleDTO.getCinemaNo();
    }

}
