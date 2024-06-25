package com.multi.mini.movie.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.multi.mini.member.model.dto.CustomUserDetails;
import com.multi.mini.movie.model.dto.*;
import com.multi.mini.movie.service.MovieService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/movie")
public class MovieController {
	
	private final MovieService movieService;
	
	
	public MovieController(MovieService movieService) {
		super();
		this.movieService = movieService;
		
	}
	
	
	@RequestMapping("/findMovieList")
	@ResponseBody
	public ArrayList<MovieDTO> findImgList() {
		
		ArrayList<MovieDTO> list = null;
		try {
			list = movieService.findMovieList();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return list;
		
	}
	
	@GetMapping("/reservationtime")
	public void movieReservationTime() {
	
	
	}
	
	
	@RequestMapping("/findRegionList")
	@ResponseBody
	public ArrayList<RegionDTO> findRegionList() {
		
		ArrayList<RegionDTO> list = null;
		try {
			list = movieService.findRegionList();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("list : " + list);
		
		return list;
		
	}
	
	
	@RequestMapping("/findCinemaListByRegionNo")
	@ResponseBody
	public ArrayList<CinemaDTO> findCinemaListByRegionNo(@RequestBody RegionDTO regionDTO) {
		
		ArrayList<CinemaDTO> list = null;
		try {
			list = movieService.findCinemaListByRegionNo(regionDTO.getRegionNo());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("list : " + list);
		
		return list;
		
	}
	
	
	@RequestMapping("/findScheduleListByCinemaNo")
	@ResponseBody
	public ArrayList<MovieScheduleDTO> findScheduleListByCinemaNo(@RequestBody CinemaDTO cinemaDTO) {
		
		ArrayList<MovieScheduleDTO> list = null;
		try {
			list = movieService.findScheduleListByCinemaNo(cinemaDTO.getCinemaNo());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("list : " + list);
		
		return list;
		
	}
	
	
	@RequestMapping("/findMovieByMovieNo")
	@ResponseBody
	public MovieDTO findMovieByMovieNo(@RequestBody MovieDTO movieDTO) {
		
		MovieDTO movieDTO2 = null;
		try {
			movieDTO2 = movieService.findMovieByMovieNo(movieDTO.getMovieNo());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("moviedto : " + movieDTO2);
		
		return movieDTO2;
		
	}
	
	
	@RequestMapping("/reservationseat")
	@ResponseBody
	public void reservationseat(@RequestBody MovieScheduleDTO movieScheduleDTO, HttpSession httpSession) {
		
		
		httpSession.setAttribute("movieScheduleDTO", movieScheduleDTO);
		System.out.println(movieScheduleDTO);
		
		
	}
	
	@GetMapping("/reservationseat")
	public void movieReservationSeat(HttpSession httpSession, Model model) {
		
		MovieScheduleDTO movieScheduleDTO = (MovieScheduleDTO) httpSession.getAttribute("movieScheduleDTO");
		
		ArrayList<SeatDTO> seatListByScreen = null;
		
		try {
			seatListByScreen = movieService.findSeatListByScreen(movieScheduleDTO.getScreenCode());
			
			System.out.println("seatListByScreen : " + seatListByScreen);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
		ArrayList<SeatDTO> reservedSeatList = null;
		try {
			/*reservedSeatList = movieService.reservedSeatList();*/
			reservedSeatList = movieService.findReservedSeat(movieScheduleDTO);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		ArrayList<String> seatNoList = new ArrayList();
		
		for (SeatDTO seat : reservedSeatList) {
			seatNoList.add(String.valueOf(seat.getSeatNo()));
		}
		
		System.out.println("seatNoList" + seatNoList);
		
		model.addAttribute("movieScheduleDTO", movieScheduleDTO);
		model.addAttribute("reservedSeatList", reservedSeatList);
		model.addAttribute("seatNoList", seatNoList);
		model.addAttribute("seatListByScreen", seatListByScreen);
		
		httpSession.removeAttribute("movieScheduleDTO");
		
	}
	
	
	@RequestMapping("/findAllSeat")
	@ResponseBody
	public ArrayList<SeatDTO> findAllSeat() {
		
		ArrayList<SeatDTO> list = null;
		try {
			list = movieService.findAllSeat();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("seatlist : " + list);
		
		return list;
		
	}
	
	
	// 예약화면으로 이동
	@RequestMapping("/insertreservation")
	@ResponseBody
	public int insertReservation(@RequestBody ReservationDataDTO reservationDataDTO) {
		
		System.out.println(reservationDataDTO);
		
		// 현재 인증된 사용자 정보를 가져옴
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		// 작성자의 memberNo와 userName을 설정
		
		int memberNO = userDetails.getMemberNo();
		int scheduleNo = reservationDataDTO.getScheduleNo();
		int adult = reservationDataDTO.getAdult();
		int teen = reservationDataDTO.getTeen();
		ArrayList<Integer> selectedSeatNoList = reservationDataDTO.getSelectedSeatNoList();
		
		ArrayList<Integer> priceList = new ArrayList<>();
		
		if (adult > 0) {
			
			for (int i = 0; i < adult; i++) {
				
				priceList.add(12000);
				
			}
			
		}
		
		if (teen > 0) {
			
			for (int i = 0; i < teen; i++) {
				
				priceList.add(8000);
				
			}
			
		}
		
		int result = 0;
		
		ArrayList<ReservationDTO> list = new ArrayList<>();
		
		
		for (int i = 0; i < selectedSeatNoList.size(); i++) {
			
			ReservationDTO reservationDTO = new ReservationDTO();
			reservationDTO.setScheduleNo(scheduleNo);
			reservationDTO.setSeatNo(selectedSeatNoList.get(i));
			reservationDTO.setMemberNo(memberNO);
			reservationDTO.setRsvMoviePrice(priceList.get(i));
			reservationDTO.setRsvIsPaid("N");
			
			int result1 = 0;
			try {
				result1 = movieService.insertReservation(reservationDTO);
				list.add(reservationDTO);
				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			result += result1;
			
			
		}
		
		
		ArrayList<Integer> rsvNoList = new ArrayList<>();
		
		for (int i = 0; i < list.size(); i++) {
			rsvNoList.add(list.get(i).getRsvNo());
		}
		
		
		return result;
		
	}
	
	
	@RequestMapping("/insertreservationpay")
	@ResponseBody
	public ArrayList<Integer> insertReservationPay(@RequestBody ReservationDataDTO reservationDataDTO) {
		
		System.out.println(reservationDataDTO);
		
		// 현재 인증된 사용자 정보를 가져옴
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		// 작성자의 memberNo와 userName을 설정
		
		int memberNO = userDetails.getMemberNo();
		int scheduleNo = reservationDataDTO.getScheduleNo();
		int adult = reservationDataDTO.getAdult();
		int teen = reservationDataDTO.getTeen();
		ArrayList<Integer> selectedSeatNoList = reservationDataDTO.getSelectedSeatNoList();
		
		ArrayList<Integer> priceList = new ArrayList<>();
		
		if (adult > 0) {
			
			for (int i = 0; i < adult; i++) {
				
				priceList.add(12000);
				
			}
			
		}
		
		if (teen > 0) {
			
			for (int i = 0; i < teen; i++) {
				
				priceList.add(8000);
				
			}
			
		}
		
		int result = 0;
		
		ArrayList<ReservationDTO> list = new ArrayList<>();
		
		
		for (int i = 0; i < selectedSeatNoList.size(); i++) {
			
			ReservationDTO reservationDTO = new ReservationDTO();
			reservationDTO.setScheduleNo(scheduleNo);
			reservationDTO.setSeatNo(selectedSeatNoList.get(i));
			reservationDTO.setMemberNo(memberNO);
			reservationDTO.setRsvMoviePrice(priceList.get(i));
			reservationDTO.setRsvIsPaid("N");
			
			int result1 = 0;
			try {
				result1 = movieService.insertReservation(reservationDTO);
				list.add(reservationDTO);
				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			result += result1;
			
			
		}
		
		
		ArrayList<Integer> rsvNoList = new ArrayList<>();
		
		for (int i = 0; i < list.size(); i++) {
			
			int rsv = 0;
			try {
				rsv = movieService.findReservationNo(list.get(i));
				rsvNoList.add(rsv);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
			
		}
		
		System.out.println(rsvNoList);
		
		return rsvNoList;
		
	}
	
	@GetMapping("/reservationseat/payment")
	public String payment(@RequestParam("list") String list, Model model) {
		
		List<String> rsvNoList = new Gson().fromJson(list, new TypeToken<List<String>>() {
		}.getType());
		
		
		// 현재 인증된 사용자 정보를 가져옴
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		// 작성자의 memberNo와 userName을 설정
		
		int memberNO = userDetails.getMemberNo();
		
		VWResDataGroupDTO vwResDataGroupDTO = new VWResDataGroupDTO();
		
		
		model.addAttribute("rsvNoList", rsvNoList);
		return "payment/payment";
		
		
	}
	
	
	@GetMapping("/reservationlist")
	public void movieReservationList() {
	
	
	}
	
	
	@RequestMapping("findAllReservationByMemberNo")
	@ResponseBody
	public ReservationResponseDTO response(@RequestParam("page") int page, Model model){
		
		
		// 현재 인증된 사용자 정보를 가져옴
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		// 작성자의 memberNo와 userName을 설정
		
		int memberNO = userDetails.getMemberNo();
		
	
		
		MoviePageDTO moviePageDTO = new MoviePageDTO();
		moviePageDTO.setPage(page);
		System.out.println("page : " + moviePageDTO.getPage());
		moviePageDTO.setStartEnd(moviePageDTO.getPage());
		
		ArrayList<VWResDataGroupDTO> list = null;
		
		
		Map<String, Object> params = new HashMap<>();
		params.put("memberNo", memberNO);
		params.put("start",moviePageDTO.getStart());
		params.put("end",moviePageDTO.getEnd());
		
		ReservationResponseDTO responseDTO= null;
		
		try {
			list = movieService.findAllReservationByMemberNo(params);
			
			for (VWResDataGroupDTO reservation : list){
				
				reservation.setSeatNames(reservation.getSeatNames());
				
			}
			
			
			
			int count = movieService.findAllReservationCountByMemberNo(memberNO);
			
			System.out.println("count : " + count);
			
			int pages = count / 3 + 1 ;
			
			System.out.println("pages : " +pages);
			
			
			
			responseDTO = new ReservationResponseDTO();
			responseDTO.setReservations(list);
			responseDTO.setPages(pages);
			responseDTO.setCount(count);
			
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("ArrayList<VWResDataDTO>" +list);
		
		
		
		
		
		
		return responseDTO;
		
	
	}
	
	@RequestMapping("findAllNotPaidReservationByMemberNo")
	@ResponseBody
	public ReservationResponseDTO response2(@RequestParam("page") int page, Model model){
		
		
		// 현재 인증된 사용자 정보를 가져옴
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		// 작성자의 memberNo와 userName을 설정
		
		int memberNO = userDetails.getMemberNo();
		
	
		
		MoviePageDTO moviePageDTO = new MoviePageDTO();
		moviePageDTO.setPage(page);
		System.out.println("page : " + moviePageDTO.getPage());
		moviePageDTO.setStartEnd(moviePageDTO.getPage());
		
		ArrayList<VWResDataGroupDTO> list = null;
		
		
		Map<String, Object> params = new HashMap<>();
		params.put("memberNo", memberNO);
		params.put("start",moviePageDTO.getStart());
		params.put("end",moviePageDTO.getEnd());
		
		ReservationResponseDTO responseDTO= null;
		
		try {
			list = movieService.findAllNotPaidReservationByMemberNo(params);
			
			for (VWResDataGroupDTO reservation : list){
				
				reservation.setSeatNames(reservation.getSeatNames());
				
			}
			
			
			
			int count = movieService.findAllNotPaidReservationCountByMemberNo(memberNO);
			
			System.out.println("count : " + count);
			
			int pages = count / 3 + 1 ;
			
	
			
			System.out.println("pages : " +pages);
			
			
			
			responseDTO = new ReservationResponseDTO();
			responseDTO.setReservations(list);
			responseDTO.setPages(pages);
			responseDTO.setCount(count);
			
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("ArrayList<VWResDataDTO>" +list);
		
		
		
		
		
		
		return responseDTO;
		
	
	}
	
	
	
	// 예약 내역 화면에서 받은 정보로 예약 번호를 추출해서 다시 페이 화면으로 보내기.
	
	@RequestMapping("/payTheBill")
	@ResponseBody
	public ArrayList<Integer> payTheBill(@RequestBody Map<String,Object> requestData) {
		
		Integer scheduleNo = (Integer) requestData.get("scheduleNo");
		
		
		System.out.println("컨트롤러 scheduleNo : " + scheduleNo);
		
		// 현재 인증된 사용자 정보를 가져옴
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		int memberNO = userDetails.getMemberNo();
		
		// 작성자의 memberNo와 userName을 설정
		VWResDataGroupDTO vwResDataGroupDTO = new VWResDataGroupDTO();
		vwResDataGroupDTO.setMemberNo(memberNO);
		vwResDataGroupDTO.setScheduleNo(scheduleNo);
		
		ArrayList<Integer> rsvNoList = new ArrayList<>();
		
		
		try {
			rsvNoList = movieService.findRsvNoListByVWGroupDTO(vwResDataGroupDTO);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("rsvNoList : " + rsvNoList);
		
		return rsvNoList;
		
	}
	
	@GetMapping("/review")
	public void goReview(@RequestParam("movieNo") int movieNo, Model model){
		
		model.addAttribute("movieNo", movieNo);
		
	}
	
	
	@RequestMapping("VWReviewListByMovieNo")
	@ResponseBody
	public ReviewResponseDTO response3(@RequestParam("page") int page, @RequestParam("movieNo") int movieNo , Model model){
		
		
		MoviePageDTO moviePageDTO = new MoviePageDTO();
		moviePageDTO.setPage(page);
		System.out.println("page : " + moviePageDTO.getPage());
		moviePageDTO.setStartEnd(moviePageDTO.getPage());
		
		
		
		
		Map<String, Object> params = new HashMap<>();
		params.put("movieNo", movieNo);
		params.put("start",moviePageDTO.getStart());
		params.put("end",moviePageDTO.getEnd());
		
		ReviewResponseDTO responseDTO= null;
		
		ArrayList<VWRewDataDTO> list = null;
		try {
			list = movieService.findVWReviewListByMovieNo(params);
			
			int count = movieService.findAllReviewCountByMovieNo(movieNo);
			
			System.out.println("count : " + count);
			
			int pages = count / 3 + 1 ;
			
			
			
			System.out.println("pages : " +pages);
			
			
			
			responseDTO = new ReviewResponseDTO();
			responseDTO.setReviews(list);
			responseDTO.setPages(pages);
			responseDTO.setCount(count);
			
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("VWRewDataDTO" +list);
		
		
		
		
		
		
		return responseDTO;
		
		
	}
	
	
	@PostMapping("insertReview")
	@ResponseBody
	public ResponseEntity<String> insertReview(@RequestBody ReviewDTO reviewDTO){
		
		
		
		// 현재 인증된 사용자 정보를 가져옴
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		// 작성자의 memberNo와 userName을 설정
		
		int memberNO = userDetails.getMemberNo();

		reviewDTO.setMemberNo(memberNO);
		
		System.out.println(reviewDTO);
		
		int result = 0;
		try {
			result = movieService.insertReview(reviewDTO);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
		System.out.println(result);
		
		
		
		return ResponseEntity.ok("리뷰 입력됨");
	}
	
	
	
	
	
	
	
}
