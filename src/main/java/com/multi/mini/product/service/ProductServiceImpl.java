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
}
