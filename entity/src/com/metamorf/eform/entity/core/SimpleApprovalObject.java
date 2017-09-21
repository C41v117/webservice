package com.metamorf.eform.entity.core;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.annotations.Expose;
import com.metamorf.eform.common.core.SystemConstant;
import com.metamorf.eform.common.core.SystemConstant.MasterDataApprovalStatus;
import com.metamorf.eform.common.core.SystemConstant.MasterDataStatus;
import com.metamorf.eform.common.enumer.ActionType;
import com.metamorf.eform.entity.deserializer.DefaultJsonDateDeserializer;
import com.softtech.kismiss.property.Property;

@MappedSuperclass
public class SimpleApprovalObject implements Serializable{

	private static final long serialVersionUID = 3161356547544918057L;

	public static final String STATUS				= "status";
	public static final String APPROVAL_STATUS		= "approvalStatus";
	public static final String LATEST_SUGGESTION	= "latestSuggestion";
	public static final String LATEST_SUGGESTOR		= "latestSuggestor";
	public static final String LATEST_APPROVAL		= "latestApproval";
	public static final String LATEST_APPROVER		= "latestApprover";
	public static final String VERSION				= "version";
	public static final String ACTION_TYPE			= "actionType";
	
	@Column(name="STATUS", length=1, nullable=false, columnDefinition="int default 0")
	@JsonProperty("status")
	@Expose
	protected Integer status = 0;
	
	@Transient
	protected String statusDescription;
	
	@Column(name="APPROVAL_STATUS", length=1, nullable=true)
	@Expose
	protected Integer approvalStatus;
	
	@Transient
	protected String approvalStatusDescription;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LATEST_SUGGESTION", nullable=true)
	@Expose
	protected Date latestSuggestion;
	
	@Column(name="LATEST_SUGGESTOR", nullable=true)
	@Expose
	protected String latestSuggestor;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LATEST_APPROVAL", nullable=true)
	@Expose
	@JsonDeserialize(using=DefaultJsonDateDeserializer.class)
	protected Date latestApproval;
	
	@Column(name="LATEST_APPROVER", nullable=true)
	@Expose
	protected String latestApprover;
	
	@Column(nullable=true)
	@JsonProperty("version")
	@Expose
	protected int version;
	
	@Column(name="ACTION_TYPE", nullable=true)
	@Enumerated(EnumType.ORDINAL)
	@Expose
	protected ActionType actionType;
	
	public SimpleApprovalObject() {
		super();
	}
	public SimpleApprovalObject(Integer status, Integer approvalStatus, Date latestSuggestion,
			String latestSuggestor, Date latestApproval, String latestApprover) {
		super();
		this.status = status;
		this.approvalStatus = approvalStatus;
		this.latestSuggestion = latestSuggestion;
		this.latestSuggestor = latestSuggestor;
		this.latestApproval = latestApproval;
		this.latestApprover = latestApprover;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		if(null==status){
			status = SystemConstant.MasterDataStatus.INACTIVE;
		}
		this.status = status;
	}
	public Integer getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(Integer approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public Date getLatestSuggestion() {
		return latestSuggestion;
	}
	public void setLatestSuggestion(Date latestSuggestion) {
		this.latestSuggestion = latestSuggestion;
	}
	public String getLatestSuggestor() {
		return latestSuggestor;
	}
	public void setLatestSuggestor(String latestSuggestor) {
		this.latestSuggestor = latestSuggestor;
	}
	@Property(name="Last Modified", position = 9 , width = 8, pattern = "dd-MM-yyyy")
	public Date getLatestApproval() {
		return latestApproval;
	}
	public void setLatestApproval(Date latestApproval) {
		this.latestApproval = latestApproval;
	}
	public String getLatestApprover() {
		return latestApprover;
	}
	public void setLatestApprover(String latestApprover) {
		this.latestApprover = latestApprover;
	}
	
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public ActionType getActionType() {
		return actionType;
	}
	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}
	@Transient
	public String getStatusDescription() {
		if (getStatus()==null) {
			return MasterDataStatus.NEW_STR;
		}
		else {
			switch (getStatus()) {
			case MasterDataStatus.NEW:
			case MasterDataStatus.ACTIVE:
			case MasterDataStatus.INACTIVE:
				return MasterDataStatus.MasterDataStatusStr[getStatus()];
			default:
				return "Undefined";
			}
		}
	}
	
	@Transient
	public String getApprovalStatusDescription() {
		if (getApprovalStatus() != null) {
			switch (getApprovalStatus()) {
			case MasterDataApprovalStatus.PENDING:
				return MasterDataApprovalStatus.MasterDataApprovalStatusStr[getApprovalStatus()];
			case MasterDataApprovalStatus.APPROVED:
				return MasterDataApprovalStatus.MasterDataApprovalStatusStr[getApprovalStatus()];
			default:
				return "";
			}
		}
		else {
			return "";
		}
	}
}
