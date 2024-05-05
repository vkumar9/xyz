package com.xyz.repos.movie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xyz.common.jpa.entities.MovieVO;
import com.xyz.repos.common.BaseDAOImpl;
@Repository(value = "IMovieDAO")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class MovieDAOImpl extends BaseDAOImpl  implements IMovieDAO {

	private static Logger logger = LoggerFactory.getLogger(MovieDAOImpl.class);
	@Autowired
    private IMovieRepository iMovieRepository;
	@Override
	@Transactional(  propagation = Propagation. REQUIRES_NEW)
	public MovieVO saveMovie(MovieVO movie) {
		return iMovieRepository.save(movie);
	}
}
