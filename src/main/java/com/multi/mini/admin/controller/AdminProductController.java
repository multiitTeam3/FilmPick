package com.multi.mini.admin.controller;

import com.multi.mini.product.model.dto.ProductDTO;
import com.multi.mini.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/product")
public class AdminProductController {
    private final ProductService productService;

    @GetMapping
    public  String productmanage(Model model) throws Exception {
        List<ProductDTO> list = productService.findAllProduct();

        model.addAttribute("productList", list);
        return "product/productmanage";
    }

    @PostMapping("insert")
    public String productInsert(ProductDTO productDTO, @RequestParam(value = "file") MultipartFile file, HttpServletRequest request) throws Exception {

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

        return "redirect:/admin/product";
    }

    @PostMapping("update")
    public String productUpdate(ProductDTO productDTO, @RequestParam(value = "file") MultipartFile file, HttpServletRequest request) throws Exception{

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
                System.out.println("삭제 경로 >> " + filePath + "/" + productDTO.getProductImg());
                new File(filePath + "/" + productDTO.getProductImg()).delete();

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

        return "redirect:/admin/product";
    }

    @PostMapping("delete")
    public String productDelete (ProductDTO productDTO , HttpServletRequest request) throws Exception {

        String path = request.getSession().getServletContext().getRealPath("");
        String root = path.substring(0,path.length() - 7);
        String filePath = root + "resources/static/img/uploadFiles";

        //기존 이미지 삭제
        System.out.println("삭제 경로 >> " + filePath + "/" + productDTO.getProductImg());
        new File(filePath + "/" + productDTO.getProductImg()).delete();


        int result = productService.deleteProduct(productDTO.getProductNo());
        System.out.println("딜리트 결과" + result);

        return "redirect:/admin/product";
    }

    @GetMapping("productbycategory")
    public String findProductByCategory(@RequestParam("category") int category , Model model) throws Exception {

        List<ProductDTO> list = productService.findProductByCategory(category);
        model.addAttribute("productList", list);
        return "product/productmanage::#productTbody";
    }
}
