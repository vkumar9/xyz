package com.xyz.common.jpa.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;

import com.xyz.common.jpa.entities.base.BaseEntityVO;


/**
 * The persistent class for the city_offer database table.
 * 
 */
@Entity
@Table(name="city_offer")
@NamedQueries({
	@NamedQuery(name="CityOfferVO.findAll", query="SELECT c FROM CityOfferVO c"),
	@NamedQuery(name="CityOfferVO.findByActiveFlagByOrgCode", query="SELECT c FROM CityOfferVO c where c.orgCode=:orgCode and c.isActive='Y'"),
})

public class CityOfferVO extends BaseEntityVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="city_offer_id")
	private int cityOfferId;

	@Column(name="city_code")
	private String cityCode;

	@Column(name="is_active") 
	private String isActive="N";

	@JoinColumn(name="offer_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private OfferVO offer;

	@Column(name="org_code")
	private String orgCode;

	 

	 

	public CityOfferVO() {
	}

	public int getCityOfferId() {
		return this.cityOfferId;
	}

	public void setCityOfferId(int cityOfferId) {
		this.cityOfferId = cityOfferId;
	}

	public String getCityCode() {
		return this.cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	 

	 

	 
	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public OfferVO getOffer() {
		return offer;
	}

	public void setOffer(OfferVO offer) {
		this.offer = offer;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	 

	 

}