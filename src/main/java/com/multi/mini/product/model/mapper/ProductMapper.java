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

    @Select("SELECT COUNT(*) FROM prod_category")
    public int findCategoryCount();

    public int insertProduct(ProductDTO productDTO) throws Exception;

    public int updateProduct(ProductDTO productDTO) throws Exception;

    public int deleteProduct(int productNo) throws Exception;

    @Select("SELECT * FROM prod_product")
    public List<ProductDTO> findAllProduct();

    List<ProductDTO> findProductByCategory(int category);

    ProductDTO findProductByProductNo(int productNo);
}
