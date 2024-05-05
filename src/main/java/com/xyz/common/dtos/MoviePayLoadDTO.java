package com.xyz.common.dtos;

import lombok.Data;
import java.util.List;
import java.util.ArrayList;

@Data
public class MoviePayLoadDTO {
	
	private List<MovieInfoDTO>movies=new ArrayList<MovieInfoDTO>();
	
	

}
