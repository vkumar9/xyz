package com.xyz.common.jpa.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import com.xyz.common.jpa.entities.base.BaseEntityVO;

import java.sql.Timestamp;


/**
 * The persistent class for the booking database table.
 * 
 */
@Entity
@Table(name="booking")
@NamedQuery(name="BookingVO.findAll", query="SELECT b FROM BookingVO b")
@NamedNativeQueries({
	 @NamedNativeQuery(name="BookingVO.cancelByBookingID",query="UPDATE booking  "
	 		+ "SET booked_status = 'CANCELLED'  where booking_id=:bookingId "
	 		+ ""),
	 @NamedNativeQuery(name="BookingVO.cancelByShowTimeId",query="UPDATE booking  "
		 		+ "SET booked_status = 'CANCELLED'  where show_id=:showId "
		 		+ "")
})
public class BookingVO extends BaseEntityVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="booking_id")
	private int bookingId;

	@Temporal(TemporalType.DATE)
	@Column(name="booking_date")
	private Date bookingDate;
	
	@Column(name="booked_status")
	private String bookedStatus;

	@Column(name="city_offer_id")
	private Integer cityOfferId;
    @OneToMany(mappedBy = "bookingId",fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
	private List<SeatVO>seats=new ArrayList<SeatVO>(); 

	 

	@Column(name="org_code")
	private String orgCode;

	@JoinColumn(name="show_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private ShowTimeVO showtime;

	@Column(name="show_offer_id")
	private Integer showOfferId;

	@Column(name="theatre_offer_id")
	private Integer theatreOfferId;

	@Column(name="total_discount")
	private Integer totalDiscount;

	@Column(name="total_price")
	private BigDecimal totalPrice;

	@Column(name="total_tickets")
	private int totalTickets;

	 

	 

	@Column(name="user_id")
	private int userId;

	public BookingVO() {
	}

	public int getBookingId() {
		return this.bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public Date getBookingDate() {
		return this.bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	 

	 
	 
	 

	public Integer getCityOfferId() {
		return cityOfferId;
	}

	public void setCityOfferId(Integer cityOfferId) {
		this.cityOfferId = cityOfferId;
	}

	public Integer getShowOfferId() {
		return showOfferId;
	}

	public void setShowOfferId(Integer showOfferId) {
		this.showOfferId = showOfferId;
	}

	public Integer getTheatreOfferId() {
		return theatreOfferId;
	}

	public void setTheatreOfferId(Integer theatreOfferId) {
		this.theatreOfferId = theatreOfferId;
	}

	public void setTotalDiscount(Integer totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	 
	public ShowTimeVO getShowtime() {
		return showtime;
	}

	public void setShowtime(ShowTimeVO showtime) {
		this.showtime = showtime;
	}

	 
	 
	public int getTotalDiscount() {
		return this.totalDiscount;
	}

	public void setTotalDiscount(int totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public BigDecimal getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getTotalTickets() {
		return this.totalTickets;
	}

	public void setTotalTickets(int totalTickets) {
		this.totalTickets = totalTickets;
	}

	 

	 

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getBookedStatus() {
		return bookedStatus;
	}

	public void setBookedStatus(String bookedStatus) {
		this.bookedStatus = bookedStatus;
	}

	public List<SeatVO> getSeats() {
		return seats;
	}

	public void setSeats(List<SeatVO> seats) {
		this.seats = seats;
	}
	
	
}