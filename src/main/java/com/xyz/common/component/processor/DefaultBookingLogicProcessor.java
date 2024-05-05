package com.xyz.common.component.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xyz.common.component.IBookingLogicExecution;
import com.xyz.common.constant.ApplicationConstant;
import com.xyz.common.dtos.DiscountInfoDTO;
import com.xyz.common.dtos.TheaterInfoDTO;
import com.xyz.common.jpa.entities.CityOfferVO;
import com.xyz.common.jpa.entities.OfferVO;
import com.xyz.common.jpa.entities.ShowOfferVO;
import com.xyz.common.jpa.entities.ShowTimeVO;
import com.xyz.common.jpa.entities.TheatreOfferVO;
import com.xyz.common.jpa.entities.TheatreVO;
import com.xyz.repos.booking.IBookingDAO;
import com.xyz.repos.booking.IBookingRepo;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;
@Component(value ="iDefaultBookingLogicExecution" )
public class DefaultBookingLogicProcessor  implements IBookingLogicExecution {
	@Autowired
    private IBookingDAO iBookingDAO;
	@Override
	public DiscountInfoDTO executeBookingLogic(ShowTimeVO showTimeVO,Integer noOfTickets) {
		   CityOfferVO cityOfferVO=iBookingDAO.getCityOfferByorgCode(showTimeVO.getTheatre().getOrgCode());
		   TheatreOfferVO theatreOfferVO=iBookingDAO.getTheaterOfferByorgCode(showTimeVO.getTheatre().getOrgCode(), showTimeVO.getTheatre().getTheatreId());
		   List<ShowOfferVO>showOffers=iBookingDAO.getSHowOffers(showTimeVO.getTheatre().getOrgCode(), showTimeVO.getShowId());
		   List<OfferVO>offers=new ArrayList<OfferVO>();
		   if(theatreOfferVO!=null) {
			   offers.add(theatreOfferVO.getOffer());
		   }
		   if(cityOfferVO!=null) {
			   offers.add(cityOfferVO.getOffer());
		   }
		   List<Double>discountsOffers=offers.stream().map(o->o.getDiscountPercentage().doubleValue()).collect(Collectors.toList());
		   List<Double>discounts= showOffers.stream().filter(s->{
			     if(s.getOffer().getOfferType().equalsIgnoreCase(ApplicationConstant.DISCOUNT_AFTER_3_TICKET)&& noOfTickets==null) {
			    	 return false;
			     }else if(s.getOffer().getOfferType().equalsIgnoreCase(ApplicationConstant.DISCOUNT_AFTER_3_TICKET)&& noOfTickets!=null && noOfTickets.intValue()<=2) {
			    	 return Boolean.FALSE;
			     } 
			     else {
			    	 return Boolean.TRUE;			     }
		   }).map(sh->sh.getOffer().getDiscountPercentage().doubleValue()).collect(Collectors.toList());
		   discounts.addAll(discountsOffers);
		   DiscountInfoDTO discountInfoDTO=new DiscountInfoDTO();
		   Optional<Double> maxDiscount = discounts.stream().max(Double::compareTo);
		   if(maxDiscount.isEmpty()) {
		   discountInfoDTO.setTotalDiscount(Double.valueOf(0));
		   }else {
			   discountInfoDTO.setTotalDiscount(maxDiscount.get());
		   }
		   return discountInfoDTO;
	}
	
	

}
