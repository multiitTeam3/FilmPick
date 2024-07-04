package com.multi.mini.payment.model.mapper;

import com.multi.mini.movie.model.dto.VWResDataDTO;
import com.multi.mini.payment.model.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PaymentMapper {



    VwGetResDataDTO vwResDataNo(int rsvNo) throws Exception;

    void updateReservationIsPaid(int rsvNo) throws Exception;

    int insertPayment(PaymentsDTO paymentsDTO) throws Exception;

    int deletePaymentByReservation(int rsvNO) throws Exception;

    int insertPaymentMovie(PayMovieDTO payMovieDTO) throws Exception;

    Integer selectPaymentNo() throws Exception;

    PayProductDTO selectProductByPay(int papProductNo) throws Exception;

    PayProductAndPaymentDTO insertPaymentProduct(int papProductNo) throws Exception;
}
