package com.xyz.repos.orgonboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xyz.common.jpa.entities.OrganizationVO;
import com.xyz.common.jpa.entities.TheatreVO;
import com.xyz.repos.common.BaseDAOImpl;

import jakarta.persistence.EntityManager;
@Repository(value = "ICatalogValueDAO")

@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class OrgOnBoardDAOImpl extends BaseDAOImpl  implements IOrgOnBoardDAO {
	private static Logger logger = LoggerFactory.getLogger(OrgOnBoardDAOImpl.class);
	@Autowired
	private ITheatreOnBoardRepo iTheatreOnBoardRepo;
	@Override
	public boolean registerOrg(OrganizationVO organizationVO) {
		 
		EntityManager em= getEntityManager();
		em.persist(organizationVO);
		return Boolean.TRUE;
		 
	}
	@Override
	public TheatreVO registerTheater(TheatreVO theaterVO) {
		TheatreVO theatreVO= iTheatreOnBoardRepo.save(theaterVO);
		return theaterVO;
		 
	}

}
