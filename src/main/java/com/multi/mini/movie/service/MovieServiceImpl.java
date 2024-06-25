package com.multi.mini.movie.service;

import com.multi.mini.movie.model.dto.*;
import com.multi.mini.movie.model.mapper.MovieMapper;
import com.multi.mini.payment.model.dto.VwResDataDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service("movieService")
public class MovieServiceImpl implements MovieService {

    private final MovieMapper movieMapper;

    public MovieServiceImpl(MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }

    @Override
    public ArrayList<MovieDTO> findMovieList() throws Exception {

        ArrayList<MovieDTO> list = movieMapper.findMovieList();

        return list;
    }

    @Override
    public ArrayList<RegionDTO> findRegionList() throws Exception {
        ArrayList<RegionDTO> list = movieMapper.findRegionList();

        return list;
    }

    @Override
    public ArrayList<CinemaDTO> findCinemaListByRegionNo(int regionNo) throws Exception {

        ArrayList<CinemaDTO> list = movieMapper.findCinemaListByRegionNo(regionNo);

        return list;


    }

    @Override
    public ArrayList<MovieScheduleDTO> findScheduleListByCinemaNo(int cinemaNo) throws Exception {

        ArrayList<MovieScheduleDTO> list = movieMapper.findScheduleListByCinemaNo(cinemaNo);

        return list;

    }

    @Override
    public MovieDTO findMovieByMovieNo(int movieNo) throws Exception {

        MovieDTO movieDTO = movieMapper.findMovieByMovieNo(movieNo);

        return movieDTO;


    }

    @Override
    public ArrayList<SeatDTO> findAllSeat() throws Exception {
        ArrayList<SeatDTO> list = movieMapper.findAllSeat();

        return list;
    }

    @Override
    public ArrayList<SeatDTO> findReservedSeat(MovieScheduleDTO movieScheduleDTO) throws Exception {
        ArrayList<SeatDTO> list = movieMapper.findReservedSeat(movieScheduleDTO);

        return list;
    }

    @Override
    public ArrayList<SeatDTO> findSeatListByScreen(String screenCode) throws Exception {
        ArrayList<SeatDTO> list = movieMapper.findSeatListByScreen(screenCode);

        return list;
    }

    @Override
    public int insertReservation(ReservationDTO reservationDTO) throws Exception {
        int result = movieMapper.insertReservation(reservationDTO);

        return result;
    }

    @Override
    public int findReservationNo(ReservationDTO reservationDTO) throws Exception {
        int rsvNo = movieMapper.findReservationNo(reservationDTO);

        return rsvNo;
    }

    @Override
    public ArrayList<VWResDataGroupDTO> findAllReservationByMemberNo(Map<String, Object> params) throws Exception {

        ArrayList<VWResDataGroupDTO> list = movieMapper.findAllReservationByMemberNo(params);

        return list;


    }

    @Override
    public int findAllReservationCountByMemberNo(int memberNO) throws Exception {
        int count = movieMapper.findAllReservationCountByMemberNo(memberNO);
        return count;
    }

    @Override
    public ArrayList<VWResDataGroupDTO> findAllNotPaidReservationByMemberNo(Map<String, Object> params) throws Exception {
        ArrayList<VWResDataGroupDTO> list = movieMapper.findAllNotPaidReservationByMemberNo(params);

        return list;
    }


    @Override
    public int findAllNotPaidReservationCountByMemberNo(int memberNO) throws Exception {
        int count = movieMapper.findAllNotPaidReservationCountByMemberNo(memberNO);
        return count;
    }


    @Override
    public ArrayList<Integer> findRsvNoListByVWGroupDTO(VWResDataGroupDTO vwResDataGroupDTO) throws Exception {

        ArrayList<Integer> list = movieMapper.findRsvNoListByVWGroupDTO(vwResDataGroupDTO);

        return list;
    }

    @Override
    public ArrayList<VWRewDataDTO> findVWReviewListByMovieNo(Map<String, Object> params) throws Exception {
        ArrayList<VWRewDataDTO> list = movieMapper.findVWReviewListByMovieNo(params);

        return list;
    }


    @Override
    public int findAllReviewCountByMovieNo(int movieNo) throws Exception {
        int count = movieMapper.findAllReviewCountByMovieNo(movieNo);
        return count;
    }


    @Override
    public int insertReview(ReviewDTO reviewDTO) throws Exception {
        int result = movieMapper.insertReview(reviewDTO);

        return result;
    }

    @Override
    public ArrayList<CinemaDTO> findCinemaList() throws Exception {
        ArrayList<CinemaDTO> list = movieMapper.findCinemaList();

        return list;


    }

	private final MovieMapper movieMapper;
	
	public MovieServiceImpl(MovieMapper movieMapper) {
		this.movieMapper = movieMapper;
	}
	
	@Override
	public ArrayList<MovieDTO> findMovieList() throws Exception {
		
		ArrayList<MovieDTO> list = movieMapper.findMovieList();
		
		return list;
	}
	
	@Override
	public ArrayList<RegionDTO> findRegionList() throws Exception {
		ArrayList<RegionDTO> list = movieMapper.findRegionList();
		
		return list;
	}
	
	@Override
	public ArrayList<CinemaDTO> findCinemaListByRegionNo(int regionNo) throws Exception {
		
		ArrayList<CinemaDTO> list = movieMapper.findCinemaListByRegionNo(regionNo);
		
		return list;
		
		
	}
	
	@Override
	public ArrayList<MovieScheduleDTO> findScheduleListByCinemaNo(int cinemaNo) throws Exception {
		
		ArrayList<MovieScheduleDTO> list = movieMapper.findScheduleListByCinemaNo(cinemaNo);
		
		return list;
		
	}
	
	@Override
	public MovieDTO findMovieByMovieNo(int movieNo) throws Exception {
		
		MovieDTO movieDTO = movieMapper.findMovieByMovieNo(movieNo);
		
		return movieDTO;
		
		
	}
	
	@Override
	public ArrayList<SeatDTO> findAllSeat() throws Exception {
		ArrayList<SeatDTO> list = movieMapper.findAllSeat();
		
		return list;
	}
	
	@Override
	public ArrayList<SeatDTO> findReservedSeat(MovieScheduleDTO movieScheduleDTO) throws Exception {
		ArrayList<SeatDTO> list = movieMapper.findReservedSeat(movieScheduleDTO);
		
		return list;
	}
	
	@Override
	public ArrayList<SeatDTO> findSeatListByScreen(String screenCode) throws Exception {
		ArrayList<SeatDTO> list = movieMapper.findSeatListByScreen(screenCode);
		
		return list;
	}
	
	@Override
	public int insertReservation(ReservationDTO reservationDTO) throws Exception {
		int result = movieMapper.insertReservation(reservationDTO);
		
		return result;
	}
	
	@Override
	public int findReservationNo(ReservationDTO reservationDTO) throws Exception {
		int rsvNo = movieMapper.findReservationNo(reservationDTO);
		
		return rsvNo;
	}

	@Override
	public VwResDataDTO getResNo(int rsvNo) throws Exception {
		return movieMapper.vwResDataNo(rsvNo);
	}

}

