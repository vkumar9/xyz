package com.xyz.common.jpa.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;

import com.xyz.common.jpa.entities.base.BaseEntityVO;


/**
 * The persistent class for the seat database table.
 * 
 */
@Entity
@Table(name="seat")
@NamedNativeQueries({
	@NamedNativeQuery(name="SeatVO.findTotalVacantSeatsByShowId",query="SELECT COUNT(seat_id) AS total_seats_available "
			+ "FROM Seat "
			+ "WHERE show_id =:showId "
			+ "AND is_booked = 'N'"),
	@NamedNativeQuery(name="SeatVO.findTotalFilledSeats",query="SELECT COUNT(seat_id) AS total_seats_available "
			+ "FROM Seat "
			+ "WHERE show_id =:showId "
			+ "AND is_booked = 'Y'"),
	
	@NamedNativeQuery(name="SeatVO.cancelSeatsByBookingId",query="UPDATE seat \r\n"
			+ "SET booking_id = NULL, is_booked = 'N' where booking_id=:bookingId "
			+ "")
})
@NamedQueries({
	@NamedQuery(name="SeatVO.findAvailableSeats", query="SELECT s FROM SeatVO s where s.showId.showId=:showId and s.isBooked='N' ORDER BY s.orderNo ASC  "),
	@NamedQuery(name="SeatVO.findPreferredSeats", query="SELECT s FROM SeatVO s where s.showId.showId=:showId and s.isBooked='N' and s.seatNumber in : seatNumbers    "),
	@NamedQuery(name="SeatVO.deleteByShowTimeId", query="DELETE FROM SeatVO s WHERE s.showId.showId = :showTimeId    "),
	@NamedQuery(name="SeatVO.getSeatInfoByShowId", query="SELECT s FROM SeatVO s WHERE s.showId.showId = :showTimeId     ORDER BY s.orderNo ASC  ")
})

public class SeatVO extends BaseEntityVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="seat_id")
	private int seatId;
	
	@Column(name="order_no")
	private Integer orderNo;
	 
	@JoinColumn(name="booking_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private BookingVO bookingId;
 

	@Column(name="is_booked")
	private String isBooked;

	@Column(name="org_code")
	private String orgCode;

	@Column(name="seat_number")
	private String seatNumber;

	@JoinColumn(name="show_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private ShowTimeVO showId;

	@Column(name="theatre_id")
	private int theatreId;

	 

	 

	public SeatVO() {
	}

	public int getSeatId() {
		return this.seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	 

	 

	 

	public BookingVO getBookingId() {
		return bookingId;
	}

	public void setBookingId(BookingVO bookingId) {
		this.bookingId = bookingId;
	}

	public String getIsBooked() {
		return this.isBooked;
	}

	public void setIsBooked(String isBooked) {
		this.isBooked = isBooked;
	}

	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getSeatNumber() {
		return this.seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	 

	public ShowTimeVO getShowId() {
		return showId;
	}

	public void setShowId(ShowTimeVO showId) {
		this.showId = showId;
	}

	public int getTheatreId() {
		return this.theatreId;
	}

	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	 

	 

	 

}