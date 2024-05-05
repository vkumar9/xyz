package com.xyz.common.dtos;

import lombok.Data;
import java.util.List;
import java.util.ArrayList;

@Data
public class MovieSearchDTO {
	
	
	private List<String>dates;
	
	private String genre;
	
	private String language;
	
	private String cityCode;
	
	
	 

}
