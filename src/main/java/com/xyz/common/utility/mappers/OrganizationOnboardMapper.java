package com.xyz.common.utility.mappers;

import java.util.Date;

import com.xyz.common.dtos.RegisterOrgDTO;
import com.xyz.common.dtos.RegisterTheatreDTO;
import com.xyz.common.jpa.entities.CityVO;
import com.xyz.common.jpa.entities.OrganizationVO;
import com.xyz.common.jpa.entities.TheatreVO;
import com.xyz.common.utility.CommonUtility;

public class OrganizationOnboardMapper  {
	
	
	public static OrganizationVO mapToOrgVO(RegisterOrgDTO registerOrgDTO) {
		OrganizationVO organization=new OrganizationVO();
		String orgCode=CommonUtility.generateUniqueCode();
		organization.setOrgCode(orgCode);
		organization.setAddress(registerOrgDTO.getAddress());
		organization.setEmail(registerOrgDTO.getEmail());
		organization.setPhone(registerOrgDTO.getPhone());
		organization.setOrgName(registerOrgDTO.getName());
	 
		return organization;
	}
	
   public static TheatreVO mapToTheatre(RegisterTheatreDTO registerTheaterDTO) {
	   TheatreVO theatreVO=new TheatreVO();
	   theatreVO.setAddress(registerTheaterDTO.getAddress());
	   String theaterCode=CommonUtility.generateUniqueCodeForTheater();
	   theatreVO.setOrgCode(registerTheaterDTO.getOrgCode());
	   theatreVO.setTheatreCode(theaterCode);
	   CityVO cityVO=new CityVO();
	   cityVO.setCode(registerTheaterDTO.getCityCode());
	   theatreVO.setCity(cityVO);
	   OrganizationVO organizationVO=new OrganizationVO();
	   organizationVO.setOrgCode(registerTheaterDTO.getOrgCode());
	   theatreVO.setOrgCode(registerTheaterDTO.getOrgCode());
	   theatreVO.setName(registerTheaterDTO.getName());
	   theatreVO.setSittingCapacity(registerTheaterDTO.getSittingCapacity());
	   return theatreVO;
   }

}
