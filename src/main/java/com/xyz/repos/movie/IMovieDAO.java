package com.xyz.repos.movie;

import com.xyz.common.jpa.entities.MovieVO;

public interface IMovieDAO {
	
	public MovieVO saveMovie(MovieVO movie);

}
