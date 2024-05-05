package com.xyz.repos.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xyz.common.jpa.entities.BookingVO;
@Repository(value = "iBookingRepo")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public interface IBookingRepo extends   JpaRepository<BookingVO,Integer> {

}
