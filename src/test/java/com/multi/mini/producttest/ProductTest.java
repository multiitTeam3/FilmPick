package com.multi.mini.producttest;


import com.multi.mini.config.MiniApplication;
import com.multi.mini.config.MybatisConfig;
import com.multi.mini.product.controller.ProductController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@ContextConfiguration(classes = {MiniApplication.class, MybatisConfig.class})
@Transactional
@AutoConfigureMockMvc
public class ProductTest {

    @Autowired
    private ProductController productController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testInit(){
        assertNotNull(productController);
        assertNotNull(mockMvc);
    }

    @DisplayName("")
    @Test
    public void ProductTest(){

        //given



        //when



        //then



    }

    @DisplayName("카테고리로 상품조회테스트")
    @Test
    public void productbycategoryTest() throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("category", "0");

        mockMvc.perform(MockMvcRequestBuilders.get("/product/productbycategory").params(params))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("product/productselect"))
                .andDo(MockMvcResultHandlers.print());



    }


}
