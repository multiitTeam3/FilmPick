package com.multi.mini.payment.model.dto;

import lombok.Data;

@Data
public class PayMovieDTO {


    private int moviePaymentsNo;
    private int payNo;
    private int rsvNo;
    private String movieTitle;
    private int ticketQuantity;

    private PaymentsDTO paymentsDTO;



}
