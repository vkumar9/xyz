package com.xyz.repos.orgonboard;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.xyz.common.jpa.entities.OrganizationVO;
@Repository(value="OrganizationOnboardRepository")
public interface OrganizationOnboardRepository   extends JpaRepository<OrganizationVO, String> {
  
	  @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	  Optional<OrganizationVO> findById(String id)  ;
	
}
