package com.xyz.common.jpa.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import com.xyz.common.jpa.entities.base.BaseEntityVO;


/**
 * The persistent class for the theatre database table.
 * 
 */
@Entity
@Table(name="theatre")
@NamedQueries({
	@NamedQuery(name="TheatreVO.findAll", query="SELECT t FROM TheatreVO t"),
	@NamedQuery(name="TheatreVO.findbyTheaterIdAndorgCode", query="SELECT t FROM TheatreVO t where t.theatreId=:theaterId and t.orgCode=:orgCode")
	
})

public class TheatreVO extends BaseEntityVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="theatre_id")
	private int theatreId;

	private String address;

	@JoinColumn(name="city_code")
	@ManyToOne(fetch = FetchType.LAZY)
	private CityVO city;

 

	 

	private String name;

	@Column(name="org_code")
	private String orgCode;

	@Column(name="sitting_capacity")
	private int sittingCapacity;

	@Column(name="theatre_code")
	private String theatreCode;

	@OneToMany(mappedBy = "theatre", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<ShowTimeVO>showList=new ArrayList<>();

	 

	public TheatreVO() {
	}

	public int getTheatreId() {
		return this.theatreId;
	}

	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	 

	public CityVO getCity() {
		return city;
	}

	public void setCity(CityVO city) {
		this.city = city;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public int getSittingCapacity() {
		return this.sittingCapacity;
	}

	public void setSittingCapacity(int sittingCapacity) {
		this.sittingCapacity = sittingCapacity;
	}

	public String getTheatreCode() {
		return this.theatreCode;
	}

	public void setTheatreCode(String theatreCode) {
		this.theatreCode = theatreCode;
	}

	public List<ShowTimeVO> getShowList() {
		return showList;
	}

	public void setShowList(List<ShowTimeVO> showList) {
		this.showList = showList;
	}

	 

	 

}