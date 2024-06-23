package com.multi.mini.payment.controller;

import com.multi.mini.movie.model.dto.MovieDTO;
import com.multi.mini.movie.service.MovieService;
import com.multi.mini.payment.model.dto.PayMovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PayMovieController {

    @Autowired
    private MovieService movieService;






}
