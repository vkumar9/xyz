package com.xyz.common.jpa.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;

import com.xyz.common.jpa.entities.base.BaseEntityVO;


/**
 * The persistent class for the show_offer database table.
 * 
 */
@Entity
@Table(name="show_offer")
@NamedQueries({
	@NamedQuery(name="ShowOfferVO.findAll", query="SELECT s FROM ShowOfferVO s"),
	@NamedQuery(name="ShowOfferVO.findByorgCodeAndShowId", query="SELECT s FROM ShowOfferVO s where s.orgCode=:orgCode and s.showId.showId=:showId"),
	@NamedQuery(name="ShowOfferVO.deleteByShowTimeId", query="DELETE FROM ShowOfferVO s WHERE s.showId.showId = :showTimeId    ")
})
public class ShowOfferVO extends BaseEntityVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="show_offer_id")
	private int showOfferId;

	 

	 

	@JoinColumn(name="offer_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private OfferVO offer;

	@Column(name="org_code")
	private String orgCode;

	@JoinColumn(name="show_id")
	@ManyToOne(fetch = FetchType.LAZY)	
	private ShowTimeVO showId;

	 

	public ShowOfferVO() {
	}

	public int getShowOfferId() {
		return this.showOfferId;
	}

	public void setShowOfferId(int showOfferId) {
		this.showOfferId = showOfferId;
	}

	 

	 

	 

	public OfferVO getOffer() {
		return offer;
	}

	public void setOffer(OfferVO offer) {
		this.offer = offer;
	}

	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public ShowTimeVO getShowId() {
		return showId;
	}

	public void setShowId(ShowTimeVO showId) {
		this.showId = showId;
	}

	 

	 

	 

}