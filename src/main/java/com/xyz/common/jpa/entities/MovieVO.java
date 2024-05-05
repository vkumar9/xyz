package com.xyz.common.jpa.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import com.xyz.common.jpa.entities.base.BaseEntityVO;
 


/**
 * The persistent class for the movie database table.
 * 
 */
@Entity
@Table(name="movie")
@NamedQuery(name="MovieVO.findAll", query="SELECT m FROM MovieVO m")
public class MovieVO extends BaseEntityVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="movie_id")
	private int movieId;

	private Time duration;

	private String genre;

	@JoinColumn(name="language_code")
	@ManyToOne(fetch = FetchType.LAZY)
	private LanguageVO languageCode;

	@Temporal(TemporalType.DATE)
	@Column(name="released_date")
	private Date releasedDate;

	private String title;
    @OneToMany(mappedBy = "movie",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<ShowTimeVO>showTimeList=new ArrayList<>();

	 

	public MovieVO() {
	}

	public int getMovieId() {
		return this.movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	 

	 

	public Time getDuration() {
		return this.duration;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	 

	public Date getReleasedDate() {
		return this.releasedDate;
	}

	public void setReleasedDate(Date releasedDate) {
		this.releasedDate = releasedDate;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ShowTimeVO> getShowTimeList() {
		return showTimeList;
	}

	public void setShowTimeList(List<ShowTimeVO> showTimeList) {
		this.showTimeList = showTimeList;
	}

	public LanguageVO getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(LanguageVO languageCode) {
		this.languageCode = languageCode;
	}

 

	 

}