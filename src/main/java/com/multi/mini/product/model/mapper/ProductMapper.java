package com.multi.mini.product.model.mapper;

import com.multi.mini.product.model.dto.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("SELECT * FROM prod_category")
    public List<CategoryDTO> findAllCategory();
}
