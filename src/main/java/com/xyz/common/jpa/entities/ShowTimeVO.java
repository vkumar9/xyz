package com.xyz.common.jpa.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import com.xyz.common.jpa.entities.base.BaseEntityVO;

import java.sql.Timestamp;


/**
 * The persistent class for the show_time database table.
 * 
 */
@Entity
@Table(name="show_time")
@NamedQueries({
	@NamedQuery(name="ShowTimeVO.findAll", query="SELECT s FROM ShowTimeVO s"),
	@NamedQuery(name="ShowTimeVO.findByMovieAndCity",query="SELECT s FROM ShowTimeVO s where UPPER(s.movie.title)=:title and s.theatre.city.code=:cityCode and s.isActive='Y'  and s.showDate >= CURRENT_DATE "),
	@NamedQuery(name="ShowTimeVO.findByCityAndDates",query="SELECT s FROM ShowTimeVO s where s.theatre.city.code=:cityCode and s.isActive='Y' and CONCAT(FUNCTION('YEAR', s.showDate), '-' , FUNCTION('MONTH', s.showDate), '-' , FUNCTION('DAY', s.showDate)) IN :showDates and s.showStartTime >= CURRENT_TIME AND s.showDate >= CURRENT_DATE   "),
	@NamedQuery(name="ShowTimeVO.findByCityAndDatesAndgenre",query="SELECT s FROM ShowTimeVO s where s.theatre.city.code=:cityCode and s.isActive='Y' and CONCAT(FUNCTION('YEAR', s.showDate), '-' , FUNCTION('MONTH', s.showDate), '-' , FUNCTION('DAY', s.showDate)) IN :showDates and UPPER(s.movie.genre)=:genre   AND s.showDate >= CURRENT_DATE  "),
	@NamedQuery(name="ShowTimeVO.findByCityAndDatesAndgenreANDlang",query="SELECT s FROM ShowTimeVO s where s.theatre.city.code=:cityCode and s.isActive='Y' and DATE(s.showDate) IN :showDates and UPPER(s.movie.genre)=:genre  and s.movie.languageCode.languageCode=:langcode    AND s.showDate >= CURRENT_DATE "),
	@NamedQuery(name="ShowTimeVO.findByShowId",query="SELECT s FROM ShowTimeVO s where  s.showId=:showId and s.isActive='Y'  "),
	@NamedQuery(name="ShowTimeVO.findByShowIdTheaterIdOrgCode",query="SELECT s FROM ShowTimeVO s where  s.showId=:showId and s.isActive='Y' and s.theatre.theatreId=:theaterId and s.showId=:showId and s.theatre.orgCode=:orgCode ")
})
 
public class ShowTimeVO extends BaseEntityVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="show_id")
	private int showId;

	@Column(name="is_active")
	private String isActive="N";
	
	@Column(name="ticket_price")
	private BigDecimal ticketPrice;
    
	@JoinColumn(name="movie_id" )
	@ManyToOne(fetch = FetchType.LAZY)
	private MovieVO movie;
    @Column(name="overridden_sitting_capacity")
	private Integer overridenSittingCapacity;
    @Column(name="show_status")
    private String showStatus;
	@Temporal(TemporalType.DATE)
	@Column(name="show_date")
	private Date showDate;

	@Column(name="show_end_time")
	private Time showEndTime;

	@Column(name="show_start_time")
	private Time showStartTime;

	@JoinColumn(name="theatre_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private TheatreVO theatre;
	@OneToMany(mappedBy = "showtime", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<BookingVO>bookings=new ArrayList<>();
	@OneToMany(mappedBy = "showId",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<SeatVO>seats=new ArrayList<>();
	@OneToMany(mappedBy = "showId",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<ShowOfferVO>showOffers=new ArrayList<>();

	public ShowTimeVO() {
	}

	public int getShowId() {
		return this.showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}

	  

	public Date getShowDate() {
		return this.showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}

	public Time getShowEndTime() {
		return this.showEndTime;
	}

	public void setShowEndTime(Time showEndTime) {
		this.showEndTime = showEndTime;
	}

	public Time getShowStartTime() {
		return this.showStartTime;
	}

	public void setShowStartTime(Time showStartTime) {
		this.showStartTime = showStartTime;
	}

	public MovieVO getMovie() {
		return movie;
	}

	public void setMovie(MovieVO movie) {
		this.movie = movie;
	}

	public TheatreVO getTheatre() {
		return theatre;
	}

	public void setTheatre(TheatreVO theatre) {
		this.theatre = theatre;
	}

	public List<BookingVO> getBookings() {
		return bookings;
	}

	public void setBookings(List<BookingVO> bookings) {
		this.bookings = bookings;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public List<SeatVO> getSeats() {
		return seats;
	}

	public void setSeats(List<SeatVO> seats) {
		this.seats = seats;
	}

	public BigDecimal getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(BigDecimal ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public Integer getOverridenSittingCapacity() {
		return overridenSittingCapacity;
	}

	public void setOverridenSittingCapacity(Integer overridenSittingCapacity) {
		this.overridenSittingCapacity = overridenSittingCapacity;
	}

	public String getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(String showStatus) {
		this.showStatus = showStatus;
	}

	public List<ShowOfferVO> getShowOffers() {
		return showOffers;
	}

	public void setShowOffers(List<ShowOfferVO> showOffers) {
		this.showOffers = showOffers;
	}

	 
	 
	 

}