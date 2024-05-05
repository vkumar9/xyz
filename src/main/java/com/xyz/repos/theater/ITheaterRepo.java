package com.xyz.repos.theater;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xyz.common.jpa.entities.TheatreVO;

@Repository(value = "iTheaterRepo")
public interface ITheaterRepo extends JpaRepository<TheatreVO,Integer>{

}
