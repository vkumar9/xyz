package com.xyz.repos.orgonboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.xyz.common.jpa.entities.TheatreVO;
@Repository(value = "iTheatreOnBoardRepo")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public interface ITheatreOnBoardRepo extends JpaRepository<TheatreVO,Integer>  {
	
      
     
     
}
