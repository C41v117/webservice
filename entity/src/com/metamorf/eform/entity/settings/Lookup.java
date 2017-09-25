package com.metamorf.eform.entity.settings;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Formula;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.metamorf.eform.entity.core.SimpleApprovalObject;

//@Entity(name="LOOKUP")
public class Lookup extends SimpleApprovalObject implements /*Comparable<Lookup>, */Serializable{

	private static final long serialVersionUID = 1419309428498671519L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	@Expose
	@JsonProperty("id")
	@XmlTransient
	private Long id;
	
	@Transient
	@XmlTransient
	private LookupGroup lookupGroup;

	@Column(name = "CODE", length = 50)
	@JsonProperty("code")
	@Expose
	private String code;
	
	@Column(name = "DESCR", length = 100)
	@JsonProperty("description")
	@Expose
	private String description;
	
	@Column(name = "ORDER_NO", precision = 22, scale = 0)
	@Expose
	@XmlTransient
	private Long orderNo;
	
	@Column(name="LOOKUP_GROUP")
	@JsonProperty("lookupGroupString")
	@Expose
	@XmlTransient
	private String lookupGroupString;
	
	@Column(name="NAME")
	@JsonProperty("name")
	@Expose
	private String name;
	
	@Column(name="IS_ALTERNATE_ENTRY")
	@Expose
	private Boolean isAlternateEntry; 
	
	@Column(name="IS_HIGH_RISK")
	@Expose
	private Boolean isHighRisk; 
	
	@Transient
	@XmlTransient
	private String lookupGroupDetail;
	
	@Transient
	@XmlTransient
	private Long lookupParentId;
	
	@Formula(" ( select lg.has_element from lookup_group lg where lg.name = lookup_group ) ")
	@XmlTransient
	private Boolean hasElement;
	
	@Formula( " ( select lg.is_updatable from lookup_group lg where lg.name = lookup_group ) " )
	@XmlTransient
	private Boolean isEditable;
	
	@Formula( " ( select lg.child_lookup_group from lookup_group lg where lg.name = lookup_group ) " )
	@XmlTransient
	private String childLookupGroup;
	
	@Formula( " ( select lg.child_lookup_view from lookup_group lg where lg.name = lookup_group ) " )
	@XmlTransient
	private String childLookupView;	
	
	public static final String LOOKUP_GROUP_STRING ="lookupGroupString";
	public static final String ORDER_NO_STRING ="orderNo";
	public static final String CODE_STRING ="code";
	public static final String NAME_STRING ="name";
	public static final String ID="id";
	public static final String LOOKUP_PARENT_ID = "lookupParentId";
	public static final String DESCRIPTION = "description";
	public static final String IS_ALTERNATE_ENTRY = "isAlternateEntry";
	public static final String IS_HIGH_RISK = "isHighRisk";
	public static final String IS_EDIT_TABLE = "isEditable";
	
	/** default constructor */
	public Lookup(String code,String description) {
		this.code= code;
		this.description = description;
	}
	
	public Lookup() {
	}

	/** full constructor */
	public Lookup(LookupGroup lookupGroup, String code, String descr, Long orderNo) {
		this.lookupGroup = lookupGroup;
		this.code = code;
		this.description = descr;
		this.orderNo = orderNo;
	}
	
	@Transient
	private Long masterDataApprovalId;
	
	public Long getMasterDataApprovalId() {
		return masterDataApprovalId;
	}

