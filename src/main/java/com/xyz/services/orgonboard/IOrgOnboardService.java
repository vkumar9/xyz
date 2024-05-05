package com.xyz.services.orgonboard;

import com.xyz.common.dtos.RegisterOrgDTO;
import com.xyz.common.dtos.RegisterTheatreDTO;
import com.xyz.common.jpa.entities.TheatreVO;

public interface IOrgOnboardService {
	
	public RegisterOrgDTO registerOrganization(RegisterOrgDTO registerOrgDTO)throws Exception;
    
	public RegisterTheatreDTO registerTheater(RegisterTheatreDTO theateDto);
}
