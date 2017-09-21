package com.metamorf.eform.common.data.util;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


public class SearchFilter implements Serializable{
	
	private static final long serialVersionUID = -7766446991177431829L;
	
	private SearchFilter leftFilter;
	private SearchFilter rightFilter;
	private boolean or = false;
	private Operator 	operand ;
	private String 		fieldName;
	private Object 		value;
	/*only work with Operand EQUALS, EQUALS_OR_GREATER_THAN, EQUALS_OR_LESS_THAN, GREATER_THAN, AND, LESS_THAN, NOT_EQUAL*/
	private boolean ignoreCase = true;
	public boolean isOr() {
		return or;
	}
	private boolean and = false;
	public boolean isAnd() {
		return and;
	}

	public boolean isIgnoreCase() {
		return ignoreCase;
	}

	public SearchFilter(SearchFilter leftFilter, SearchFilter rightFilter) {
		this.leftFilter = leftFilter;
		this.rightFilter = rightFilter;
		or = true ;
	}
	
	public SearchFilter(SearchFilter leftFilter, SearchFilter rightFilter, boolean and) {
		this.leftFilter = leftFilter;
		this.rightFilter = rightFilter;
		this.and = and ;
	}
	
	public SearchFilter(String fieldName, Operator operator, Object value, boolean ignoreCase) {
		this.fieldName = fieldName;
		this.operand = operator;
		this.value = value;
		this.ignoreCase = ignoreCase;
	}

	
	public SearchFilter getLeftFilter() {
		return leftFilter;
	}
	public SearchFilter getRightFilter() {
		return rightFilter;
	}
	

	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}

	public SearchFilter(String fieldName, Operator operator, Object value) {
		this.fieldName = fieldName;
		this.operand = operator;
		this.value = value;
	}

	public Operator getOperand() {
		return operand;
	}
	public void setOperand(Operator operand) {
		this.operand = operand;
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	@Override
	public int hashCode() {
		 return HashCodeBuilder.reflectionHashCode(this);
	}
	
	
	@Override
	public String toString() {
		return  " field name : " + getFieldName() + ", operand : " + getOperand() + " , value : " + getValue();
	}


	

	
	
}
