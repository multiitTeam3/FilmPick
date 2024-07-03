package com.multi.mini.admin.screen.controller;

import com.multi.mini.admin.screen.service.ScreenService;
import com.multi.mini.movie.model.dto.MovieDTO;
import com.multi.mini.movie.model.dto.ScreenDTO;
import com.multi.mini.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/cinemamanage/screen")
public class ScreenController {
	private final ScreenService screenService;
	private final MovieService movieService;
	
	@GetMapping
	public String showScreenManage(@RequestParam("no") int cinemaNo, Model model) {
		try {
			ArrayList<ScreenDTO> ScreenList = screenService.getScreenByCinemaNo(cinemaNo);
			ArrayList<ScreenDTO> ScreenNameList = screenService.getScreenList();
			ArrayList<MovieDTO> movieList = movieService.findMovieList();
			String minDate = LocalDate.now().toString();
			
			ScreenNameList.get(0).setCinemaNo(cinemaNo);
			
			System.out.println("howScreenManage : " + ScreenList);
			
			
			model.addAttribute("screens", ScreenList);
			model.addAttribute("screensNames", ScreenNameList);
			model.addAttribute("movies", movieList);
			model.addAttribute("minDate", minDate);
		} catch (Exception e) {
			model.addAttribute("msg", "상영관 목록 조회에 실패했습니다.");
		}
		
		return "admin/screen/viewScreen";
	}
	
	@PostMapping("/insert")
	public String createScreen(ScreenDTO screenDTO, RedirectAttributes redirectAttributes) {
		System.out.println("screenDTO : " + screenDTO);
		
		try {
			screenService.insertScreenByCinemaNo(screenDTO);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return "redirect:/admin/cinemamanage/screen?no=" + screenDTO.getCinemaNo();
	}

//    @PostMapping
//    public String updateScreen(ScreenDTO screenDTO, RedirectAttributes redirectAttributes) {
//        try {
//            screenService.updateScreen(screenDTO);
//            redirectAttributes.addFlashAttribute("msg", "상영관 수정에 성공했습니다.");
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("msg", "상영관 수정에 실패했습니다.");
//        }
//        return "redirect:/admin/cinemamanage/screen";
//    }
	
	@PostMapping
	public String deleteScreen(@RequestParam("no") int cinemaNo, RedirectAttributes redirectAttributes) {
		try {
			screenService.deleteScreen(cinemaNo);
			redirectAttributes.addFlashAttribute("msg", "상영관 삭제에 성공했습니다.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "상영관 삭제에 실패했습니다.");
		}
		return "redirect:/admin/cinemamanage/screen?no=" + cinemaNo;
	}
	
	@PostMapping("findNotEnrolledScreenByCinemaNo")
	@ResponseBody
	public ArrayList<ScreenDTO> findNotEnrolledScreenByCinemaNo(@RequestParam("cinemaNo") int cinemaNo) {
		
		
		ArrayList<ScreenDTO> list = null;
		try {
			list = screenService.findNotEnrolledScreenByCinemaNo(cinemaNo);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("findNotEnrolledScreenByCinemaNo : " + list);
		
		return list;
		
	}
	
}
