package com.xyz.repos.orgonboard;

import com.xyz.common.jpa.entities.OrganizationVO;
import com.xyz.common.jpa.entities.TheatreVO;

public interface IOrgOnBoardDAO {
	
	public boolean registerOrg(OrganizationVO organizationVO);
	
	public TheatreVO registerTheater(TheatreVO theaterVO);
	

}
