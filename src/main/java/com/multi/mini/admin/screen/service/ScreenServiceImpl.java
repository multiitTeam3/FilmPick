package com.multi.mini.admin.screen.service;


import com.multi.mini.admin.screen.model.mapper.ScreenMapper;
import com.multi.mini.movie.model.dto.ScreenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ScreenServiceImpl implements ScreenService{
    private final ScreenMapper screenMapper;

    @Override
    public ArrayList<ScreenDTO> getScreenByCinemaNo(int cinemaNo) throws Exception{
        ArrayList<ScreenDTO> list =  screenMapper.findScreenByCinemaNo(cinemaNo);
        if(list == null) throw new RuntimeException("상영관 조회 DB Error");
        return list;
    }

//    @Override
//    public void updateScreen(ScreenDTO screenDTO) throws Exception {
//        int result = screenMapper.updateScreen(screenDTO);
//        if(result == 0) throw new RuntimeException("상영관 수정에 실패했습니다.");
//    }

    @Override
    public void deleteScreen(int cinemaNo) throws Exception{
        int result = screenMapper.deleteScreenByCinemaNo(cinemaNo);
        if(result == 0) throw new RuntimeException("상영관 삭제 DB Error");
    }

    @Override
    public ArrayList<ScreenDTO> getScreenList() throws Exception{
        ArrayList<ScreenDTO> list =  screenMapper.findScreenAll();
        if(list == null) throw new RuntimeException("상영관 리스트 조회 DB Error");
        return list;
    }
}
