package com.multi.mini.common.model.mapper;

import com.multi.mini.common.model.dto.TempPasswordDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TempPasswordMapper {

    @Insert("INSERT INTO mem_temp_password " +
            "(" +
            "  member_no" +
            ", temp_pw" +
            ", exp_date" +
            ") VALUES (" +
            "  #{ memberNo }" +
            ", #{ tempPw }" +
            ", #{ expDate })")
    void insertTempPassword(TempPasswordDTO tempPasswordDTO) throws Exception;
}
