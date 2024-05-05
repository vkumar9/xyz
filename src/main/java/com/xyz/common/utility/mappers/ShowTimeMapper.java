package com.xyz.common.utility.mappers;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.xyz.common.constant.ApplicationConstant;
import com.xyz.common.dtos.ShowTimeDTO;
import com.xyz.common.jpa.entities.MovieVO;
import com.xyz.common.jpa.entities.OfferVO;
import com.xyz.common.jpa.entities.SeatVO;
import com.xyz.common.jpa.entities.ShowOfferVO;
import com.xyz.common.jpa.entities.ShowTimeVO;
import com.xyz.common.utility.CommonUtility;

public class ShowTimeMapper {
	
	
	public static ShowTimeVO  mapShowTime(ShowTimeDTO showTimeDTO,List<OfferVO>offers )throws Exception {
		ShowTimeVO showTimeVO=new ShowTimeVO();
		showTimeVO.setIsActive( "Y");
		BigDecimal ticketprice=new BigDecimal(showTimeDTO.getTicketPrice());
		showTimeVO.setTicketPrice(ticketprice);
		Date showDate=CommonUtility.convertToDate(showTimeDTO.getShowDate(), ApplicationConstant.DATE_YYYY_MM_DD);
		showTimeVO.setShowDate(showDate);
		showTimeVO.setShowStartTime(Time.valueOf(showTimeDTO.getShowStartTime()));
		showTimeVO.setShowEndTime(Time.valueOf(showTimeDTO.getShowEndTime()));
		MovieVO movieVO=new MovieVO();
		movieVO.setMovieId(showTimeDTO.getMovieId());
		showTimeVO.setMovie(movieVO);
		showTimeVO.setTheatre(showTimeDTO.getTheatreVO());
		int sittingCapacity=showTimeDTO.getTheatreVO().getSittingCapacity();
		if(showTimeDTO.getSittingCapacity()!=null && showTimeDTO.getSittingCapacity().intValue()>0) {
			sittingCapacity=showTimeDTO.getSittingCapacity();
		}
		showTimeVO.setOverridenSittingCapacity(sittingCapacity);
		showTimeVO.setShowStatus( "RUNNING");
		IntStream intStream= IntStream.rangeClosed(1, sittingCapacity);
	   List<SeatVO>seats=	intStream.mapToObj(i->{
			SeatVO seatVO=new SeatVO();
			seatVO.setOrgCode(showTimeDTO.getTheatreVO().getOrgCode());
			seatVO.setIsBooked( "N");
			seatVO.setSeatNumber(Integer.toString(i));
			seatVO.setOrderNo( i);
			seatVO.setShowId(showTimeVO);
			showTimeVO.getSeats().add(seatVO);
			seatVO.setTheatreId(showTimeDTO.getTheaterId());
			seatVO.setOrgCode(showTimeDTO.getTheatreVO().getOrgCode());
			return  seatVO;
		}).collect(Collectors.toList());
	   
	   if(offers!=null && offers.size()>0) {
		 List<ShowOfferVO>showOffers=  offers.stream().map(o->{
			    ShowOfferVO showOffer=new ShowOfferVO();
			    showOffer.setOffer(o);
			    showOffer.setOrgCode(showTimeDTO.getTheatreVO().getOrgCode());
			    showOffer.setShowId(showTimeVO);
			     
			    return showOffer;
		   }).collect(Collectors.toList());
		 showTimeVO.setShowOffers(showOffers);
		 
	   }
	    
	   
	   return showTimeVO;
	}

}
