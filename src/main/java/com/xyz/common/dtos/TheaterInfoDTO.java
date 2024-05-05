package com.xyz.common.dtos;

import lombok.Data;
import java.util.List;
import java.util.ArrayList;
@Data
public class TheaterInfoDTO {
	 
	private String  theaterName;
	private String theaterAddress;
	private String theaterCode;
	private List<ShowTimeInfoDTO>showTimes=new ArrayList<>();
	 
	 
	private Integer theaterId;
	private Integer sittingCapacity;
	 
}
