package com.metamorf.eform.common.file;

import com.metamorf.eform.common.exception.SystemException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * 
 * @author anicka andry
 *
 * @param <T>
 */
public interface UploadReader<T>  {
	
	public List<T> read(InputStream inputStream) throws SystemException, IOException ;

}
