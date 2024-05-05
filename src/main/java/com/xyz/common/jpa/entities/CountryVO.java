package com.xyz.common.jpa.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

/**
 * The persistent class for the country database table.
 * 
 */
@Entity
@Table(name="country")
@NamedQuery(name="CountryVO.findAll", query="SELECT c FROM CountryVO c")
public class CountryVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String code;

	private String country;
	@OneToMany(mappedBy = "country",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<CityVO>cities=new ArrayList<>();

	public CountryVO() {
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<CityVO> getCities() {
		return cities;
	}

	public void setCities(List<CityVO> cities) {
		this.cities = cities;
	}
	
}