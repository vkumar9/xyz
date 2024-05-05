package com.xyz.services.movie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xyz.common.dtos.MovieResponseDTO;
import com.xyz.common.dtos.MoviesPayLoadDTO;
import com.xyz.common.jpa.entities.MovieVO;
import com.xyz.common.utility.mappers.MovieMapper;
import com.xyz.repos.movie.IMovieDAO;

import java.util.ArrayList;
import java.util.List;
@Service(value = "iMovieService")
public class MovieServiceImpl  implements IMovieService {
	private static Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);
	
	@Autowired
	private IMovieDAO iMovieDAO;
	@Override
	@Transactional(  propagation = Propagation.REQUIRED)
	public MoviesPayLoadDTO saveMovies(MoviesPayLoadDTO moviePaylDto) {
		  List<MovieVO>movies=MovieMapper.mapMovie(moviePaylDto);
		  List<MovieResponseDTO>moviesResponseDTOs=new ArrayList<>();
		  for(MovieVO movie:movies) {
			  MovieResponseDTO movieResponseDTO=new MovieResponseDTO();
			  try {
				 
				  
				  movieResponseDTO.setTitle(movie.getTitle());
				 movie= iMovieDAO.saveMovie(movie);
				  movieResponseDTO.setCreatedStatus(Boolean.TRUE);
				  movieResponseDTO.setMovieId(movie.getMovieId());
				  
				   
			  }catch(Exception e) {
				  logger.error( "Exwception occurred while saving movie: "+movie.getTitle());
			      movieResponseDTO.setCreatedStatus(Boolean.FALSE);
			  }
			  moviesResponseDTOs.add(movieResponseDTO);
		  }
		  moviePaylDto.setResponse(moviesResponseDTOs);
		  return moviePaylDto;
		 
	}

}
