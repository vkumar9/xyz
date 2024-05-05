package com.xyz.repos.booking;

import com.xyz.common.dtos.TheaterInfoDTO;
import com.xyz.common.jpa.entities.BookingVO;
import com.xyz.common.jpa.entities.CityOfferVO;
import com.xyz.common.jpa.entities.LogicProcessorVO;
import com.xyz.common.jpa.entities.SeatVO;
import com.xyz.common.jpa.entities.ShowOfferVO;
import com.xyz.common.jpa.entities.ShowTimeVO;
import com.xyz.common.jpa.entities.TheatreOfferVO;
import java.util.List;
import java.util.ArrayList;

public interface IBookingDAO {
	
	public BookingVO saveBooking(BookingVO bookingVO);
	
	public CityOfferVO getCityOfferByorgCode(String orgCode);
	
	public TheatreOfferVO getTheaterOfferByorgCode(String orgCode,Integer theaterId);
	
    public List<ShowOfferVO>getSHowOffers(String orgCode,Integer showId);
    
    public LogicProcessorVO getByorgCodeAndType(String orgCode, String type);
    
    public ShowTimeVO updateShowTimeVO(ShowTimeVO showTimeVO);
    
    public ShowTimeVO findByShowId(Integer showId);
    
    public Integer totalAvailableSeats(Integer showId);
    
    public Integer totalFilledSeats(Integer showId);
    
    public List<SeatVO>getAvailableSeats(Integer showid, int totalSeats);
    
    public boolean cancelBooking(Integer bookingId);
    
    public boolean cancelSeats(Integer bookingId);
    
    public List<SeatVO>getPreferredSeats(List<String>seatNumbers, Integer showId);
    
    public List<SeatVO>getSeatsInfoByShowId(Integer showId);
    
}
