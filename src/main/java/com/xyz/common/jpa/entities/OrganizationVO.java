package com.xyz.common.jpa.entities;

import java.io.Serializable;
import jakarta.persistence.*;
 

import com.xyz.common.jpa.entities.base.BaseEntityVO;


/**
 * The persistent class for the organization database table.
 * 
 */
@Entity
@Table(name="organization")
@NamedQuery(name="OrganizationVO.findAll", query="SELECT o FROM OrganizationVO o")
public class OrganizationVO extends BaseEntityVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="org_code")
	private String orgCode;
	private String email;

	@Column(name="org_name")
	private String orgName;

	private String phone;
    
	private String address;

	public OrganizationVO() {
	}

	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	 

	 
	 

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
    
	 

}