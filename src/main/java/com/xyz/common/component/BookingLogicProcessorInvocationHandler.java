package com.xyz.common.component;

import java.lang.reflect.Constructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xyz.common.component.processor.DefaultBookingLogicProcessor;
import com.xyz.common.constant.ApplicationConstant;
import com.xyz.common.dtos.DiscountInfoDTO;
import com.xyz.common.jpa.entities.LogicProcessorVO;
import com.xyz.common.jpa.entities.ShowTimeVO;
import com.xyz.repos.booking.IBookingDAO;

@Component

public class BookingLogicProcessorInvocationHandler {
	 @Autowired
	 private AutowireCapableBeanFactory beanfactory;
	 @Autowired
	 @Qualifier("iDefaultBookingLogicExecution")
	 private   IBookingLogicExecution iBookingLogicExecution;
	 
	 @Autowired
	 private IBookingDAO iBookingDao;
	 public Object invokeBusinessPartnersLogic(ShowTimeVO showtimeVO,Integer numberOfTickets)throws Exception {
		 String orgCode=showtimeVO.getTheatre().getOrgCode();
		 LogicProcessorVO logicProcessorVO=iBookingDao.getByorgCodeAndType(orgCode, ApplicationConstant.DISCOUNT_LOGIC_TYPE);   
		 if(logicProcessorVO==null) {
			 DiscountInfoDTO dscounDiscountInfoDTO= iBookingLogicExecution.executeBookingLogic(showtimeVO, numberOfTickets);
			 return dscounDiscountInfoDTO;	
		 }else {
			 String logicName=logicProcessorVO.getLogicExecutionListener();
			 IBookingLogicExecution iBookingLogicExecution=(IBookingLogicExecution)createinstance(logicName);
			 beanfactory.autowireBean(iBookingLogicExecution);
			 DiscountInfoDTO dscounDiscountInfoDTO= iBookingLogicExecution.executeBookingLogic(showtimeVO, numberOfTickets);
			 return dscounDiscountInfoDTO;
		 }
		  
	 }
	 
	  
	 
	 private Object createinstance(String logicName)throws Exception {
		 Class cls=Class.forName(logicName);
		 
		 Constructor<?> constructor = cls.getDeclaredConstructor();
        
         constructor.setAccessible(true);
     
         Object instance = constructor.newInstance();
		 return instance;
	 }

}
