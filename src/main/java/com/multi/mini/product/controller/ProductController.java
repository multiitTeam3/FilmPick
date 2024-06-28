package com.multi.mini.product.controller;

import com.multi.mini.member.model.dto.CustomUserDetails;
import com.multi.mini.product.model.dto.CategoryDTO;
import com.multi.mini.product.model.dto.ProductDTO;
import com.multi.mini.product.model.dto.ProductListDTO;
import com.multi.mini.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String basketMake(HttpServletRequest request, @RequestParam("totalprice") int totalprice) throws Exception {

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

        return "/home";
    }

    @GetMapping(value="getbasket", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ProductListDTO getbasket(HttpSession session){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();


        ProductListDTO basket = (ProductListDTO)session.getAttribute(userDetails.getMemberNo()+"basket");

        System.out.println("basket>> " + basket);

        return basket;
    }

}
