package com.xyz.repos.booking;

import java.math.BigInteger;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xyz.common.dtos.TheaterInfoDTO;
import com.xyz.common.jpa.entities.BookingVO;
import com.xyz.common.jpa.entities.CityOfferVO;
import com.xyz.common.jpa.entities.LogicProcessorVO;
import com.xyz.common.jpa.entities.SeatVO;
import com.xyz.common.jpa.entities.ShowOfferVO;
import com.xyz.common.jpa.entities.ShowTimeVO;
import com.xyz.common.jpa.entities.TheatreOfferVO;
import com.xyz.common.jpa.entities.TheatreVO;
import com.xyz.repos.common.BaseDAOImpl;
import com.xyz.repos.show.IShowTimeRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
@Repository(value = "iBookingDAO")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class BookingDAOImpl  extends BaseDAOImpl implements IBookingDAO {
	private static Logger logger = LoggerFactory.getLogger(BookingDAOImpl.class);
	@Autowired
	private IBookingRepo iBookingRepo;
	
	@Override
	public BookingVO saveBooking(BookingVO bookingVO) {
		 return iBookingRepo.save(bookingVO);
	}
	@Override
	public CityOfferVO getCityOfferByorgCode(String orgCode) {
		 EntityManager em=getEntityManager();
		 Query query=em.createNamedQuery( "CityOfferVO.findByActiveFlagByOrgCode");
		 query=query.setParameter("orgCode" , orgCode);
		 List<CityOfferVO>cityOffers=query.getResultList();
		 if(!cityOffers.isEmpty()) {
			 return cityOffers.get(0);
		 }else {
			 return null;
		 }
	} 
	@Override
	public TheatreOfferVO getTheaterOfferByorgCode(String orgCode,Integer theaterId) {
		 EntityManager em=getEntityManager();
		 Query query=em.createNamedQuery( "TheatreOfferVO.findByActiveFlagAndOrgCodeAndTheaterId");
		 query=query.setParameter("orgCode" , orgCode);
		 query=query.setParameter("theaterId" , theaterId);
		 List<TheatreOfferVO>theaterOffers=query.getResultList();
		 if(!theaterOffers.isEmpty()) {
			 return  theaterOffers.get(0);
		 }else {
			 return null;
		 }
	}
	@Override
	public List<ShowOfferVO> getSHowOffers(String orgCode, Integer showId) {
		 EntityManager em=getEntityManager();
		 Query query=em.createNamedQuery( "ShowOfferVO.findByorgCodeAndShowId");
		 query=query.setParameter("orgCode" , orgCode);
		 query=query.setParameter("showId" , showId);
		 List<ShowOfferVO>showOffers=query.getResultList();
		 return showOffers;
	}
	 
	public LogicProcessorVO getByorgCodeAndType(String orgCode, String type) {
		 EntityManager em=getEntityManager();
		 Query query=em.createNamedQuery( "LogicProcessorVO.findByOrgCodeAndtype");
		 query=query.setParameter( "orgCode", orgCode);
		 query=query.setParameter( "logicType", type);
		 List<LogicProcessorVO>logicProcessorVOs=query.getResultList();
		 if(!logicProcessorVOs.isEmpty()) {
			 return logicProcessorVOs.get(0);
		 }else {
			 
			 return null;
		 }
	}
	@Override
	public ShowTimeVO findByShowId(Integer showId) {
		EntityManager em=getEntityManager();
		Query query=em.createNamedQuery( "ShowTimeVO.findByShowId");
		query=query.setParameter( "showId", showId);
		List<ShowTimeVO>showTimes=query.getResultList();
		if(!showTimes.isEmpty()) {
			return showTimes.get(0);
		}else {
			return null;
		}
		  
	}
	@Override
	public Integer totalAvailableSeats(Integer showId) {
		 EntityManager em=getEntityManager();
		 Query query=em.createNamedQuery("SeatVO.findTotalVacantSeatsByShowId" );
		 query=query.setParameter( "showId",showId);
		 Long result = (Long) query.getSingleResult();
		 return result.intValue();
		 
	}
	@Override
	public Integer totalFilledSeats(Integer showId) {
		EntityManager em=getEntityManager();
		 Query query=em.createNamedQuery("SeatVO.findTotalFilledSeats" );
		 query=query.setParameter( "showId",showId);
		 Long result = (Long) query.getSingleResult();
		 return result.intValue();
	}
	@Override
	public List<SeatVO> getAvailableSeats(Integer showid, int totalSeats) {
		 EntityManager  em=getEntityManager();
		 Query query=em.createNamedQuery( "SeatVO.findAvailableSeats");
		 query=query.setParameter( "showId", showid);
		 query=query.setFirstResult(0);
		 query=query.setMaxResults(totalSeats);
		 List<SeatVO>seats=query.getResultList();
		 return seats;
	}
	@Override
	public ShowTimeVO updateShowTimeVO(ShowTimeVO showTimeVO) {
		  EntityManager em=getEntityManager();
		  return  em.merge(showTimeVO);
	}
	@Override
	public boolean cancelBooking(Integer bookingId) {
		 Query query=getEntityManager().createNamedQuery( "BookingVO.cancelByBookingID");
	     query =query.setParameter( "bookingId", bookingId);
	     int totalUpdates= query.executeUpdate();
	     if(totalUpdates>0) {
	    	 return Boolean.TRUE;
	     }else {
	    	 return Boolean.FALSE;
	     }
	    
	}
	@Override
	public boolean cancelSeats(Integer bookingId) {
		Query query=getEntityManager().createNamedQuery( "SeatVO.cancelSeatsByBookingId");
	     query =query.setParameter( "bookingId", bookingId);
	     int totalUpdates= query.executeUpdate();
	     if(totalUpdates>0) {
	    	 return Boolean.TRUE;
	     }else {
	    	 return Boolean.FALSE;
	     }
	}
	@Override
	public List<SeatVO> getPreferredSeats(List<String> seatNumbers,Integer showId) {
		EntityManager  em=getEntityManager();
		 Query query=em.createNamedQuery( "SeatVO.findPreferredSeats");
		 query=query.setParameter( "seatNumbers", seatNumbers);
		 query=query.setParameter( "showId", showId);
		 
		 List<SeatVO>seats=query.getResultList();
		 return seats;
	}
	@Override
	public List<SeatVO> getSeatsInfoByShowId(Integer showId) {
		 EntityManager em=getEntityManager();
		 Query query =em.createNamedQuery("SeatVO.getSeatInfoByShowId");
		 query=query.setParameter( "showTimeId", showId);
		 List<SeatVO>seats=query.getResultList();
		 return seats;
	}
	
	
 
 
}
