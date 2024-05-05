package com.xyz.common.dtos;

import lombok.Data;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
@Data
public class MoviesPayLoadDTO {
	@NotEmpty(message = "List cannot be empty") 
	private List<@Valid MovieDTO>movies=new ArrayList<MovieDTO>();
	
	private List<MovieResponseDTO>response=new ArrayList<>();
	
	

}
