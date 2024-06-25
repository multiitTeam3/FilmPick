package com.multi.mini.product.model.mapper;

import com.multi.mini.product.model.dto.CategoryDTO;
import com.multi.mini.product.model.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("SELECT * FROM prod_category")
    public List<CategoryDTO> findAllCategory();

    public int insertProduct(ProductDTO productDTO) throws Exception;

    @Select("SELECT * FROM prod_product")
    public List<ProductDTO> findAllProduct();

    List<ProductDTO> findProductByCategory(int category);
}
