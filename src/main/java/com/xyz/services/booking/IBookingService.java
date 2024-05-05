package com.xyz.services.booking;

import com.xyz.common.dtos.BookingInfoDTO;
import com.xyz.common.dtos.SeatsInfoDTO;
import com.xyz.common.exception.BookingFailedException;
 

public interface IBookingService  {
	
	public BookingInfoDTO bookShow(BookingInfoDTO bookingInfoDTO)throws BookingFailedException;
    public boolean  cancelBooking(Integer bookingId);
    
    public SeatsInfoDTO getSeatsInfoByShowId(Integer ShowId);
}
