package com.xyz.common.utility.mappers;

import com.xyz.common.dtos.BookingInfoDTO;
import com.xyz.common.dtos.SeatInfoDTO;
import com.xyz.common.dtos.SeatsInfoDTO;
import com.xyz.common.dtos.SittingInfoDTO;
import com.xyz.common.jpa.entities.BookingVO;
import com.xyz.common.jpa.entities.SeatVO;
 
import com.xyz.common.jpa.entities.ShowTimeVO;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class BookingMapper {
	
	
	public static  BookingVO mapToBookingVO(BookingInfoDTO bookingInfoDTO,ShowTimeVO showTimeVO,List<SeatVO>seats) {
		BookingVO bookingVO=new BookingVO();
		bookingVO.setBookedStatus( "BOOKED");
		bookingVO.setShowtime(showTimeVO);
		showTimeVO.getBookings().add(bookingVO);
		bookingVO.setBookingDate(new Date());
		bookingVO.setOrgCode(showTimeVO.getTheatre().getOrgCode()); 
		bookingVO.setTotalTickets(bookingInfoDTO.getTotalTickets());
	    seats.forEach(s->{
	    	    s.setBookingId(bookingVO);
	    	    s.setIsBooked( "Y");
	    	    bookingVO.getSeats().add(s);
	    });
	    return bookingVO;
	}
	public static SeatsInfoDTO mapSeats(List<SeatVO>seats) {
	 
	Map<String,List<SittingInfoDTO>>maps=  	 seats.stream().map(s->{
			  SittingInfoDTO sittingInfoDTO=new SittingInfoDTO();
			  sittingInfoDTO.setIsBooked(s.getIsBooked());
			  sittingInfoDTO.setOrderNo(s.getOrderNo());
			  sittingInfoDTO.setSeatId(s.getSeatId());
			  sittingInfoDTO.setSeatNumber(s.getSeatNumber());
			   
			  return sittingInfoDTO;
		 }).collect(Collectors.groupingBy(SittingInfoDTO::getIsBooked,Collectors.toList() )) ;
	    SeatsInfoDTO seatInfoDTO=new SeatsInfoDTO();
	   if(maps.get( "Y")!=null) {
		   seatInfoDTO.setFilledSeats(maps.get( "Y"));
		    
	   } 
	   if(maps.get( "N")!=null) {
		   seatInfoDTO.setAvailableSeats(maps.get( "N"));
	   } 
	   seatInfoDTO.setTotalFilledSeats(seatInfoDTO.getFilledSeats().size());
	   seatInfoDTO.setTotalVailableSeats(seatInfoDTO.getAvailableSeats().size());
	   return seatInfoDTO;
	}
	

}
