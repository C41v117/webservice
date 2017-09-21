package com.metamorf.eform.common.data.util;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SearchOrder implements Serializable{

	private static final long serialVersionUID = 239182083377090421L;

	public static String ASC = "asc";	//ascending
	public static String DESC = "desc";	//descending
	
	public enum Sort { ASC , DESC}
	public static final Map<String,Sort> SORD;
	static{
		Map<String,Sort> map = new HashMap<String,Sort>();
		map.put(ASC, Sort.ASC);
		map.put(DESC, Sort.DESC);
		SORD = Collections.unmodifiableMap(map);
	}
	
	private String fieldName;
	private Sort sort;
	

	public SearchOrder(String fieldName, Sort sort) {
		super();
		this.fieldName = fieldName;
		this.sort = sort;
	}

	public SearchOrder() {
		super();
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}
	
	

}
