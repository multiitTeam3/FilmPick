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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {


    private final ProductService productService;

    @GetMapping("productmanage")
    public ModelAndView productmanage(ModelAndView mv) throws Exception {

        List<ProductDTO> list = productService.findAllProduct();

        mv.addObject("productList", list);
        mv.setViewName("product/productmanage");

        return mv;
    }

    @GetMapping("productselect")
    public void productselect(){

    }


    @GetMapping(value="category", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CategoryDTO> findAllCategory() throws Exception {
        return productService.findAllCategory();
    }


    @PostMapping("insert")
    public void productInsert(ProductDTO productDTO, @RequestParam(value = "file") MultipartFile file, HttpServletRequest request) throws Exception {

        String path = request.getSession().getServletContext().getRealPath("");
        String root = path.substring(0,path.length() - 7);
        System.out.println("path : " + path);
        System.out.println("root : " + root);

        String filePath = root + "resources/static/img/uploadFiles";

        File mkdir = new File(filePath);
        if (!mkdir.exists()) {
            mkdir.mkdirs();
        }

        if (!file.isEmpty()){
            String originFileName = file.getOriginalFilename();
            String ext = originFileName.substring(originFileName.lastIndexOf("."));
            String savedName = UUID.randomUUID().toString().replace("-", "") + ext;

            try {
                file.transferTo(new File(filePath + "\\" + savedName));

                productDTO.setProductImg(savedName);

                int result = productService.insertProduct(productDTO);
                System.out.println("인서트 결과" + result);

            }catch (Exception e) {
                e.printStackTrace();
                /* 실패시 파일 삭제 */
                new File(filePath + "\\" + savedName).delete();
            }
        }
        else {
            System.out.println("이미지 없이 생성");
            int result = productService.insertProduct(productDTO);
            System.out.println(result);
        }

    }

    @PostMapping("update")
    public void productUpdate(ProductDTO productDTO, @RequestParam(value = "file") MultipartFile file, HttpServletRequest request) throws Exception{

        String path = request.getSession().getServletContext().getRealPath("");
        String root = path.substring(0,path.length() - 7);
        System.out.println("path : " + path);
        System.out.println("root : " + root);

        String filePath = root + "resources/static/img/uploadFiles";



        File mkdir = new File(filePath);
        if (!mkdir.exists()) {
            mkdir.mkdirs();
        }

        if (!file.isEmpty()){
            String originFileName = file.getOriginalFilename();
            String ext = originFileName.substring(originFileName.lastIndexOf("."));
            String savedName = UUID.randomUUID().toString().replace("-", "") + ext;

            try {
                //기존 이미지 삭제
                new File(filePath + "\\" + productDTO.getProductImg()).delete();

                file.transferTo(new File(filePath + "\\" + savedName));

                productDTO.setProductImg(savedName);

                int result = productService.updateProduct(productDTO);
                System.out.println("업데이트 결과" + result);

            }catch (Exception e) {
                e.printStackTrace();
                /* 실패시 파일 삭제 */
                new File(filePath + "\\" + savedName).delete();
            }
        }
        else {
            System.out.println("이미지 없이 변경");
            int result = productService.updateProduct(productDTO);
            System.out.println("업데이트 결과" + result);
        }
    }

    @PostMapping("delete")
    public void productDelete (ProductDTO productDTO , HttpServletRequest request) throws Exception {

        String path = request.getSession().getServletContext().getRealPath("");
        String root = path.substring(0,path.length() - 7);
        String filePath = root + "resources/static/img/uploadFiles";

        //기존 이미지 삭제
        new File(filePath + "\\" + productDTO.getProductImg()).delete();


        int result = productService.deleteProduct(productDTO.getProductNo());
        System.out.println("딜리트 결과" + result);
    }



    @GetMapping(value="productbycategory", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<ProductDTO> findProductByCategory(@RequestParam("category") int category) throws Exception {

        List<ProductDTO> list = productService.findProductByCategory(category);
        return list;
    }

    @PostMapping("basket")
    public void basketMake(HttpServletRequest request, @RequestParam("totalprice") int totalprice) throws Exception {

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
