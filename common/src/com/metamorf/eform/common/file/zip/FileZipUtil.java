/**
 * 
 */
package com.metamorf.eform.common.file.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Hendri Yauw
 *
 */
public class FileZipUtil {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(FileZipUtil.class);
	
	private String sourceFileLocation;
	private String generateFileLocation;
	
	public FileZipUtil(String sourceFileLocation, String generateFileLocation) {
		this.sourceFileLocation = sourceFileLocation;
		this.generateFileLocation = generateFileLocation;
	}
	
	/**
	 * @return the sourceFileLocation
	 */
	public String getSourceFileLocation() {
		return sourceFileLocation;
	}
	/**
	 * @param sourceFileLocation the sourceFileLocation to set
	 */
	public void setSourceFileLocation(String sourceFileLocation) {
		this.sourceFileLocation = sourceFileLocation;
	}
	/**
	 * @return the generateFileLocation
	 */
	public String getGenerateFileLocation() {
		return generateFileLocation;
	}
	/**
	 * @param generateFileLocation the generateFileLocation to set
	 */
	public void setGenerateFileLocation(String generateFileLocation) {
		this.generateFileLocation = generateFileLocation;
	}
	
	public void zipFile() throws Exception {
		zipFile(sourceFileLocation, generateFileLocation);
	}
	
	public void zipFile(String sourceFileLocation, String generateFileLocation) throws Exception {
		LOGGER.debug("[START] FileZipUtil sourceFileLocation [{}] and generateFileLocation [{}]",URLEncoder.encode(StringUtils.trimToEmpty(sourceFileLocation), "UTF-8"), URLEncoder.encode(StringUtils.trimToEmpty(generateFileLocation), "UTF-8"));
		FileOutputStream fos = null;
		ZipOutputStream out = null;
//		BufferedInputStream in = null;
		File file = null;
		FileInputStream fis = null;
		try {
			file = new File(sourceFileLocation);

			if (file.exists()) {
				/*
				out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(generateFileLocation)));
				byte[] data = new byte[1000];               
			    in = new BufferedInputStream(new FileInputStream(sourceFileLocation));
			    int count;
//			    out.putNextEntry(new ZipEntry(generateFileLocation));
			    out.putNextEntry(new ZipEntry("SRIBBKIDJA.zip"));
			    
			    while((count = in.read(data,0,1000)) != -1) {      
			    	out.write(data, 0, count);
			    }
				*/
				/*
				byte[] buf = new byte[1024];
	            fis = new FileInputStream(sourceFileLocation);
	            fis.read(buf,0,buf.length);
	            
	            CRC32 crc = new CRC32();
	            out = new ZipOutputStream(
	                    (OutputStream)new FileOutputStream(generateFileLocation));
	            
	            out.setLevel(6);
	            
	            ZipEntry entry = new ZipEntry(sourceFileLocation);
	            entry.setSize((long)buf.length);
	            crc.reset();
	            crc.update(buf);
	            entry.setCrc( crc.getValue());
	            out.putNextEntry(entry);
	            out.write(buf, 0, buf.length);*/
				
				byte[] buffer = new byte[18024];
				fos = new FileOutputStream(generateFileLocation);
				if(fos!=null){
					out = new ZipOutputStream(fos);
					out.setLevel(Deflater.DEFAULT_COMPRESSION);
					fis = new FileInputStream(sourceFileLocation); 
					out.putNextEntry(new ZipEntry(file.getName()));
					int len;
					while ((len = fis.read(buffer)) > 0){
						out.write(buffer, 0, len);
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		finally {
//			if (in != null)
//				in.close();
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(fos);
			IOUtils.closeQuietly(fis);
			/*try{
				if (out != null) {
					out.closeEntry();
					out.finish();
				    out.flush();
				    out.close();
				}
			}finally{
				try{
					if(fos != null) {
						fos.flush();
						fos.close();
					}
				}finally{
					
				}
			}
			try{
				if (fis != null){
					fis.close();
				}
			}finally{
				
			}*/
			File fileZip = new File(generateFileLocation);
			if (fileZip!=null&&fileZip.exists()){
				file.delete();
			}
		}
		LOGGER.debug("[END] FileZipUtil sourceFileLocation [{}] and generateFileLocation [{}]",URLEncoder.encode(StringUtils.trimToEmpty(sourceFileLocation), "UTF-8"), URLEncoder.encode(StringUtils.trimToEmpty(generateFileLocation), "UTF-8"));
	}
	
	/**
	 * Zip a directory and files inside
	 * @param inputFolderPath - Input folder path
	 * @param outputFilePath - Output zip file path
	 * @throws Exception
	 */
	public static void zipDirectory(String inputFolderPath, String outputFilePath) throws Exception {
		FileOutputStream fout = null;
		ZipOutputStream zout = null;

		try{
			fout = new FileOutputStream(outputFilePath);
			zout = new ZipOutputStream(fout);
			File folderSource = new File(inputFolderPath);
			
			addFolderFilesToZipOutStream(zout, folderSource);
		}finally{
			try{
				if(zout!=null){
					zout.close();
				}
			}finally{
				if(fout!=null){
					fout.close();
				}
			}
		}
	}
	
	private static void addFolderFilesToZipOutStream(ZipOutputStream zout, File folderSource) throws Exception {
		zout.putNextEntry(new ZipEntry(folderSource.getName() + "/"));
		
		File[] files = folderSource.listFiles();
		
		for (int i=0; i<files.length; ++i) {

			if (files[i].isDirectory()) {
				addFolderFilesToZipOutStream(zout, files[i]);
			}
			else {
				byte[] buffer = new byte[1024];
				FileInputStream fin = new FileInputStream(files[i]);
				try{
					zout.putNextEntry(new ZipEntry(folderSource.getName() + "/" + files[i].getName()));
	
					int length;
					while ((length = fin.read(buffer)) > 0) {
						zout.write(buffer, 0, length);
					}
				}finally{
					try{
						zout.closeEntry();
					}finally{
						fin.close();
					}
				}
			}
		}
	}
}
