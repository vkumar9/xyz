package com.xyz.repos.show;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xyz.common.jpa.entities.ShowTimeVO;

 
@Repository(value = "iShowTimeRepo")
public interface IShowTimeRepo extends JpaRepository<ShowTimeVO,Integer> {

}