	public void setMasterDataApprovalId(Long masterDataApprovalId) {
		this.masterDataApprovalId = masterDataApprovalId;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLookupParentId() {
		return lookupParentId;
	}
	
	public void setLookupParentId(Long lookupParentId) {
		this.lookupParentId = lookupParentId;
	}

	public LookupGroup getLookupGroup() {
		return this.lookupGroup;
	}

	public void setLookupGroup(LookupGroup lookupGroup) {
		this.lookupGroup = lookupGroup;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String descr) {
		this.description = descr;
	}

	public Long getOrderNo() {
		return this.orderNo;
	}

	/* (non-Javadoc)
	 * @see com.metamorf.eform.collsys.valueobject.hibernate.ILookup#setSettlementNo(java.lang.Long)
	 */
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	public String getLookupGroupString() {
		return lookupGroupString;
	}

	public void setLookupGroupString(String groupName) {
		this.lookupGroupString = groupName;
	}
	
	@Transient
	public String getCodeAndName(){
		return getCode() + "-" +getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Boolean getIsAlternateEntry() {
		return isAlternateEntry;
	}

	public void setIsAlternateEntry(Boolean isAlternateEntry) {
		this.isAlternateEntry = isAlternateEntry;
	}

	public Boolean getHasElement() {
		return hasElement;
	}

	public void setHasElement(Boolean hasElement) {
		this.hasElement = hasElement;
	}

	public Boolean getIsEditable() {
		return isEditable;
	}

	public void setIsEditable(Boolean isEditable) {
		this.isEditable = isEditable;
	}
	
	public String getChildLookupGroup() {
		return childLookupGroup;
	}

	public void setChildLookupGroup(String childLookupGroup) {
		this.childLookupGroup = childLookupGroup;
	}

	public String getChildLookupView() {
		return childLookupView;
	}

	public void setChildLookupView(String childLookupView) {
		this.childLookupView = childLookupView;
	}


/*	@Override
	public int compareTo(Lookup obj) {
		return this.description.compareTo(obj.getDescription());
	}*/

	public Boolean getIsHighRisk() {
		return isHighRisk;
	}

	public void setIsHighRisk(Boolean isHighRisk) {
		this.isHighRisk = isHighRisk;
	}

	public String getLookupGroupDetail() {
		/*if(lookupGroupString.equals(ILookupGroupConstant.SELLING_AGENT)) {
			lookupGroupDetail = ILookupGroupConstant.BRANCH_CODE;
		}
		else if(lookupGroupString.equals(ILookupGroupConstant.CLIENT_TYPE)) {
			lookupGroupDetail = ILookupGroupConstant.CLIENT_TYPE_TAX_NPWP;
		}
		else if(lookupGroupString.equals(ILookupGroupConstant.BUSINESS_TYPE)) {
			lookupGroupDetail = ILookupGroupConstant.BUSINESS_TYPE_TAX_YEAR;
		}
		else if(lookupGroupString.equals(ILookupGroupConstant.DGT)) {
			lookupGroupDetail = ILookupGroupConstant.DGT_TAX_COUNTRY;
		}
		else if(lookupGroupString.equals(ILookupGroupConstant.PROVINCE)) {
			lookupGroupDetail = ILookupGroupConstant.CITY;
		}
		else if(lookupGroupString.equals(ILookupGroupConstant.CITY)) {
			lookupGroupDetail = ILookupGroupConstant.CITY_DEPKEU;
		}
		else if(lookupGroupString.equals(ILookupGroupConstant.PROVINCE_DEPKEU)) {
			lookupGroupDetail = ILookupGroupConstant.CITY_DEPKEU;
		}*/
		return lookupGroupDetail;
	}
	
	public void setLookupGroupDetail(String lookupGroupDetail) {
		this.lookupGroupDetail = lookupGroupDetail;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof Lookup)) return false;
		if(((Lookup)obj).getId().longValue() == getId().longValue() &&
				((Lookup)obj).getLookupGroupString().equals(getLookupGroupString()) && 
				((Lookup)obj).getCode().equals(getCode()) &&
				((Lookup)obj).getName().equals(getName()) && 
				((Lookup)obj).getDescription().equals(getDescription()) &&
				((Lookup)obj).getIsAlternateEntry().equals(getIsAlternateEntry())) {
			return true;
		}
		
		return false;
	}

	@Override
	public int hashCode() {
		return (int) (
				(getId()!=null?getId().longValue():0) * 
				(getLookupGroupString()!=null?getLookupGroupString().length():0) + 
				(getCode()!=null?getCode().length():0) +
				(getName()!=null?getName().length():0) +
				(getDescription()!=null?getDescription().length():0)
		);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("{");
		sb.append("\"id\"").append(":\"" + id + "\"");
		sb.append(",");
		sb.append("\"code\"").append(":\"" + JSONObject.escape(code) + "\"");
		sb.append(",");
		sb.append("\"name\"").append(":\"" + JSONObject.escape(name) + "\"");
		sb.append(",");
		sb.append("\"description\"").append(":\"" + JSONObject.escape(description) + "\"");
		sb.append("}");

		return sb.toString();
	}

	public static final String[] MAINTENANCE_DETAIL_FIELDS = {
		ID, LOOKUP_GROUP_STRING, ORDER_NO_STRING, CODE_STRING, NAME_STRING, DESCRIPTION, IS_ALTERNATE_ENTRY, IS_HIGH_RISK, VERSION, STATUS, ACTION_TYPE,IS_EDIT_TABLE
	} ;

}
