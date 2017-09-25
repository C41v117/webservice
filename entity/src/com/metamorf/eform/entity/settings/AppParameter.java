package com.metamorf.eform.entity.settings;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.metamorf.eform.common.core.SystemConstant;
import com.metamorf.eform.entity.core.SimpleApprovalObject;

//@Entity(name="APP_PARAMETER")
@DynamicUpdate(value=true)
public class AppParameter extends SimpleApprovalObject implements Serializable{

	private static final long serialVersionUID = -831683316737564543L;

	@Id
	@Column(name="ID")
	@Expose
	private Long id;
	
	@Column(name="NAME", length=100, nullable=false, updatable=false)
	@JsonProperty("name")
	@Expose
	private String name;
	
	@Column(name="VALUE", length=500, nullable=false)
	@JsonProperty("value")
	@Expose
	private String value;
	
	@Column(name="DATA_TYPE", nullable=false, columnDefinition="int default 0", updatable=false)
	@Expose
	private Integer dataType;
	
	
	
	@Column(name="DESCRIPTION", nullable=true)
	@JsonProperty("description")
	@Expose
	private String description;
	
	@Column(name="IS_VIEWABLE", nullable=false, columnDefinition="integer default 1", updatable=false)
	private Boolean viewable;
	
	@Column(name="FOR_SECURITY", nullable=false, columnDefinition="integer default 0", updatable=false)
	private Boolean forSecurity;
	
	@Column(name="IS_TEXTAREA", nullable=false, columnDefinition="integer default 0", updatable=false)
	private Boolean textArea;
	
	public AppParameter(Long id, String name, String value, Integer dataType,
			String description, Boolean viewable, Boolean forSecurity,
			Boolean textArea, Integer status, Integer approvalStatus, Date latestSuggestion,
			String latestSuggestor, Date latestApproval, String latestApprover) {
		super(status, approvalStatus, latestSuggestion, latestSuggestor, latestApproval, latestApprover);
		this.id = id;
		this.name = name;
		this.value = value;
		this.dataType = dataType;
		this.description = description;
		this.viewable = viewable;
		this.forSecurity = forSecurity;
		this.textArea = textArea;
	}

	public AppParameter() {
		super();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getDataType() {
		return dataType;
	}
	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getViewable() {
		return viewable;
	}

	public void setViewable(Boolean viewable) {
		this.viewable = viewable;
	}

	public Boolean getForSecurity() {
		return forSecurity;
	}

	public void setForSecurity(Boolean forSecurity) {
		this.forSecurity = forSecurity;
	}

	public Boolean getTextArea() {
		return textArea;
	}

	public void setTextArea(Boolean textArea) {
		this.textArea = textArea;
	}
	
	public static final String INTEGER = "INTEGER";
	public static final String LONG    = "LONG";
	public static final String DOUBLE  = "DOUBLE";
	public static final String STRING  = "STRING";
	public static final String DATE    = "DATE";
	public static final String BOOLEAN = "BOOLEAN";
	public static final String TIME	= "TIME";
	public static final String DAY		= "DAY";
	
	@Transient
	@JsonIgnore
	public String getDataTypeDesc() {
        switch (dataType.intValue()) {
            case SystemConstant.FIELD_TYPE_INT :
                return INTEGER;
            case SystemConstant.FIELD_TYPE_LONG :
                return LONG;
            case SystemConstant.FIELD_TYPE_DOUBLE :
                return DOUBLE;
            case SystemConstant.FIELD_TYPE_STRING :
                return STRING;
            case SystemConstant.FIELD_TYPE_DATE :
                return DATE;
            case SystemConstant.FIELD_TYPE_BOOLEAN :
                return BOOLEAN;
            case SystemConstant.FIELD_TYPE_TIME :
            	return TIME;
            case SystemConstant.FIELD_TYPE_DAY :
            	return DAY;
        }
        return null;
    }
	
	public static final String ID				= "id";
	public static final String NAME				= "name";
	public static final String VALUE			= "value";
	public static final String DESCR			= "description";
	public static final String DATATYPE			= "dataType";
	public static final String IS_FOR_SECURITY	= "forSecurity";
	public static final String IS_VIEWABLE		= "viewable";
	public static final String IS_TEXT_AREA		= "textArea";
	public static final String LOB				= "lob";
	
	public static final String[] MAINTENANCE_LIST_FIELDS = {
		ID, NAME, VALUE, DESCR, DATATYPE, IS_FOR_SECURITY, IS_VIEWABLE, IS_TEXT_AREA, STATUS, APPROVAL_STATUS, LATEST_SUGGESTION, LATEST_SUGGESTOR, 
		LATEST_APPROVAL, LATEST_APPROVER, VERSION, ACTION_TYPE, LOB
	} ;
	
	//Penambahan field pembeda SDB & Sinaya
	@Column(name="lob", nullable=false, columnDefinition="int default 0")
	@Expose
	private Integer lob;

	public Integer getLob() {
		return lob;
	}
	public void setLob(Integer lob) {
		this.lob = lob;
	}
	
	@Transient
	public String getLobDescr(){
		return SystemConstant.ProjectType.userLobList.get(getLob());
	}
}
