package com.xyz.common.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
@Data
public class BookingInfoDTO {
	
	private Integer bookingId;
	@NotNull(message = "ShowId can not be a null or empty")
	private Integer showId;
	@NotNull(message = "Total Tickets cannot be null.")
	private Integer totalTickets;
	
	private List<String>preferredSeats;
	
	private List<SeatInfoDTO>seatsInfo=new ArrayList<SeatInfoDTO>();
	
	

}
