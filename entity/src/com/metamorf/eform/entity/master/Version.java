package com.metamorf.eform.entity.master;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.metamorf.eform.common.enumer.ModuleType;

//@Entity(name="VERSION")
public class Version {
	
	public static final String[] MAINTENANCE_LIST_FIELDS = {"id", "moduleType", "version"};
	
	public static final String MODULE_TYPE = "moduleType";
	
	public static final String VERSION = "version";
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonProperty("id")
	@Expose
	private Long id;
	
	@Column(name="MODULE_TYPE", nullable=false)
	@Enumerated(EnumType.ORDINAL)
	@JsonProperty("moduleType")
	@Expose
	private ModuleType moduleType;
	
	@Column(name="VERSION", nullable=false)
	@JsonProperty("version")
	@Expose
	private int version;
	
	@Column(name="VERSION_DATE", nullable=false)
	private Date versionDate;
	
	public Version() {
		super();
	}

	public Version(ModuleType moduleType, int version) {
		super();
		this.moduleType = moduleType;
		this.version = version;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ModuleType getModuleType() {
		return moduleType;
	}

	public void setModuleType(ModuleType moduleType) {
		this.moduleType = moduleType;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Date getVersionDate() {
		return versionDate;
	}

	public void setVersionDate(Date versionDate) {
		this.versionDate = versionDate;
	}
	
}
