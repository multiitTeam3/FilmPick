package com.multi.mini.payment.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.multi.mini.movie.model.dto.MovieDTO;
import com.multi.mini.movie.model.dto.ReservationDTO;
import com.multi.mini.movie.model.dto.ReservationDataDTO;
import com.multi.mini.movie.service.MovieService;
import com.multi.mini.payment.model.dto.PayMovieDTO;
import com.multi.mini.payment.model.dto.VwResDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PayMovieController {

    @Autowired
    private MovieService movieService;


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
