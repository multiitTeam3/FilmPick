package com.multi.mini.payment.controller;

import com.multi.mini.member.model.dto.CustomUserDetails;
import com.multi.mini.payment.model.dto.PayProductDTO;
import com.multi.mini.payment.service.PaymentService;
import com.multi.mini.product.model.dto.ProductDTO;
import com.multi.mini.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class PayProductController {


    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ProductService productService;



//    @PostMapping("basketpay")
//    public String basketpay(Model model, @RequestParam("totalprice") int totalPrice, HttpServletRequest request) throws Exception{
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//
//        String[] productNoList = request.getParameterValues("productnum");
//        String[] quantity = request.getParameterValues("productcount");
//
//
//
//
////        HashSet<PayProductDTO> productImg = new HashSet<>();
//        Map<String, PayProductDTO> productMap = new HashMap<>();
//
//        //총합상품계수
//        int totalQuantity = 0;
//
//
//
//
//        // 유저정보
//        int MemberNo = userDetails.getMemberNo();
//        String UserName = userDetails.getUsername();
//
//
//        for (int i = 0; i < productNoList.length; i++) {
//            int productNo = Integer.parseInt(productNoList[i]);
//            int qty = Integer.parseInt(quantity[i]);
//
//            totalQuantity += qty;
//
//            ProductDTO p = productService.findProductByProductNo(productNo);
//
//            System.out.println("Product No: " + productNo);
//            System.out.println("Total Quantity: " + totalQuantity);
//
//            // 이미지 URL 생성
//            String imageUrl = "/static/img/uploadFiles/" + p.getProductImg();
//
//            // PayProductDTO 객체 생성 및 설정
//            PayProductDTO payProduct = new PayProductDTO();
//            payProduct.setPosterPath(imageUrl);
//            payProduct.setProductName(p.getName());
//            payProduct.setProductNo(productNo);
//
//            // 기존에 같은 이미지가 있으면 수량을 합산
//            if (productMap.containsKey(imageUrl)) {
//                PayProductDTO existingProduct = productMap.get(imageUrl);
//                existingProduct.setProductQuantity(existingProduct.getProductQuantity() + qty);
//            } else {
//                payProduct.setProductQuantity(qty);
//                productMap.put(imageUrl, payProduct);
//            }
//        }
//
//
//
////        for(int i = 0; i < productNoList.length; i++){
////
////            int productNo = Integer.parseInt(productNoList[i]);
////            int qty = Integer.parseInt(quantity[i]);
////
////            totalQuantity += qty;
////
////            ProductDTO p =  productService.findProductByProductNo(productNo);
////
////            System.out.println("Product No: " + productNo);
////            System.out.println("Total Quantity: " + totalQuantity);
////
////
////
////            PayProductDTO productImgAndName = new PayProductDTO();
////            productImgAndName.setPosterPath(p.getProductImg());
////            productImgAndName.setProductName(p.getName());
////            productImg.add(productImgAndName);
////
////           PayProductDTO productQuantity = new PayProductDTO();
////           productQuantity.setProductQuantity(totalQuantity);
////           productQuantity.setPapProductNo(p.getProductNo());
////
////
////
////            model.addAttribute("productImgAndName",productImgAndName);
////            model.addAttribute("productQuantity",productQuantity);
////
////        }
//        // model에 유저 정보 및 총합 수량, 총 가격 설정
//        model.addAttribute("MemberNo", MemberNo);
//        model.addAttribute("UserName", UserName);
//        model.addAttribute("totalPrice", totalPrice);
//        model.addAttribute("totalQuantity", totalQuantity);
//
//        // 중복 제거된 상품 정보 model에 추가
//        model.addAttribute("productImgAndName", productMap.values());
//
//
//
//
//
//
//
//
//        return "/payment/paymentProduct";
//
//    }

}
