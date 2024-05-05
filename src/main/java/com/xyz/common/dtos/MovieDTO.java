package com.xyz.common.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MovieDTO {
	
	private Integer movieId;
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Invalid date format. Date must be in yyyy-MM-dd format.")
	private String releasedDate;
	@NotEmpty(message = "Title can not be a null or empty")
	private String title;
	@NotEmpty(message = "Language can not be a null or empty")
	private String languageCode;
	@NotEmpty(message = "Genre can not be a null or empty")
	private String genre;
	@Pattern(regexp = "\\d{2}:\\d{2}:\\d{2}", message = "Invalid time format. Time must be in HH:MM:SS format.")
    @NotEmpty(message = "Duration can not be null or empty")
    private String duration;
	
	private boolean createStatus;
	
	
	
	
	
	

}
