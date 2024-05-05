package com.xyz.common.utility.mappers;

import com.xyz.common.constant.ApplicationConstant;
import com.xyz.common.dtos.MovieInfoDTO;
import com.xyz.common.dtos.MoviesPayLoadDTO;
import com.xyz.common.dtos.ShowTimeDTO;
import com.xyz.common.dtos.ShowTimeInfoDTO;
import com.xyz.common.dtos.TheaterInfoDTO;
import com.xyz.common.jpa.entities.LanguageVO;
import com.xyz.common.jpa.entities.MovieVO;
import com.xyz.common.jpa.entities.ShowTimeVO;
import com.xyz.common.utility.CommonUtility;
import java.util.List;
import java.sql.Time;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
 
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
public class MovieMapper {
	private static Logger logger = LoggerFactory.getLogger(MovieMapper.class);
	public static List<MovieVO> mapMovie(MoviesPayLoadDTO moviePayLoadDTO)    {
		
	  List<MovieVO>movies=	moviePayLoadDTO.getMovies().stream().map(movieDTO->{
			MovieVO movieVO=new MovieVO();
			Time time=Time.valueOf(movieDTO.getDuration());
			movieVO.setDuration(time);
			movieVO.setGenre(movieDTO.getGenre());
			movieVO.setTitle(movieDTO.getTitle());
			LanguageVO languageVO=new LanguageVO();
			languageVO.setLanguageCode(movieDTO.getLanguageCode());
			movieVO.setLanguageCode(languageVO);
			try {
				Date releasedDate=CommonUtility.convertToDate(movieDTO.getReleasedDate(), ApplicationConstant.DATE_YYYY_MM_DD);
				movieVO.setReleasedDate(releasedDate);
			} catch (ParseException e) {
				logger.error("Invalid date format of movie :"+movieDTO.getTitle());
				return null;
			}
			 
			return movieVO;
		}).filter(a->{
			 if(a!=null) {
				 return Boolean.TRUE;
			 }else {
				return Boolean.FALSE;
			 }
		}).collect(Collectors.toList());
	  return movies;
		
		 
		
	}
	
	public static List<MovieInfoDTO>mapToMovieInfoDTO(List<ShowTimeVO>showTimeVOs){
		Map<Integer,MovieInfoDTO>movieInfoMap=new HashMap<Integer,MovieInfoDTO>();
		Map<Integer,TheaterInfoDTO>theaterMap=new HashMap<Integer,TheaterInfoDTO>();
		List<MovieInfoDTO>movies=new ArrayList< >();
		showTimeVOs.forEach(showTimeVO->{
			if(validateDateAndTime(showTimeVO.getShowDate(), showTimeVO.getShowStartTime())) {
				MovieInfoDTO movieInfoDTO=null;
				ShowTimeInfoDTO showTimeInfoDTO=new ShowTimeInfoDTO();
				showTimeInfoDTO.setShowTimeId(showTimeVO.getShowId());
				String showStartTime=CommonUtility.convertTimeToString(showTimeVO.getShowStartTime(),ApplicationConstant.TIME_HH_MM_SS);
				showTimeInfoDTO.setPrice(showTimeVO.getTicketPrice().toString());
				showTimeInfoDTO.setSittingCapacity(showTimeVO.getOverridenSittingCapacity());
				showTimeInfoDTO.setShowStartTime(showStartTime);
				showTimeInfoDTO.setShowEndTime(CommonUtility.convertTimeToString(showTimeVO.getShowEndTime(),ApplicationConstant.TIME_HH_MM_SS));
				showTimeInfoDTO.setShowDate(CommonUtility.convertTodateToString(showTimeVO.getShowDate(), ApplicationConstant.DATE_YYYY_MM_DD));
				if(movieInfoMap.get(showTimeVO.getMovie().getMovieId())==null) {
					movieInfoDTO=new MovieInfoDTO();
					movieInfoDTO.setGenre(showTimeVO.getMovie().getGenre());
					movieInfoDTO.setLanguage(showTimeVO.getMovie().getLanguageCode().getLanguage());
					movieInfoDTO.setMovieId(showTimeVO.getMovie().getMovieId());
					movieInfoDTO.setTitle(showTimeVO.getMovie().getTitle());
					movieInfoMap.put(movieInfoDTO.getMovieId(), movieInfoDTO);
					movies.add(movieInfoDTO);
					
				}else {
					movieInfoDTO=movieInfoMap.get(showTimeVO.getMovie().getMovieId());
				}
				TheaterInfoDTO theaterInfoDTO=null;
				if(theaterMap.get(showTimeVO.getTheatre().getTheatreId())==null) {
					theaterInfoDTO=new TheaterInfoDTO();
					theaterInfoDTO.setSittingCapacity(showTimeVO.getTheatre().getSittingCapacity());
					theaterInfoDTO.setTheaterId(showTimeVO.getTheatre().getTheatreId());
					theaterInfoDTO.setTheaterName(showTimeVO.getTheatre().getName() );
					theaterInfoDTO.setTheaterAddress(showTimeVO.getTheatre().getAddress());
					theaterInfoDTO.setTheaterCode(showTimeVO.getTheatre().getTheatreCode());
					theaterMap.put(theaterInfoDTO.getTheaterId(), theaterInfoDTO);
					movieInfoDTO.getTheaters().add(theaterInfoDTO);
				}else {
					theaterInfoDTO=theaterMap.get(showTimeVO.getTheatre().getTheatreId());
				}
				theaterInfoDTO.getShowTimes().add(showTimeInfoDTO);
			
			}
			
		});
		return movies;
	}
	
	
	private static  boolean  validateDateAndTime(Date showDate,Time showStartTime) {
		Date currentDate=getCurrentDate();
		if ( showDate.compareTo(currentDate)==0) {
			LocalTime currentTime = LocalTime.now();
			LocalTime localShowStartTime = showStartTime.toLocalTime();
			if ( localShowStartTime.isBefore(currentTime)) {
			    return Boolean.FALSE;
			} else {
			     return Boolean.TRUE;
			}
		} else {
		return 	Boolean.TRUE;
		}
	
	}
	
	private static Date getCurrentDate() {
		Calendar calendar = Calendar.getInstance();

        
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

         
        Date currentDate = calendar.getTime();
        return currentDate;
	}
	
	 

}
