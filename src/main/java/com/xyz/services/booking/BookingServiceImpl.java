package com.xyz.services.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.Show;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xyz.common.component.BookingLogicProcessorInvocationHandler;
import com.xyz.common.dtos.BookingInfoDTO;
import com.xyz.common.dtos.DiscountInfoDTO;
import com.xyz.common.dtos.SeatInfoDTO;
import com.xyz.common.dtos.SeatsInfoDTO;
import com.xyz.common.exception.BookingFailedException;
import com.xyz.common.jpa.entities.BookingVO;
import com.xyz.common.jpa.entities.SeatVO;
 
import com.xyz.common.jpa.entities.ShowTimeVO;
import com.xyz.common.utility.mappers.BookingMapper;
import com.xyz.repos.booking.IBookingDAO;
import com.xyz.repos.booking.IBookingRepo;
import com.xyz.repos.show.IShowTimeRepo;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
@Service(value = "iBookingService")
public class BookingServiceImpl implements IBookingService {
    @Autowired
	private IBookingDAO iBookingDAO;
     
    @Autowired
	private BookingLogicProcessorInvocationHandler bookingLogicInvocationHandler;
	@Override
	@Transactional(  propagation = Propagation.REQUIRED)
	public BookingInfoDTO bookShow(BookingInfoDTO bookingInfoDTO) throws BookingFailedException {
		ShowTimeVO showTimeVO=iBookingDAO.findByShowId(bookingInfoDTO.getShowId());
		
		int availableSeats=iBookingDAO.totalAvailableSeats(bookingInfoDTO.getShowId());
		if(availableSeats>=bookingInfoDTO.getTotalTickets()) {
			List<SeatVO>seats=null;
			if(bookingInfoDTO.getPreferredSeats()!=null && bookingInfoDTO.getPreferredSeats().size()>0) {
				seats=iBookingDAO.getPreferredSeats(bookingInfoDTO.getPreferredSeats(),bookingInfoDTO.getShowId());
				if(seats.size()!=bookingInfoDTO.getTotalTickets()) {
					throw new BookingFailedException("Seats are already booked or seats are not sufficient.");
				}
			}else {
			seats=iBookingDAO.getAvailableSeats(bookingInfoDTO.getShowId(), bookingInfoDTO.getTotalTickets());
			}
		    try {
		    
			DiscountInfoDTO discountInfoDTO=(DiscountInfoDTO)	bookingLogicInvocationHandler.invokeBusinessPartnersLogic(showTimeVO, bookingInfoDTO.getTotalTickets());
			BookingVO bookingVO=BookingMapper.mapToBookingVO(bookingInfoDTO, showTimeVO, seats);
			if(discountInfoDTO.getTotalDiscount()!=null && discountInfoDTO.getTotalDiscount().doubleValue()>0) {
				Double newPrice= calculateFinalPrice(discountInfoDTO.getTotalDiscount(),showTimeVO.getTicketPrice().doubleValue());
			    bookingVO.setTotalPrice(new BigDecimal(   newPrice*bookingInfoDTO.getTotalTickets()));
			    bookingVO.setTotalDiscount(discountInfoDTO.getTotalDiscount().intValue());
			}else {
				bookingVO.setTotalPrice(new BigDecimal(showTimeVO.getTicketPrice().doubleValue()* bookingInfoDTO.getTotalTickets()));
			}
		    
		    //showTimeVO=iBookingDAO.updateShowTimeVO(showTimeVO);
		    final BookingVO  bookingVOn=	iBookingDAO.saveBooking(bookingVO);
		    bookingInfoDTO.setBookingId(bookingVO.getBookingId());
		    List<SeatInfoDTO>seatInfoDTOs=  bookingVO.getSeats().stream().map(s->{
		    	  SeatInfoDTO seatInfDTO=new SeatInfoDTO();
		    	  seatInfDTO.setBookingId(bookingVOn.getBookingId());
		    	  seatInfDTO.setSeatId(s.getSeatId());
		    	  seatInfDTO.setSeatNumber(s.getSeatNumber());
		    	  return seatInfDTO;
		    }).collect(Collectors.toList());
		    bookingInfoDTO.setSeatsInfo(seatInfoDTOs);
		    } catch (Exception e) {
				throw new BookingFailedException( "Booking Discount service failed with exception");
			}
		}else {
			throw new BookingFailedException( "Seats are not sufficient");
		}
		return bookingInfoDTO;
	}
	
	private Double calculateFinalPrice(double discount,double price) {
		double  discountedAmount=(price*discount)/100;
		double newprice =price-discountedAmount;
		return newprice;
		
		
	}

	@Override
	@Transactional(  propagation = Propagation.REQUIRED)
	public boolean cancelBooking(Integer bookingId) {
		 iBookingDAO.cancelBooking(bookingId);
		 iBookingDAO.cancelSeats(bookingId);
		 return Boolean.TRUE;   
	}

	@Override
	public SeatsInfoDTO getSeatsInfoByShowId(Integer ShowId) {
		List<SeatVO>seats= iBookingDAO.getSeatsInfoByShowId(ShowId);
		SeatsInfoDTO seatsInfoDTO=BookingMapper.mapSeats(seats);
		return seatsInfoDTO;
	}

}
