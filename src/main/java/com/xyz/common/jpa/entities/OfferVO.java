package com.xyz.common.jpa.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import com.xyz.common.jpa.entities.base.BaseEntityVO;


/**
 * The persistent class for the offer database table.
 * 
 */
@Entity
@Table(name="offer")
@NamedQueries({
	@NamedQuery(name="OfferVO.findAll", query="SELECT o FROM OfferVO o"),
	@NamedQuery(name="OfferVO.findByofferCodeOrgCode", query="SELECT o FROM OfferVO o where o.orgCode=:orgCode and o.offerCode in :offerCodes")
})
public class OfferVO extends BaseEntityVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="offer_id")
	private int offerId;

	 

	@Lob
	private String description;

	@Column(name="discount_percentage")
	private BigDecimal discountPercentage;

	@Column(name="offer_code")
	private String offerCode;

	@Column(name="offer_type")
	private String offerType;

	@Column(name="org_code")
	private String orgCode;

	@OneToMany(mappedBy = "offer", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<ShowOfferVO>showOffers=new ArrayList<>();
	
	@OneToMany(mappedBy = "offer", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<CityOfferVO>cityOffers=new ArrayList<>();
	@OneToMany(mappedBy = "offer", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<TheatreOfferVO>theatreOffers=new ArrayList<>();

	 

	public OfferVO() {
	}

	public int getOfferId() {
		return this.offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	 

	 

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getDiscountPercentage() {
		return this.discountPercentage;
	}

	public void setDiscountPercentage(BigDecimal discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public String getOfferCode() {
		return this.offerCode;
	}

	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	public String getOfferType() {
		return this.offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public List<ShowOfferVO> getShowOffers() {
		return showOffers;
	}

	public void setShowOffers(List<ShowOfferVO> showOffers) {
		this.showOffers = showOffers;
	}

	public List<CityOfferVO> getCityOffers() {
		return cityOffers;
	}

	public void setCityOffers(List<CityOfferVO> cityOffers) {
		this.cityOffers = cityOffers;
	}

	public List<TheatreOfferVO> getTheatreOffers() {
		return theatreOffers;
	}

	public void setTheatreOffers(List<TheatreOfferVO> theatreOffers) {
		this.theatreOffers = theatreOffers;
	}

	 
	
	 

	 

	 
}