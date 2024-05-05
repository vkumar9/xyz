package com.xyz.repos.show;

import com.xyz.common.dtos.TheaterInfoDTO;
import com.xyz.common.jpa.entities.OfferVO;
import com.xyz.common.jpa.entities.ShowTimeVO;
import com.xyz.common.jpa.entities.TheatreVO;

import java.util.Date;
import java.util.List;
public interface IShowTimeDAO  {
	
	public TheatreVO findById(Integer id);
	
	public ShowTimeVO saveShowTimeVO(ShowTimeVO showTimeVO);
	
	public List<ShowTimeVO> findHowsByCityAndTitle(String title, String cityCode);
	public List<ShowTimeVO> findHowsByCityDates( String cityCode,List<String>showdates);
    public List<ShowTimeVO> findShowsByCityAndGenreAndLangWithDates(String cityCode,String genre,String languageCode ,List<Date>dates);
    public List<ShowTimeVO> findShowsByCityAndGenreWithDates(String cityCode,String genre, List<String>dates);
    public  ShowTimeVO  getShowTimeByOrgIdTheaterIdAndShowTimeId(Integer showTimeId, Integer theaterId, String orgCode);
    public ShowTimeVO updateShowTimeVO(ShowTimeVO showTimeVO);
    
    public TheatreVO findbyTheaterIdAndorgCode(Integer theaterId, String orgCode);
    
    public boolean cancelBookingByShowId(Integer showTimeId);
    public boolean deletShowOfferByShowId(Integer showTimeId);
    
    public boolean deleteSeatsByShowTimeId(Integer showTimeId);
    
    public List<OfferVO> findByOfferCodeAndOrgCode(List<String>offerCodes,String orgCode);
}
