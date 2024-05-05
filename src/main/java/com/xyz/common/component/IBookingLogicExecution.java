package com.xyz.common.component;

import com.xyz.common.jpa.entities.ShowTimeVO;

/**
 * 
 */
public interface IBookingLogicExecution {
	
	
	public <T> T executeBookingLogic(ShowTimeVO showTimeVO,Integer noOfTickets);

}
