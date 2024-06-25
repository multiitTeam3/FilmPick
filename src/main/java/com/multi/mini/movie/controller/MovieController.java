package com.multi.mini.movie.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.multi.mini.member.model.dto.CustomUserDetails;
import com.multi.mini.movie.model.dto.*;
import com.multi.mini.movie.service.MovieService;
import com.multi.mini.payment.model.dto.VwResDataDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
		List<Integer> rsvNoList = new Gson().fromJson(list, new TypeToken<List<Integer>>() {
		}.getType());

		List<VwResDataDTO> reservations = new ArrayList<>();

		ReservationDataDTO reservationDataDTO = new ReservationDataDTO();

		int adultCount = 0;
		int teenCount = 0;
		ArrayList<Integer> selectedSeatNoList = reservationDataDTO.getSelectedSeatNoList();

		for (int rsvNo : rsvNoList) {
			try {
				VwResDataDTO reservation = movieService.getResNo(rsvNo);
				reservations.add(reservation);
				adultCount += reservationDataDTO.getAdult(); // 성인 수 합산
				teenCount += reservationDataDTO.getTeen();
				selectedSeatNoList.addAll(reservationDataDTO.getSelectedSeatNoList());

				for(int i=0; reservation.getSeatName())
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("reservations: "+ reservations);
			model.addAttribute("adultCount", adultCount);
			model.addAttribute("teenCount", teenCount);
			model.addAttribute("selectedSeatNoList", selectedSeatNoList);


			model.addAttribute("reservations", reservations);


		}
		return "payment/payment";

	}
}
