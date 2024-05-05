package com.xyz.common.jpa.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

/**
 * The persistent class for the city database table.
 * 
 */
@Entity
@Table(name="city")
@NamedQuery(name="CityVO.findAll", query="SELECT c FROM CityVO c")
public class CityVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String code;

	@Column(name="city_name")
	private String cityName;

	@JoinColumn(name="country_code",insertable = false,updatable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private CountryVO country;
	
	@OneToMany(mappedBy = "city",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<TheatreVO>theaters=new ArrayList<>();

	public CityVO() {
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public CountryVO getCountry() {
		return country;
	}

	public void setCountry(CountryVO country) {
		this.country = country;
	}

	public List<TheatreVO> getTheaters() {
		return theaters;
	}

	public void setTheaters(List<TheatreVO> theaters) {
		this.theaters = theaters;
	}

	 

}