package com.xyz.common.jpa.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;

import com.xyz.common.jpa.entities.base.BaseEntityVO;


/**
 * The persistent class for the theatre_offer database table.
 * 
 */
@Entity
@Table(name="theatre_offer")
@NamedQueries({
	@NamedQuery(name="TheatreOfferVO.findAll", query="SELECT t FROM TheatreOfferVO t"),
	@NamedQuery(name="TheatreOfferVO.findByActiveFlagAndOrgCodeAndTheaterId", query="SELECT t FROM TheatreOfferVO t where t.isActive='Y' and t.orgCode=:orgCode and t.theatreId=:theaterId"),
})

public class TheatreOfferVO extends BaseEntityVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="theatre_offer_id")
	private int theatreOfferId;

	@Column(name="is_active") 
	private String isActive="N";

	@JoinColumn(name="offer_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private OfferVO offer;

	@Column(name="org_code")
	private String orgCode;

	@Column(name="theatre_id")
	private int theatreId;

	 

	 

	public TheatreOfferVO() {
	}

	public int getTheatreOfferId() {
		return this.theatreOfferId;
	}

	public void setTheatreOfferId(int theatreOfferId) {
		this.theatreOfferId = theatreOfferId;
	}

	 

	 

	 

	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public int getTheatreId() {
		return this.theatreId;
	}

	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
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