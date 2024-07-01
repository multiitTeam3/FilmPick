package com.multi.mini.product.service;

import com.multi.mini.product.model.dto.CategoryDTO;
import com.multi.mini.product.model.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    public List<CategoryDTO> findAllCategory() throws Exception;

    public int insertProduct(ProductDTO productDTO) throws Exception;

    public int updateProduct(ProductDTO productDTO) throws Exception;

    public int deleteProduct(int productNo) throws Exception;

    public List<ProductDTO> findAllProduct() throws Exception;

    public List<ProductDTO> findProductByCategory(int category) throws Exception;

    public  ProductDTO findProductByProductNo(int productNo) throws Exception;

    public List<ProductDTO> findProductBySearch(String searchword);
}
