package com.xyz.common.dtos;

import lombok.Data;

@Data
public class ShowTimeInfoDTO {
	
	 
	private Integer showTimeId;
	private String  showStartTime;
	private String showEndTime;
	private Integer vacantSeats;
	
	private String showDate;
	
	private String price;
	
	private Integer sittingCapacity;
	 
	 
	

}
