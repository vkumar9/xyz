package com.xyz.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.common.constant.ApplicationConstant;
import com.xyz.common.constant.MessageConstants;
import com.xyz.common.dtos.MoviesPayLoadDTO;
 
import com.xyz.common.dtos.ResponseDTO;
import com.xyz.services.movie.IMovieService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path="/movie", produces = {MediaType.APPLICATION_JSON_VALUE})
public class MovieController {
	
	@Autowired
	private IMovieService iMovieService;
	@PostMapping("/create")
    public ResponseEntity<ResponseDTO> createMovies(@Valid @RequestBody MoviesPayLoadDTO moviePayLoadDTO)throws Exception {
	   
       try{
    	   moviePayLoadDTO=	iMovieService.saveMovies(moviePayLoadDTO);
	   return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(ApplicationConstant.STATUS_201, MessageConstants.MOVIE_CREATED_SUCCESS,Boolean.TRUE,moviePayLoadDTO));
       }catch(Exception e) {
    	   throw e;
       }
    }
}
