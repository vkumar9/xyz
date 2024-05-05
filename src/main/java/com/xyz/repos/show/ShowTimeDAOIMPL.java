package com.xyz.repos.show;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xyz.common.dtos.TheaterInfoDTO;
import com.xyz.common.jpa.entities.OfferVO;
import com.xyz.common.jpa.entities.ShowTimeVO;
import com.xyz.common.jpa.entities.TheatreVO;
import com.xyz.repos.common.BaseDAOImpl;
import com.xyz.repos.theater.ITheaterRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
@Repository(value = "iShowTimeDAO")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ShowTimeDAOIMPL extends BaseDAOImpl  implements IShowTimeDAO {
	private static Logger logger = LoggerFactory.getLogger(ShowTimeDAOIMPL.class);
	@Autowired
	private ITheaterRepo iTheaterRepo;
	@Autowired
	private IShowTimeRepo iSHowTimeRepo;
	@Override
	public TheatreVO findById(Integer id) {
		Optional<TheatreVO>optional= iTheaterRepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}
	@Override
	public ShowTimeVO saveShowTimeVO(ShowTimeVO showTimeVO) {
		 
		return iSHowTimeRepo.save(showTimeVO);
	}
	@Override
	public List<ShowTimeVO> findHowsByCityAndTitle(String title, String cityCode) {
		 
		 EntityManager em=getEntityManager();
		 Query query= em.createNamedQuery( "ShowTimeVO.findByMovieAndCity");
		 query=query.setParameter( "title", title.toUpperCase());
		 query=query.setParameter( "cityCode", cityCode);
		 List<ShowTimeVO>showTimes=query.getResultList();
		 return showTimes;
		 
	}
	@Override
	public List<ShowTimeVO> findHowsByCityDates(String cityCode, List<String> showdates) {
		 EntityManager em=getEntityManager();
		 Query query= em.createNamedQuery( "ShowTimeVO.findByCityAndDates");
		  
		 query=query.setParameter( "cityCode", cityCode);
		 query=query.setParameter( "showDates", showdates);
		 List<ShowTimeVO>showTimes=query.getResultList();
		 return showTimes;
	}
	@Override
	public List<ShowTimeVO> findShowsByCityAndGenreAndLangWithDates(String cityCode, String genre, String languageCode,
			List<Date> dates) {
		EntityManager em=getEntityManager();
		 Query query= em.createNamedQuery( "ShowTimeVO.findByCityAndDatesAndgenreANDlang");
		  
		 query=query.setParameter( "cityCode", cityCode);
		 query=query.setParameter( "showDates", dates);
		 query=query.setParameter( "genre", genre.toUpperCase());
		 query=query.setParameter( "langcode", languageCode);
		 List<ShowTimeVO>showTimes=query.getResultList();
		 return showTimes;
	}
	@Override
	public List<ShowTimeVO> findShowsByCityAndGenreWithDates(String cityCode, String genre ,
			List<String> dates) {
		  EntityManager em=getEntityManager();
		 Query query= em.createNamedQuery( "ShowTimeVO.findByCityAndDatesAndgenre");
		  
		 query=query.setParameter( "cityCode", cityCode);
		 query=query.setParameter( "showDates", dates);
		 query=query.setParameter( "genre", genre);
		 
		 List<ShowTimeVO>showTimes=query.getResultList();
		 return showTimes;
	}
	@Override
	public ShowTimeVO getShowTimeByOrgIdTheaterIdAndShowTimeId(Integer showTimeId, Integer theaterId, String orgCode) {
		 EntityManager em=getEntityManager();
		 Query query=em.createNamedQuery( "ShowTimeVO.findByShowIdTheaterIdOrgCode");
		 query =query.setParameter( "theaterId", theaterId);
		 query=query.setParameter( "showId", showTimeId) ;
		 query=query.setParameter( "orgCode", orgCode);
		 List<ShowTimeVO>results=query.getResultList();
		 if(results.size()>0) {
			 return results.get(0);
		 }
		 return null;
	}
	@Override
	public ShowTimeVO updateShowTimeVO(ShowTimeVO showTimeVO) {
		 EntityManager em=getEntityManager();
		 return em.merge(showTimeVO);
	}
	@Override
	public TheatreVO findbyTheaterIdAndorgCode(Integer theaterId, String orgCode) {
		 EntityManager em=getEntityManager();
		 Query query= em.createNamedQuery( "TheatreVO.findbyTheaterIdAndorgCode");
		 query=query.setParameter( "theaterId", theaterId);
		 query=query.setParameter( "orgCode", orgCode);
		 List<TheatreVO>results=query.getResultList();
		 if(results.size()>0) {
			 return results.get(0);
		 }
		 return null;
		  
				 
	}
	@Override
	public boolean cancelBookingByShowId(Integer showTimeId) {
		 EntityManager em=getEntityManager();
		 Query query=em.createNamedQuery( "BookingVO.cancelByShowTimeId");
		 query=query.setParameter( "showId", showTimeId);
		 int updates=query.executeUpdate();
		 return Boolean.TRUE;
	}
	@Override
	public boolean deleteSeatsByShowTimeId(Integer showTimeId) {
		 EntityManager em=getEntityManager();
		 Query query=em.createNamedQuery( "SeatVO.deleteByShowTimeId");
		 query=query.setParameter( "showTimeId", showTimeId);
		 query.executeUpdate();
		 return Boolean.TRUE;
	}
	@Override
	public boolean deletShowOfferByShowId(Integer showTimeId) {
		 EntityManager em=getEntityManager();
		 Query query=em.createNamedQuery( "ShowOfferVO.deleteByShowTimeId");
		 query=query.setParameter( "showTimeId", showTimeId);
		 query.executeUpdate();
		 return Boolean.TRUE;
	}
	@Override
	public List<OfferVO> findByOfferCodeAndOrgCode(List<String>offerCodes, String orgCode) {
		 EntityManager em=getEntityManager();
		 Query query=em.createNamedQuery( "OfferVO.findByofferCodeOrgCode");
		 query=query.setParameter( "offerCodes", offerCodes);
		 query=query.setParameter( "orgCode",orgCode);
		 List<OfferVO>offers=query.getResultList();
		 return offers;
		 
	}
}
