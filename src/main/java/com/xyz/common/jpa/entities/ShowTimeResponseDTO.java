package com.xyz.common.jpa.entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class ShowTimeResponseDTO {
	
	private Integer showTimeId;
	
	private List<SeatDTO>seats=new ArrayList<>();
	
	
	
	

}
