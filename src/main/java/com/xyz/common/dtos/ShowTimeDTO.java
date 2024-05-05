package com.xyz.common.dtos;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xyz.common.jpa.entities.TheatreVO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.util.List;

@Data
public class ShowTimeDTO {
	
	private Integer showId;
	@NotNull(message = "Theater Id canot be null.")
	private Integer theaterId;
	@NotNull(message = "Movie Id canot be null.")
	private Integer movieId;
	@Pattern(regexp = "\\d{2}:\\d{2}:\\d{2}", message = "Invalid time format. Time must be in HH:MM:SS format.")
    @NotEmpty(message = "Duration can not be null or empty")
	private String showStartTime;
	@Pattern(regexp = "\\d{2}:\\d{2}:\\d{2}", message = "Invalid time format. Time must be in HH:MM:SS format.")
    @NotEmpty(message = "Duration can not be null or empty")
	private String showEndTime;
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Invalid date format. Date must be in yyyy-MM-dd format.")
	private String showDate;
	
	private List<String>showOfferCodes=new ArrayList<String>();
	
	private Integer sittingCapacity;
	
	@JsonIgnore
	 
	private TheatreVO theatreVO;
	@NotNull(message = "Ticket price canot be null.")
	private Double ticketPrice;
	
	

}
