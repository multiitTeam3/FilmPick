package com.multi.mini.admin.cinema.controller;


import com.multi.mini.admin.cinema.service.CinemaService;
import com.multi.mini.movie.model.dto.CinemaDTO;
import com.multi.mini.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@RequiredArgsConstructor
@RequestMapping("/admin/cinemamanage")

@Controller
public class CinemaController {
    private final CinemaService cinemaService;
    private final MovieService movieService;

    @GetMapping
    public String showCinemaList(Model model) {
        try {
           ArrayList<CinemaDTO> list = movieService.findCinemaList();
           model.addAttribute("cinemas", list);
        } catch (Exception e) {
            model.addAttribute("msg", "영화관 리스트 조회 실패");
        }

        return "admin/cinema/viewCinema";
    }

    @PostMapping("/insert")
    public String insertCinema(CinemaDTO cinemaDTO, Model model) {
        try {
            cinemaService.insertCinema(cinemaDTO);
            model.addAttribute("msg",  "영화관 등록에 성공했습니다.");
        } catch (Exception e) {
            model.addAttribute("msg", "영화관 등록에 실패했습니다." + e);
        }

        return "redirect:/admin/cinemamanage";
    }

    @GetMapping("/delete")
    public String deleteCinema(@RequestParam("code") int cinemaNo, Model model) {
        try {
            cinemaService.deleteCinema(cinemaNo);
            model.addAttribute("msg",  "영화관 삭제에 성공했습니다.");
        } catch (Exception e) {
            model.addAttribute("msg", "영화관 삭제에 실패했습니다." + e);
        }

        return "redirect:/admin/cinemamanage";
    }

}
