package com.xyz.common.component.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xyz.common.component.IBookingLogicExecution;
import com.xyz.common.constant.ApplicationConstant;
import com.xyz.common.dtos.DiscountInfoDTO;
import com.xyz.common.jpa.entities.OfferVO;
import com.xyz.common.jpa.entities.ShowOfferVO;
import com.xyz.common.jpa.entities.ShowTimeVO;
import com.xyz.repos.booking.IBookingDAO;

public class BookingLogicCombo implements IBookingLogicExecution{
	@Autowired
    private IBookingDAO iBookingDAO;
	@Override
	public DiscountInfoDTO executeBookingLogic(ShowTimeVO showTimeVO, Integer noOfTickets) {
		List<ShowOfferVO>showOffers=iBookingDAO.getSHowOffers(showTimeVO.getTheatre().getOrgCode(), showTimeVO.getShowId());
		   
		    
		   
		   List<Double>discounts= showOffers.stream().filter(s->{
			     if(s.getOffer().getOfferType().equalsIgnoreCase(ApplicationConstant.DISCOUNT_AFTER_3_TICKET)&& noOfTickets==null) {
			    	 return false;
			     }else if(s.getOffer().getOfferType().equalsIgnoreCase(ApplicationConstant.DISCOUNT_AFTER_3_TICKET)&& noOfTickets!=null && noOfTickets.intValue()<=2) {
			    	 return false;
			     }else {
			    	 return Boolean.TRUE;	
			    }
		   }).map(sh->sh.getOffer().getDiscountPercentage().doubleValue()).collect(Collectors.toList());
		   double sum= discounts.stream().mapToDouble(Double::doubleValue).sum();
		   DiscountInfoDTO discountInfoDTO=new DiscountInfoDTO();
		   
		   discountInfoDTO.setTotalDiscount(sum);
		   return discountInfoDTO;
	}

}
