package com.xyz.repos.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xyz.common.jpa.entities.MovieVO;

@Repository(value = "iMovieRepository")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public interface IMovieRepository extends JpaRepository<MovieVO,Integer> {
	
	

}
