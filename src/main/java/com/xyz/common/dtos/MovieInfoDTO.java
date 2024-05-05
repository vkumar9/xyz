package com.xyz.common.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
 
@Data
public class MovieInfoDTO {
	
	private String genre;
	private Integer movieId;
	private String language;
	private String title;
	
	private List<TheaterInfoDTO>theaters=new ArrayList<>();
	
	
}
