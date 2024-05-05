package com.xyz.common.jpa.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;

import com.xyz.common.jpa.entities.base.BaseEntityVO;


/**
 * The persistent class for the logic_processor database table.
 * 
 */
@Entity
@Table(name="logic_processor")
@NamedQueries({
	@NamedQuery(name="LogicProcessorVO.findAll", query="SELECT l FROM LogicProcessorVO l"),
	@NamedQuery(name="LogicProcessorVO.findByOrgCodeAndtype", query="SELECT l FROM LogicProcessorVO l where l.orgCode=:orgCode and l.logicType=:logicType"),
	
})
public class LogicProcessorVO extends BaseEntityVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="logic_processor_id")
	private int logicProcessorId;

	 

	 

	@Column(name="is_active")
	private String isActive;

	@Column(name="logic_execution_listener")
	private String logicExecutionListener;

	@Column(name="logic_type")
	private String logicType;

	@Column(name="org_code")
	private String orgCode;

	 

	 

	public LogicProcessorVO() {
	}

	public int getLogicProcessorId() {
		return this.logicProcessorId;
	}

	public void setLogicProcessorId(int logicProcessorId) {
		this.logicProcessorId = logicProcessorId;
	}

	 

	 

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getLogicExecutionListener() {
		return this.logicExecutionListener;
	}

	public void setLogicExecutionListener(String logicExecutionListener) {
		this.logicExecutionListener = logicExecutionListener;
	}

	public String getLogicType() {
		return this.logicType;
	}

	public void setLogicType(String logicType) {
		this.logicType = logicType;
	}

	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	 

	 

}