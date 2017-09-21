package com.metamorf.eform.data.access.core;

import org.hibernate.sql.JoinType;

public class SearchAlias {

	private JoinType aliasJoinType;
	private String aliasName;
	private String aliasProperty;

	public SearchAlias(String aliasProperty, String aliasName) {
		this.aliasProperty = aliasProperty;
		this.aliasName = aliasName;
	}
	
	public SearchAlias(String aliasProperty, String aliasName, JoinType aliasJoinType) {
		this.aliasProperty = aliasProperty;
		this.aliasName = aliasName;
		this.aliasJoinType = aliasJoinType;
	}

	public JoinType getAliasJoinType() {
		return aliasJoinType;
	}

	public String getAliasName() {
		return aliasName;
	}

	public String getAliasProperty() {
		return aliasProperty;
	}

	public void setAliasJoinType(JoinType aliasFetchMode) {
		this.aliasJoinType = aliasFetchMode;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public void setAliasProperty(String aliasProperty) {
		this.aliasProperty = aliasProperty;
	}

}
