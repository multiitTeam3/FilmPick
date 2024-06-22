package com.multi.mini.product.service;

import com.multi.mini.product.model.dto.CategoryDTO;
import com.multi.mini.product.model.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    public List<CategoryDTO> findAllCategory() throws Exception;

    public int insertProduct(ProductDTO productDTO) throws Exception;
}
