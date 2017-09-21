package com.metamorf.eform.entity.settings;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

//TODO WILLY harus diorder, lihat fsd 2 hal 7 dan 7b
@Entity(name="APP_FUNCTION")
public class AppFunction implements Serializable{
	
	private static final long serialVersionUID = 3763772113597670926L;

	@Id
	@Column(name="ID")
	private Long id;
	
	@Column(name="PARENT_ID")
	private Long parentId;
	
	@Column(name="NAME", length=255)
	private String name;
	
	@Column(name="DESCR", length=255)
	private String descr;
	
	@Column(name="NAMESPACE", length=255)
	private String namespace;
	
	@Column(name="ACCESS_PAGE", length=255)
	private String accessPage;
	
	@Column(name="IS_ACTIVE")
	private Boolean isActive;
	
	@Column(name="IS_VIEWABLE")
	private Boolean isViewable;
	
	@Column(name="USER_TYPE")
	private Integer userType;
	
	@Column(name="ORDER_NO")
	private Long orderNo;
	
	@Column(name="MENU_ORDER_NO")
	private Long menuOrderNo;
	
	@OneToMany(mappedBy="appFunction",fetch=FetchType.LAZY)
	private List<AppRoleFunction> appRoleFunctions;
	
	@Transient
	private List<AppFunction> appFunctions;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	public String getAccessPage() {
		return accessPage;
	}
	public void setAccessPage(String accessPage) {
		this.accessPage = accessPage;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Boolean getIsViewable() {
		return isViewable;
	}
	public void setIsViewable(Boolean isViewable) {
		this.isViewable = isViewable;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public Long getMenuOrderNo() {
		return menuOrderNo;
	}
	public void setMenuOrderNo(Long menuOrderNo) {
		this.menuOrderNo = menuOrderNo;
	}

    public List<AppRoleFunction> getAppRoleFunctions() {
		return appRoleFunctions;
	}

	public void setAppRoleFunctions(List<AppRoleFunction> appRoleFunctions) {
		this.appRoleFunctions = appRoleFunctions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppFunction other = (AppFunction) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public AppFunction() {
		super();
	}

	public AppFunction(Long id) {
		super();
		this.id = id;
	}

	public List<AppFunction> getAppFunctions() {
		return appFunctions;
	}
	public void setAppFunctions(List<AppFunction> appFunctions) {
		this.appFunctions = appFunctions;
	}




	public static final String PARENT_ID	= "parentId";
	public static final String USER_TYPE	= "userType";
	public static final String ID			= "id";
	public static final String ORDER_NO		= "orderNo";
	public static final String MENU_ORDER_NO = "menuOrderNo";
	public static final String IS_ACTIVE	= "isActive";
	
	public String toString() {
		return this.getId() + ":" + this.getName() + ":" + this.getDescr() + ":" + this.getParentId() + ":" + this.getAccessPage(); 
	}
}
