package com.xyz.services.orgonboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xyz.common.dtos.RegisterOrgDTO;
import com.xyz.common.dtos.RegisterTheatreDTO;
import com.xyz.common.jpa.entities.OrganizationVO;
import com.xyz.common.jpa.entities.TheatreVO;
import com.xyz.common.utility.mappers.OrganizationOnboardMapper;
import com.xyz.repos.orgonboard.IOrgOnBoardDAO;
import com.xyz.repos.orgonboard.OrganizationOnboardRepository;


@Service(value = "iOrgOnboardService")
public class OrgOnBoardServiceImpl implements IOrgOnboardService {
     
	@Autowired
    private OrganizationOnboardRepository organizationOnboardRepository;	
	@Autowired
	private IOrgOnBoardDAO iorgOnboardDAO;
	@Override
	@Transactional(  propagation = Propagation.REQUIRED)
	public RegisterOrgDTO registerOrganization(RegisterOrgDTO registerOrgDTO)throws Exception {
		 
	   OrganizationVO organization=	OrganizationOnboardMapper.mapToOrgVO(registerOrgDTO);
	   iorgOnboardDAO.registerOrg(organization);
	   registerOrgDTO.setOrgCode(organization.getOrgCode());
	   return registerOrgDTO;
		
	}
	@Override
	@Transactional(  propagation = Propagation.REQUIRED)
	public RegisterTheatreDTO registerTheater(RegisterTheatreDTO registerTheatreDTO) {
		 
		 TheatreVO theatreVO=OrganizationOnboardMapper.mapToTheatre(registerTheatreDTO);
		 theatreVO= iorgOnboardDAO.registerTheater(theatreVO);
		 registerTheatreDTO.setId(theatreVO.getTheatreId()); 
		 registerTheatreDTO.setTheatreCode(theatreVO.getTheatreCode());
		 return registerTheatreDTO;
	}

}
