package com.xyz.common.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterTheatreDTO {
	
	private Integer id;
	
	private String theatreCode;
	@NotEmpty(message = "City Code can not be a null or empty")
	private String cityCode;
	//@NotEmpty(message = "Org Code Code can not be a null or empty")
	private String orgCode;
	@NotEmpty(message = "address can not be a null or empty")
	private String address;
	@NotEmpty(message = "name can not be a null or empty")
	private String name;
	@NotNull(message = "Sitting capacity can not be a null or empty")
	private Integer sittingCapacity;

}
