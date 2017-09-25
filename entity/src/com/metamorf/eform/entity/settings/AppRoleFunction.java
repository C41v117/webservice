package com.metamorf.eform.entity.settings;

import java.io.Serializable;

import com.metamorf.eform.entity.user.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.ForeignKey;

//@Entity(name="APP_ROLE_FUNCTION")
public class AppRoleFunction implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -9079062912417233346L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="APP_FUNCTION_ID", nullable=false)
	@ForeignKey(name="FK_ARF_APP_FUNCTION_APP_FUNCTION")
	private AppFunction appFunction;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ROLE_ID", nullable = true)
	@ForeignKey(name="FK_ARF_APP_ROLE_APP_ROLE")
	private Role role;

	// Constructors

	/** default constructor */
	public AppRoleFunction() {
	}

	/** full constructor */
	public AppRoleFunction(AppFunction appFunction, Role appRole) {
		this.appFunction = appFunction;
		this.role = appRole;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AppFunction getAppFunction() {
		return this.appFunction;
	}

	public void setAppFunction(AppFunction appFunction) {
		this.appFunction = appFunction;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role appRole) {
		this.role = appRole;
	}
	
	public boolean equals(Object obj) {
        if (obj instanceof AppRoleFunction) {
            return EqualsBuilder.reflectionEquals(obj, this);
        }
        return false;
    }
	
	 public int hashCode() {
	        return HashCodeBuilder.reflectionHashCode(this);
	 }
	 
	 public String toString(){
			return ToStringBuilder.reflectionToString(this);
	}
}
