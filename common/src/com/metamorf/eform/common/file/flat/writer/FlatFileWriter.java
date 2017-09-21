package com.metamorf.eform.common.file.flat.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.blackbear.flatworm.FileCreator;

/**
 * 
 * @author anicka andry
 * for example see <li>http://javaconfessions.com/2009/04/writing-flat-files-in-java-with.html</li>
 */
public  class FlatFileWriter<T> implements InitializingBean{
	
	private final static Logger LOGGER = LoggerFactory.getLogger(FlatFileWriter.class);
	
	protected String headerBeanName;
	protected String beanName;
	protected String footerBeanName;
	
	protected String headerRecordName;
	protected String recordName;
	protected String footerRecordName;
	
	protected String recordSeperator;
	protected String configurationFile;
	
	
	/**
	 * @param headerBeanName the headerBeanName to set
	 */
	public void setHeaderBeanName(String headerBeanName) {
		this.headerBeanName = headerBeanName;
	}


	/**
	 * @param footerBeanName the footerBeanName to set
	 */
	public void setFooterBeanName(String footerBeanName) {
		this.footerBeanName = footerBeanName;
	}


	/**
	 * @param headerRecordName the headerRecordName to set
	 */
	public void setHeaderRecordName(String headerRecordName) {
		this.headerRecordName = headerRecordName;
	}


	/**
	 * @param footerRecordName the footerRecordName to set
	 */
	public void setFooterRecordName(String footerRecordName) {
		this.footerRecordName = footerRecordName;
	}


	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	
	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}

	
	public void setRecordSeperator(String recordSeperator) {
		this.recordSeperator = recordSeperator;
	}

	
	public void setConfigurationFile(String configurationFile) {
		this.configurationFile = configurationFile;
	}

	public void write(List<Object> listHeader, List<T> records, List<Object> footers, String outputFile) throws Exception {
		LOGGER.debug(" FlatFileWriter for headerBean ["+headerBeanName+"] and headerRecord ["+headerRecordName+"], bean ["+beanName+"] and record ["+recordName+"],  footerBean ["+footerBeanName+"] and footerRecord ["+footerRecordName+"] started .. ");
		LOGGER.debug(" writing to [{}] total records [{}] ",outputFile, records.size());
		FileCreator fileCreator = null;
		try {
			fileCreator = new FileCreator(configurationFile, outputFile);
			fileCreator.open();
			fileCreator.setRecordSeperator(recordSeperator);
			
			for (Object fileRecord : listHeader) {
				fileCreator.setBean(headerBeanName, fileRecord);
				fileCreator.write(headerRecordName);
			}
			
			for (T fileRecord : records) {
				fileCreator.setBean(beanName, fileRecord);
				fileCreator.write(recordName);
			}
			
			for (Object fileRecord : footers) {
				fileCreator.setBean(footerBeanName, fileRecord);
				fileCreator.write(footerRecordName);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		finally {
			if(fileCreator!=null){
				fileCreator.close();
			}
		}
		LOGGER.debug(" FlatFileWriter for bean [{}] and record [{}] finished .. ",beanName, recordName);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if(beanName==null || recordName == null || configurationFile == null || recordSeperator==null){
			throw new IllegalArgumentException("beanName or recordName or configurationFile or recordSeperator is null in Application Context configuration file  ");
		}
		LOGGER.debug(" initializing [{}] with configuration file [{}]" , this.getClass().getName() , configurationFile);
		
	} 
	
}
