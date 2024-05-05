package com.xyz.common.dtos;

import lombok.Data;

@Data
public class MovieResponseDTO {
	
	private Integer movieId;
	private String title;
	private boolean createdStatus;

}
