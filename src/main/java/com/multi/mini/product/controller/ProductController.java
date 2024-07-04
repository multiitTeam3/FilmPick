package com.multi.mini.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.multi.mini.member.model.dto.CustomUserDetails;
import com.multi.mini.payment.model.dto.KakaoReadyProductDTO;
import com.multi.mini.payment.model.dto.PayProductDTO;
import com.multi.mini.product.model.dto.CategoryDTO;
import com.multi.mini.product.model.dto.ProductDTO;
import com.multi.mini.product.model.dto.ProductListDTO;
import com.multi.mini.product.service.ProductService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {


    private final ProductService productService;

    @GetMapping("productmanage")
    public String productmanage(Model model) throws Exception {

        List<ProductDTO> list = productService.findAllProduct();

        model.addAttribute("productList", list);

        return "product/productmanage";
    }

    @GetMapping("productselect")
    public void productselect(){

    }

    @GetMapping("productbasket")
    public void productbasket(){

    }


    @GetMapping(value="category", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CategoryDTO> findAllCategory() throws Exception {
        return productService.findAllCategory();
    }






    @GetMapping(value="productbycategory", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<ProductDTO> findProductByCategory(@RequestParam("category") int category) throws Exception {

        List<ProductDTO> list = productService.findProductByCategory(category);
        return list;
    }

    @PostMapping("basket")
    public String basketMake(HttpServletRequest request, HttpServletResponse response, @RequestParam("totalprice") int totalprice) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        ProductListDTO productListDTO = new ProductListDTO();

        productListDTO.setMemberNo(userDetails.getMemberNo());
        productListDTO.setUsername(userDetails.getUsername());
        productListDTO.setTotalprice(totalprice);

        String[] productNo = request.getParameterValues("productnum");
        String[] quantity = request.getParameterValues("productcount");



        for (int i = 0; i < productNo.length; i++) {
            productListDTO.getProductList().add(productService.findProductByProductNo(Integer.parseInt(productNo[i])));
            productListDTO.getProductQuantityList().add(Integer.parseInt(quantity[i]));
        }

        HttpSession session = request.getSession();

        session.setAttribute( productListDTO.getMemberNo() + "basket" , productListDTO);

        //객체를 json으로
        ObjectMapper objectMapper = new ObjectMapper();
        String basketJson = objectMapper.writeValueAsString(productListDTO);

        System.out.println(" 장바구니 json >> " + basketJson);

        Cookie basketCookie = new Cookie(userDetails.getMemberNo() + "basket" , URLEncoder.encode(basketJson,"UTF-8"));
        basketCookie.setPath("/");
        response.addCookie(basketCookie);
        

        return "product/productbasket";
    }

    @GetMapping(value="getbasket", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String getbasket(HttpServletRequest request) throws UnsupportedEncodingException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String basketJson = "";


        Cookie[] cookies=request.getCookies(); // 모든 쿠키 가져오기
        if(cookies!=null){
            for (Cookie c : cookies) {
                String name = c.getName(); // 쿠키 이름 가져오기
                String value = c.getValue(); // 쿠키 값 가져오기
                if (name.equals(userDetails.getMemberNo() + "basket")) {
                    System.out.println( "받아온 쿠키값 >> " + value);
                    basketJson = URLDecoder.decode(value , "UTF-8");
                    System.out.println( "넘어온 json >> " + basketJson);
                }
            }
        }

        return basketJson;
    }

    @PostMapping("basketpay")
    public String basketpay(Model model, @RequestParam("totalprice") int totalPrice, HttpServletRequest request) throws Exception{

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String[] productNoList = request.getParameterValues("productnum");
        String[] quantity = request.getParameterValues("productcount");


        Map<String, PayProductDTO> productMap = new HashMap<>();

        //총합상품계수
        int totalQuantity = 0;




        // 유저정보
        int MemberNo = userDetails.getMemberNo();
        String UserName = userDetails.getUsername();


        for (int i = 0; i < productNoList.length; i++) {
            int productNo = Integer.parseInt(productNoList[i]);
            int qty = Integer.parseInt(quantity[i]);

            totalQuantity += qty;

            ProductDTO p = productService.findProductByProductNo(productNo);

            System.out.println("Product No: " + productNo);
            System.out.println("Total Quantity: " + totalQuantity);

            // 이미지 URL 생성
            String imageUrl = "/static/img/uploadFiles/" + p.getProductImg();

            // PayProductDTO 객체 생성 및 설정
            PayProductDTO payProduct = new PayProductDTO();
            payProduct.setPosterPath(imageUrl);
            payProduct.setProductName(p.getName());
            payProduct.setProductNo(productNo);

            // 기존에 같은 이미지가 있으면 수량을 합산
            if (productMap.containsKey(imageUrl)) {
                PayProductDTO existingProduct = productMap.get(imageUrl);
                existingProduct.setProductQuantity(existingProduct.getProductQuantity() + qty);
            } else {
                payProduct.setProductQuantity(qty);
                productMap.put(imageUrl, payProduct);

            }
        }

        // model에 유저 정보 및 총합 수량, 총 가격 설정
        model.addAttribute("MemberNo", MemberNo);
        model.addAttribute("UserName", UserName);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("totalQuantity", totalQuantity);


        // 중복 제거된 상품 정보 model에 추가
        model.addAttribute("productImgAndName", productMap.values());


        System.out.println("PRODUCT MAP"+productMap);



        return "/payment/paymentProduct";

    }

}
