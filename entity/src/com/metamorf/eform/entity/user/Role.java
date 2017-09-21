package com.metamorf.eform.entity.user;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.metamorf.eform.common.core.SystemConstant;
import com.metamorf.eform.entity.core.SimpleApprovalObject;
import com.metamorf.eform.entity.settings.UserGroupAccessNode;

@Entity(name="APP_ROLE")
public class Role extends SimpleApprovalObject {

	private static final long serialVersionUID = -4789059886967810330L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	@Expose
	private Long id;
	
	@Column(name="CODE", length=100)
	@Expose
	private String code;
	
	@Column(name="NAME", length=255)
	@Expose
	private String name;
	
	@Column(name="TYPE", length=1, nullable=false, columnDefinition="int default 1")
	@Expose
	private Integer type;
	
	@Column(name="IS_SUPER")
	@Expose
	private Integer isSuper=0;
	
	@Column(name="LOB")
	@Expose
	private Integer lob;
	
	@Transient
	@Expose
	private Long[] accessibilities;
	
	@Transient
	@Expose
	private List<UserGroupAccessNode> accessNodes;
	
	@Transient
	@Expose
	private String groupName;
	
	public static final String ID				= "id";
	public static final String CODE				= "code";
	public static final String NAME				= "name";
	public static final String TYPE				= "type";
	public static final String IS_SUPER			= "isSuper";
	public static final String LOB				= "lob";
	
	public static final String[] MAINTENANCE_LIST_FIELDS = {
		ID, CODE, NAME, STATUS, LOB, APPROVAL_STATUS, LATEST_SUGGESTION, LATEST_SUGGESTOR, LATEST_APPROVAL, LATEST_APPROVER
	};
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long[] getAccessibilities() {
		return accessibilities;
	}
	public void setAccessibilities(Long[] accessibilities) {
		this.accessibilities = accessibilities;
	}
	public List<UserGroupAccessNode> getAccessNodes() {
		return accessNodes;
	}
	public void setAccessNodes(List<UserGroupAccessNode> accessNodes) {
		this.accessNodes = accessNodes;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getIsSuper() {
		return isSuper;
	}
	public void setIsSuper(Integer isSuper) {
		this.isSuper = isSuper;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
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