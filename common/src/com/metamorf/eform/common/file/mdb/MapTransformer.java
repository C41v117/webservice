package com.metamorf.eform.common.file.mdb;

import java.util.Map;

/**
 * 
 * @author anicka andry
 *
 */
public interface MapTransformer<T> {
	
	public T transform(Map<?, ?> mdbMap);

}
