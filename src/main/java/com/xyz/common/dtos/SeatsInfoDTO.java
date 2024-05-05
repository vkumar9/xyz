package com.xyz.common.dtos;
import java.util.List;

import lombok.Data;

import java.util.ArrayList;
@Data
public class SeatsInfoDTO {
	private Integer totalFilledSeats;
	private Integer totalVailableSeats;
	private List<SittingInfoDTO>availableSeats=new ArrayList<>();
	
	private List<SittingInfoDTO>filledSeats=new ArrayList<>();
	
	

}
