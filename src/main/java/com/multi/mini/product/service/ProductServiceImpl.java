package com.multi.mini.product.service;

import com.multi.mini.product.model.dto.CategoryDTO;
import com.multi.mini.product.model.dto.ProductDTO;
import com.multi.mini.product.model.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {


    private final ProductMapper productMapper;

    @Override
    public List<CategoryDTO> findAllCategory() throws Exception {
        List<CategoryDTO> list =  productMapper.findAllCategory();
        return list;
    }

    @Override
    public int insertProduct(ProductDTO productDTO) throws Exception {
        return productMapper.insertProduct(productDTO);
    }

    @Override
    public int updateProduct(ProductDTO productDTO) throws Exception {
        return productMapper.updateProduct(productDTO);
    }

    @Override
    public int deleteProduct(int productNo) throws Exception {
        return productMapper.deleteProduct(productNo);
    }

    @Override
    public List<ProductDTO> findAllProduct() throws Exception {
        return productMapper.findAllProduct();
    }

    @Override
    public List<ProductDTO> findProductByCategory(int category) throws Exception {
        return productMapper.findProductByCategory(category);
    }

    @Override
    public ProductDTO findProductByProductNo(int productNo) throws Exception {
        return productMapper.findProductByProductNo(productNo);
    }
}
