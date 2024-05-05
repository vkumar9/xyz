package com.xyz.common.dtos;

import lombok.Data;

@Data
public class SittingInfoDTO {
	private Integer seatId;
	
	private String seatNumber;
	
	private Integer orderNo;
	
	private String isBooked;

}
