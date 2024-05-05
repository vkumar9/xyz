package com.xyz.services.showtime;

import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xyz.common.constant.ApplicationConstant;
import com.xyz.common.dtos.MovieInfoDTO;
import com.xyz.common.dtos.MoviePayLoadDTO;
import com.xyz.common.dtos.MovieSearchDTO;
import com.xyz.common.dtos.ShowTimeDTO;
import com.xyz.common.jpa.entities.MovieVO;
import com.xyz.common.jpa.entities.OfferVO;
import com.xyz.common.jpa.entities.SeatDTO;
import com.xyz.common.jpa.entities.ShowOfferVO;
import com.xyz.common.jpa.entities.ShowTimeResponseDTO;
import com.xyz.common.jpa.entities.ShowTimeVO;
import com.xyz.common.jpa.entities.TheatreVO;
import com.xyz.common.utility.CommonUtility;
import com.xyz.common.utility.mappers.MovieMapper;
import com.xyz.common.utility.mappers.ShowTimeMapper;
import com.xyz.repos.movie.IMovieRepository;
import com.xyz.repos.show.IShowTimeDAO;
import java.util.Map;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service(value = "iShowTimeService")
public class ShowTimeServiceImpl implements IShowTimeService {
	private static Logger logger = LoggerFactory.getLogger(ShowTimeServiceImpl.class);
	@Autowired
	private IShowTimeDAO iShowTimeDAO;
	@Autowired
	IMovieRepository iMovieRepository;
	@Override
	@Transactional(  propagation = Propagation.REQUIRED)
	public ShowTimeResponseDTO createShowTime(ShowTimeDTO showTimeDTO) throws Exception {
		  TheatreVO theaterVo=iShowTimeDAO.findById(showTimeDTO.getTheaterId());
		  showTimeDTO.setTheatreVO(theaterVo);
		  List<OfferVO>offers=new ArrayList<>();
		  if(showTimeDTO.getShowOfferCodes()!=null && showTimeDTO.getShowOfferCodes().size()>0) {
			  offers=iShowTimeDAO.findByOfferCodeAndOrgCode(showTimeDTO.getShowOfferCodes(), theaterVo.getOrgCode());
		  }
		  ShowTimeVO showTimeVO=ShowTimeMapper.mapShowTime(showTimeDTO,offers);
		  Optional<MovieVO> optionalMovie=iMovieRepository.findById(showTimeDTO.getMovieId());
		  if(optionalMovie.isPresent()) {
			  MovieVO movie=optionalMovie.get();
			  showTimeVO.setMovie(movie);
			  movie.getShowTimeList().add(showTimeVO);
		  }else {
			  //Set custom exception
			  throw new Exception("Movie does not exist");
		  }
		  showTimeVO= iShowTimeDAO.saveShowTimeVO(showTimeVO);
		  ShowTimeResponseDTO showTimeResponseDTO=new ShowTimeResponseDTO();
		  showTimeResponseDTO.setShowTimeId(showTimeVO.getShowId());
		  List<SeatDTO>seats= showTimeVO.getSeats().stream().map(s->{
			  SeatDTO seatDTO=new SeatDTO();
			  seatDTO.setSeatId(s.getSeatId());
			  seatDTO.setSeatNumber(s.getSeatNumber());
			  return seatDTO;
		  }).collect(Collectors.toList());
		  showTimeResponseDTO.setSeats(seats);
		  return showTimeResponseDTO;
		  
		  
		  
		  
	}
	@Override
	public MoviePayLoadDTO getTheatersWithShowsByMovieTitleAndCityCode(String title, String cityCode) {
		List<ShowTimeVO>showTimes=iShowTimeDAO.findHowsByCityAndTitle(title, cityCode) ;
	    List<MovieInfoDTO>movies=MovieMapper.mapToMovieInfoDTO(showTimes);
	    MoviePayLoadDTO moviePayLoadDTO=new MoviePayLoadDTO();
	    moviePayLoadDTO.setMovies(movies);
		return moviePayLoadDTO;
	}
	@Override
	public MoviePayLoadDTO getTheatersWithShowsByCriteria(MovieSearchDTO movieSearchDTO)throws Exception {
		 MoviePayLoadDTO moviePayLoadDTO=new MoviePayLoadDTO();
		 List<ShowTimeVO>showTimes=null;
		 
		 if(!movieSearchDTO.getDates().isEmpty()&& StringUtils.isNotBlank(movieSearchDTO.getGenre())&& StringUtils.isNotBlank(movieSearchDTO.getLanguage())) {
			 List<Date>dates=movieSearchDTO.getDates().stream().map(sd->{
				  Date date;
				try {
					date = CommonUtility.convertToDate(sd, ApplicationConstant.DATE_YYYY_MM_DD);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					return null;
				}
			      return date;
			 }).filter(a->a!=null). collect(Collectors.toList());
			 showTimes= iShowTimeDAO.findShowsByCityAndGenreAndLangWithDates(movieSearchDTO.getCityCode(), movieSearchDTO.getGenre(), movieSearchDTO.getLanguage(), dates);
		 }else if(!movieSearchDTO.getDates().isEmpty()&& StringUtils.isNotBlank(movieSearchDTO.getGenre()) ) {
			 showTimes= iShowTimeDAO.findShowsByCityAndGenreWithDates(movieSearchDTO.getCityCode(), movieSearchDTO.getGenre(),movieSearchDTO.getDates());
		 }else {
			 showTimes=new ArrayList<>();
		 }
		 List<MovieInfoDTO>movies=MovieMapper.mapToMovieInfoDTO(showTimes);
		 moviePayLoadDTO.setMovies(movies);
		 return moviePayLoadDTO;
	}
	@Override
	@Transactional(  propagation = Propagation.REQUIRED)
	public boolean canCelShow(Integer showId, Integer theaterId,String orgCode) {
		  
		 ShowTimeVO showTimeVO=iShowTimeDAO.getShowTimeByOrgIdTheaterIdAndShowTimeId(showId, theaterId, orgCode);
	     showTimeVO.setIsActive( "N");
	     showTimeVO.setShowStatus( "CANCELLED");
	     logger.debug(""+showTimeVO.getSeats().size());
	     showTimeVO.setSeats(new ArrayList<>());
	     iShowTimeDAO.updateShowTimeVO(showTimeVO); 
	     iShowTimeDAO.deleteSeatsByShowTimeId(showId);
	     iShowTimeDAO.deletShowOfferByShowId(showId);
		 iShowTimeDAO.cancelBookingByShowId(showId);
		 return Boolean.TRUE;
		   
	}
}
