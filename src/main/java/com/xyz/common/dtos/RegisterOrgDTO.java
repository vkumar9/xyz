package com.xyz.common.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterOrgDTO {
	
	
	private String orgCode;
	@NotEmpty(message = "Email address can not be a null or empty")
    @Email(message = "Email address should be a valid value")
	private String email;
	@NotEmpty(message = "Name can not be a null or empty")
    @Size(min = 5, max = 99, message = "The length of the organization name should be between 5 and 99")
	private String name;
	@NotEmpty(message = "Phone can not be a null or empty")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
	private String phone;
	
	@NotEmpty(message = "Address can not be a null or empty")
	private String address;
	

}
