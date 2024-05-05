package com.xyz.rest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.common.constant.ApplicationConstant;
import com.xyz.common.constant.MessageConstants;
import com.xyz.common.dtos.BookingInfoDTO;
import com.xyz.common.dtos.MoviePayLoadDTO;
import com.xyz.common.dtos.MovieSearchDTO;
import com.xyz.common.dtos.MoviesPayLoadDTO;
import com.xyz.common.dtos.ResponseDTO;
import com.xyz.common.dtos.SeatsInfoDTO;
import com.xyz.common.dtos.ShowTimeDTO;
import com.xyz.common.jpa.entities.ShowTimeResponseDTO;
import com.xyz.services.booking.IBookingService;
import com.xyz.services.showtime.IShowTimeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path="/showtime", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ShowTimeRestController {
	private static Logger logger = LoggerFactory.getLogger(ShowTimeRestController.class);
	@Autowired
	private IShowTimeService iShowTimeService;
	@Autowired
	private IBookingService iBookingService;
	
	@PostMapping("/create")
    public ResponseEntity<ResponseDTO> createShowTime(@Valid @RequestBody ShowTimeDTO showTimeDTO)throws Exception {
	   
       try{
    	   ShowTimeResponseDTO showTieResposeDto=iShowTimeService.createShowTime(showTimeDTO);
	   return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(ApplicationConstant.STATUS_201, MessageConstants.SHOWTIME_CREATED,Boolean.TRUE,showTieResposeDto));
       }catch(Exception e) {
    	   throw e;
       }
    }
	
	@GetMapping("/getshows/{movieTitle}/{cityCode}")
    public ResponseEntity<ResponseDTO> getShows( @PathVariable("movieTitle") String movieTitle,@PathVariable("cityCode") String cityCode )throws Exception {
	   
       try{
    	   MoviePayLoadDTO moviePayLoadDTO=iShowTimeService.getTheatersWithShowsByMovieTitleAndCityCode(movieTitle, cityCode);
	   return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(ApplicationConstant.STATUS_200,  "",Boolean.TRUE,moviePayLoadDTO));
       }catch(Exception e) {
    	   throw e;
       }
    }
	
	@PostMapping("/getshowsbycriteria")
    public ResponseEntity<ResponseDTO> getShowsByCriteria(  @RequestBody MovieSearchDTO movieSearchDTO)throws Exception {
	   
       try{
    	   MoviePayLoadDTO moviePayLoadDTO=iShowTimeService.getTheatersWithShowsByCriteria(movieSearchDTO);
	   return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(ApplicationConstant.STATUS_200,  "",Boolean.TRUE,moviePayLoadDTO));
       }catch(Exception e) {
    	   throw e;
       }
    }
	
	@PostMapping("/bookshow")
    public ResponseEntity<ResponseDTO> bookShow(  @RequestBody @Valid BookingInfoDTO bookingInfoDTO)throws Exception {
	   
       try{
    	   bookingInfoDTO=iBookingService.bookShow(bookingInfoDTO);
	   return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(ApplicationConstant.STATUS_200,MessageConstants.SHOW_BOOK_SUCCESSS,Boolean.TRUE,bookingInfoDTO));
       }catch(Exception e) {
    	   throw e;
       }
    }
	
	@PutMapping("/cancelbooking/{bookingId}")
    public ResponseEntity<ResponseDTO> cancelBooking(  @PathVariable("bookingId")Integer bookingId)throws Exception {
	   
       try{
    	   Boolean status=iBookingService.cancelBooking(bookingId);
	   return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(ApplicationConstant.STATUS_200,MessageConstants.BOOKING_CANCELLED,Boolean.TRUE,status));
       }catch(Exception e) {
    	   throw e;
       }
    }
	
	@PutMapping("/cancelshow/{theaterId}/{orgCode}/{showId}")
    public ResponseEntity<ResponseDTO> cancelShow(  @PathVariable("theaterId")Integer theaterId,@PathVariable("orgCode")String orgCode,@PathVariable("showId")Integer showId )throws Exception {
	   
       try{
    	   Boolean status= iShowTimeService.canCelShow(showId, theaterId, orgCode);
	   return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(ApplicationConstant.STATUS_200,MessageConstants.SHOW_CANCELLED,Boolean.TRUE,status));
       }catch(Exception e) {
    	   throw e;
       }
    }
	
	
	@GetMapping("/seatinfo/{showid}/")
    public ResponseEntity<ResponseDTO> getSeatInfo(  @PathVariable("showid")Integer showId )throws Exception {
	   
       try{
    	   SeatsInfoDTO seatsInfoDTO=iBookingService.getSeatsInfoByShowId(showId);
	   return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(ApplicationConstant.STATUS_200, "",Boolean.TRUE,seatsInfoDTO));
       }catch(Exception e) {
    	   throw e;
       }
    }

}
