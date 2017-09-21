package com.metamorf.eform.common.file.flat;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;

import com.blackbear.flatworm.ConfigurationReader;
import com.blackbear.flatworm.FileFormat;
import com.blackbear.flatworm.MatchedRecord;
import com.blackbear.flatworm.errors.FlatwormConfigurationValueException;
import com.blackbear.flatworm.errors.FlatwormConversionException;
import com.blackbear.flatworm.errors.FlatwormCreatorException;
import com.blackbear.flatworm.errors.FlatwormInputLineLengthException;
import com.blackbear.flatworm.errors.FlatwormInvalidRecordException;
import com.blackbear.flatworm.errors.FlatwormUnsetFieldValueException;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.file.UploadReader;

public class UploadFileReader<T> implements  UploadReader<T> , InitializingBean {
	
	protected String recordName;
	protected String configurationFile;
	protected File flatFile;
	protected String beanName;
    
    
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	
	public void setConfigurationFile(String configurationFile) {
		this.configurationFile = configurationFile;
	}
	
    
	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<T> read(InputStream inputStream) throws SystemException, IOException {
		if(inputStream==null) 
			throw new IllegalArgumentException() ;
		
		ConfigurationReader parser = new ConfigurationReader();
		List<T> list = new LinkedList<T>();
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(inputStream));
        try {
            FileFormat ff = parser.loadConfigurationFile(configurationFile);
            //bufIn = new BufferedReader(new InputStreamReader(inputStream));
            MatchedRecord results;
            while ((results = ff.getNextRecord(bufIn)) != null) {
                if (results.getRecordName().equals(recordName)) {
                	list.add((T)results.getBean(beanName));
                }
            }

        }catch(FlatwormConfigurationValueException e){
        	e.printStackTrace();
        	throw new SystemException("FlatwormConfigurationValueException occur");
		}catch(FlatwormUnsetFieldValueException e){
			e.printStackTrace();
			throw new SystemException("FlatwormUnsetFieldValueException occur");
		} catch (FlatwormInvalidRecordException e) {
			e.printStackTrace();
			throw new SystemException("FlatwormInvalidRecordException occur");
		} catch (FlatwormInputLineLengthException e) {
			e.printStackTrace();
			throw new SystemException("FlatwormInputLineLengthException occur");
		} catch (FlatwormConversionException e) {
			e.printStackTrace();
			throw new SystemException("FlatwormConversionException occur");
		} catch (FlatwormCreatorException e) {
			e.printStackTrace();
			throw new SystemException("FlatwormCreatorException occur");
		}finally{
			if(bufIn!=null){
				bufIn.close();
			}
        }
        return list;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if(beanName==null || recordName == null || configurationFile == null){
			throw new IllegalArgumentException("beanName or recordName or configurationFile is null ");
		}
		
	}

	
	

}
