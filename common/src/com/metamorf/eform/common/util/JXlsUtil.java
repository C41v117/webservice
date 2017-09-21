package com.metamorf.eform.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.ReaderConfig;
import net.sf.jxls.reader.XLSReadStatus;
import net.sf.jxls.reader.XLSReader;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.metamorf.eform.common.config.BeanUtilsConfigurer;
import com.metamorf.eform.common.core.SystemParameter;
import com.metamorf.eform.common.exception.SystemException;

public  class JXlsUtil<T> {
	private static final Logger logger = LoggerFactory.getLogger(JXlsUtil.class);
	
	public List<T> read(InputStream xlsDataInputStream,InputStream xlsXmlTemplateStream,String mapName,boolean skipError) throws IOException , SAXException, InvalidFormatException {
		InputStream inputXML = new BufferedInputStream(xlsXmlTemplateStream);
		
        XLSReader mainReader = ReaderBuilder.buildFromXML( inputXML );
        
        InputStream inputXLS = new BufferedInputStream(xlsDataInputStream);
        List<T> list = new LinkedList<T>();
        Map<String, List<T>> beans = new HashMap<String,  List<T>>();
        beans.put(mapName,list);
        ReaderConfig.getInstance().setSkipErrors( skipError );
        XLSReadStatus readStatus = mainReader.read( inputXLS, beans);
        readStatus.getReadMessages();
        // reset beanutils' converter setting back to default
        BeanUtilsConfigurer.configure();
		return list ;
	}

	public byte[] write(String templateFileName, List<?> listData) throws IOException, RuntimeException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		InputStream inputStream = null;
		try {
			Map beans = new HashMap();
			
			beans.put("detail", listData);
			
			if (listData == null || listData.size() == 0)
				beans.put("note", "No Data Found");
			
			String directory = SystemParameter.TEMPLATE_DIRECTORY;
			if (!(directory.endsWith("/") || directory.endsWith("\\"))) {
				directory += "/";
			}
//			InputStream inputStream = getClass().getResourceAsStream(directory + templateFileName);
			inputStream = getClass().getResourceAsStream(templateFileName);
			
			XLSTransformer transformer = new XLSTransformer();
			
			Workbook wb = null;
			wb = transformer.transformXLS(inputStream, beans);
			
			wb.write(bos);
			bos.close();
		} catch (Exception e) {
			logger.error("Error saat ekspor excel", e);
			throw new SystemException("Error saat ekspor excel");
		} finally{
			if(inputStream!=null){
				inputStream.close();
			}
		}
		// reset beanutils' converter setting back to default
        BeanUtilsConfigurer.configure();
		return bos.toByteArray();
	}
	
	public void write(String template ,String outputdir,Map beans) throws IOException, RuntimeException {
		FileOutputStream fos = new FileOutputStream(outputdir);
		OutputStream bos = new BufferedOutputStream(fos);
		InputStream is = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(template);
			is = new BufferedInputStream(fis);
			XLSTransformer transformer = new XLSTransformer();
			Workbook wb = transformer.transformXLS(is, beans);
			wb.write(bos);
		} catch (Exception e) {
			logger.error("Error saat ekspor excel", e);
			throw new SystemException("Error saat ekspor excel");
		}finally {
			try{
				if(is!=null){
					is.close();
				}
			}finally{
				try{
					if(fis!=null){
						fis.close();
					}
				}finally{
					try{
						if(bos!=null){
							bos.flush();
							bos.close();
						}
					}finally{
						if(fos!=null){
							fos.flush();
							fos.close();
						}
					}
				}
			}
			
		}
		// reset beanutils' converter setting back to default
        BeanUtilsConfigurer.configure();
	}
	
	public void write(InputStream xlsDataInputStream,OutputStream outputStream ,List<T> listData) throws IOException, ParsePropertyException, InvalidFormatException {
		XLSTransformer transformer = new XLSTransformer();
		Map beans = new HashMap();
		beans.put("list", listData);
		Workbook wb = transformer.transformXLS(xlsDataInputStream, beans);
		wb.write(outputStream);
		outputStream.flush();
		outputStream.close();
		BeanUtilsConfigurer.configure();
	}
	
	public void write(String template,OutputStream outputStream ,List<T> listData) throws IOException, ParsePropertyException, InvalidFormatException {
		XLSTransformer transformer = new XLSTransformer();
		Map beans = new HashMap();
		beans.put("list", listData);
		InputStream inputStream = getClass().getResourceAsStream(template);
		try{
			Workbook wb = transformer.transformXLS(inputStream, beans);
			wb.write(outputStream);
		}finally{
			try{
				if(inputStream!=null){
					inputStream.close();
				}
			}finally{
				if(outputStream!=null){
					outputStream.flush();
					outputStream.close();
				}
			}
		}
		BeanUtilsConfigurer.configure();
	}
}
