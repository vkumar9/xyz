package com.xyz.services.showtime;

import com.xyz.common.dtos.MoviePayLoadDTO;
import com.xyz.common.dtos.MovieSearchDTO;
import com.xyz.common.dtos.ShowTimeDTO;
import com.xyz.common.dtos.ShowTimeInfoDTO;
import com.xyz.common.jpa.entities.ShowTimeResponseDTO;
import java.util.List;
public interface IShowTimeService {
	
	public ShowTimeResponseDTO createShowTime(ShowTimeDTO showTimeDTO)throws Exception;
    public MoviePayLoadDTO getTheatersWithShowsByMovieTitleAndCityCode(String title, String cityCode);
    public MoviePayLoadDTO getTheatersWithShowsByCriteria( MovieSearchDTO movieSearchDTO)throws Exception;
    public boolean canCelShow(Integer  showId,Integer theaterId,String orgCode);
}
