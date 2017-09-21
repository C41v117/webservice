package com.metamorf.eform.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.zip.CRC32;

import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.metamorf.eform.common.core.SystemParameter;

public class ChunkUtil {
	Logger logger = LoggerFactory.getLogger(ChunkUtil.class);
	
	public static final String PART_SPLIT_REGEX = ",";
	
	public boolean isChecksumValid(File file, long checksum) throws IOException {
		BufferedInputStream bis = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			bis = new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[4096];
			int len = 0;
			bos = new ByteArrayOutputStream();
			while ((len = bis.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}
		} catch (IOException e) {
			logger.error("Catching error during checking checksum", e);
			throw e;
		} finally {
			if (bis != null) {
				bis.close();
			}
		}
		
		return isChecksumValid(bos.toByteArray(), checksum);
	}
	public boolean isChecksumValid(byte[] data, long checksum) {
		CRC32 crcCheck = new CRC32();
		crcCheck.update(data);
		logger.debug("CRC result=" + crcCheck.getValue() + ", given value=" + checksum);
		return (crcCheck.getValue() == checksum);
	}
	
	public ChunkResult createChunk(File file, String chunkPrefix, int chunkSize) throws IOException {
		ChunkResult result = new ChunkResult(chunkSize, file.getName(), chunkPrefix);
		result.chunk(file);
		
		return result;
	}
	
	/**
	 * Combine encodedBase64 file chunk, then decode to one result file
	 * @param fileNames
	 * @param resultName
	 * @return
	 * @throws IOException
	 */
	public File combineChunks(List<String> fileNames, String resultName) throws IOException {
		
		
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		File result = null;
		try {
			result = new File(resultName);
			bos = new BufferedOutputStream(new FileOutputStream(result));
			for (String fileName: fileNames) {
				File inputFile = new File(fileName);
				if (!inputFile.exists()) {
					// delete the output file
					bos.close();
					result.delete();
					throw new IOException("File " + fileName + " doesn't exists");
				}
				bis = new BufferedInputStream(new FileInputStream(inputFile));
				
				logger.debug("Copying file " + fileName + " to result");
				
				
				byte[] buffer = new byte[4096];
				
				int len = 0;
				ByteArrayOutputStream bos2 = new ByteArrayOutputStream();
				// read 1 file chunk
				while ((len = bis.read(buffer)) != -1) {
//					byte[] decoded = Base6464.decodeBase64(buffer);
					bos2.write(buffer, 0, len);
//					bos.write(decoded, 0, len);
				}
				byte[] decoded = Base64.decodeBase64(bos2.toByteArray());
				bos.write(decoded);
				bis.close();
			}
		} catch (IOException e) {
			logger.error("Catching error during combining chunks",e);
			throw e;
		} finally {
			if (bos != null) {
				bos.close(); 
			}
			if (bis != null) {
				try {
					bis.close();
				} catch (Exception e) {
					logger.warn("Error during closing input stream",e);
				}
			}
		}
		
		return result;
	}
	
	public void deleteAllPart(String phoneNo, String filename, String[] parts) throws IOException{
		for (String part : parts) {
			File file = new File(generateDir(phoneNo), generateFilename(filename, part));
			if(file.exists()){
				file.delete();
			}
		}
	}
	
	public void deleteDirectory(String phoneNo) throws IOException {
		FileUtils.deleteDirectory(generateDir(phoneNo));
	}
	
	public static class ChunkResult {
		int numOfChunks;
		int chunkSize;
		List<String> fileNames;
		List<Long> crc;
		String sourceName;
		String chunkPrefix;

		public ChunkResult(int chunkSize, String sourceName, String chunkPrefix) {
			this.chunkSize = chunkSize;
			this.sourceName = sourceName;
			this.chunkPrefix = chunkPrefix;
			fileNames = new ArrayList<String>();
		}
		
		public int getChunkSize() {
			return chunkSize;
		}
		public void setChunkSize(int chunkSize) {
			this.chunkSize = chunkSize;
		}
		public String getChunkPrefix() {
			return chunkPrefix;
		}
		public void setChunkPrefix(String chunkPrefix) {
			this.chunkPrefix = chunkPrefix;
		}
		public String getSourceName() {
			return sourceName;
		}
		public void setSourceName(String sourceName) {
			this.sourceName = sourceName;
		}
		public int getNumOfChunks() {
			return numOfChunks;
		}
		public List<String> getFileNames() {
			return fileNames;
		}
		public List<Long> getCrc() {
			return crc;
		}

		public void chunk(File file) throws IOException {
			chunk(new FileInputStream(file));
		}
		
		public void chunk(InputStream is) throws IOException {
			BufferedInputStream bis = null;
			// 1. Encode using Base64 and copy to temp file
			File tempOut = File.createTempFile(chunkPrefix, "temp");
			BufferedOutputStream bos = null;
			
			byte[] buffer = new byte[4096];
			int len = 0;
			try {
				bis = new BufferedInputStream(is);
				bos = new BufferedOutputStream(new FileOutputStream(tempOut));
				while ((len = bis.read(buffer)) != -1) {
					bos.write(encodeBase64(buffer, len));
				}
			} finally {
				if (bis != null) {
					bis.close();
				}
				if (bos != null) {
					bos.close();
				}
			}
			
			long resultSize = tempOut.length();
			numOfChunks = (int) Math.ceil((double)resultSize / (double) this.chunkSize);
			
			
			// start create chunk
			int chunkIndex = 1;
			int byteRead = 0;
			int maxToRead = (buffer.length > chunkSize) ? chunkSize: buffer.length;
			int numToRead = maxToRead;
			ByteArrayOutputStream bosChunk = new ByteArrayOutputStream(chunkSize);
			bis = null;
			try {
				bis = new BufferedInputStream(new FileInputStream(tempOut));
				
				while ((len = bis.read(buffer, 0, numToRead)) != -1) {
					bosChunk.write(buffer, 0, len);
					byteRead += len;
					if (byteRead >= chunkSize) {
						chunkIndex = createChunkFile(bosChunk.toByteArray(), chunkIndex);
						numToRead = maxToRead;
						bosChunk.reset();
						byteRead = 0;
					} else if (byteRead + numToRead > chunkSize) {
						numToRead = chunkSize - byteRead;
					}
				}
				if (byteRead >= 0) {
					createChunkFile(bosChunk.toByteArray(), chunkIndex);
					numToRead = maxToRead;
					bosChunk.reset();
				}	
			} finally {
				if (bis != null) {
					bis.close();
				}
			}
		}

		private byte[] encodeBase64(byte[] data, int len) {
			byte[] buffer = data;
			if (data.length > len) {
				buffer = Arrays.copyOf(data, len);
			}
			return Base64.encodeBase64(buffer);
		}
		
		private byte[] decodeBase64(byte[] data, int len) {
			byte[] buffer = data;
			if (data.length > len) {
				buffer = Arrays.copyOf(data, len);
			}
			return Base64.decodeBase64(buffer);
		}
		
		private String createChunkName(int chunkIndex) {
			return chunkPrefix + chunkIndex + ".eform";
		}
		
		private int createChunkFile(byte[] byteArray, int chunkIndex) throws IOException {
			BufferedOutputStream bos = null;
			try {
				File outFile = new File(createChunkName(chunkIndex));
				while (outFile.exists()) {
					chunkIndex++;
					outFile = new File(createChunkName(chunkIndex));
				}
				
				bos = new BufferedOutputStream(new FileOutputStream(outFile));
				bos.write(byteArray);
				bos.close();
				fileNames.add(outFile.getName());
				
				// calculate CRC
				CRC32 crc32 = new CRC32();
				crc32.update(byteArray);
				crc.add(crc32.getValue());
			} finally {
				if (bos != null) {
					bos.close();
				}
			}
			return chunkIndex++;
		}
		
	}
	
	public void writeToFile(byte[] data, String phoneNo, String filename, String partNo) throws IOException{
		File dir = generateDir(phoneNo);
		dir.mkdirs();
		String generatedFilename = generateFilename(filename, partNo);
		File file = new File(dir, generatedFilename);
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
		bos.write(data);
		bos.close();
	}
	
	public List<File> listChunkFile(String phoneNo, String filename, String[] parts) throws FileNotFoundException {
		List<File> files = new ArrayList<File>();
		for (String part : parts) {
			File file = new File(generateDir(phoneNo), generateFilename(filename, part));
			if(file.exists()){
				files.add(file);
			} else {
				throw new FileNotFoundException(part);
			}
		}
		
		return files;
	}
	
	public List<String> generateListChunkName(String phoneNo, String filename, String[] parts) {
		List<String> chunkNames = new ArrayList<String>();
		for (String part : parts) {
			File file = new File(generateDir(phoneNo), generateFilename(filename, part));
			chunkNames.add(file.getPath());
		}
		
		return chunkNames;
	} 
	
	public String[] listMissingChunk(String phoneNo, String filename, String[] parts){
		List<String> list = new ArrayList<String>();
		for (String part : parts) {
			File inputFile = new File(generateDir(phoneNo), generateFilename(filename, part));
			if (!inputFile.exists()) {
				list.add(part);
			}
		}
		String[] array = new String[list.size()];
		array = list.toArray(array);
		return array;
	}
	
	public String generatePartSuccess(String partSuccess, String[] missingParts) throws Exception{
		String[] partSuccessArray = partSuccess.split(PART_SPLIT_REGEX);
		List<String> temp = Arrays.asList(partSuccessArray);
		List<String> listSuccess = new ArrayList<String>(temp);
		StringBuilder result = new StringBuilder();
		for (String missingPart : missingParts) {
			if(missingPart != null){
				listSuccess.remove(missingPart);
			}
		}
		// reconstruct part success
		for(String idx: listSuccess) {
			String text = idx.trim();
			if (text.length() > 0) {
				if (result.length() > 0) {
					result.append(",");
				}
				result.append(text);
			}
		}
		
		return result.toString();
	}
	
	public File generateDir(String phoneNumber){
		File file = new File(SystemParameter.FILE_CHUNK_TEMP_LOCATION, phoneNumber);
		return file;
	}
	
	public String generateFilename(String filename, String partNo) {
		return filename + "_" + partNo;
	}
	
	public String generateResultName(String filename) {
		return filename + SystemParameter.FILE_RESULT_EXTENSION;
	}

	
	public String generateResultPath(String dir, String phoneNumber, String filename) {
		File file = new File(dir, phoneNumber);
		file.mkdirs();
		file = new File(file, generateResultName(filename));
		
		return file.getPath();
	}
	
	
	public String generateResultPathWithTaskId(String dir, Long taskId, String filename) {
		File file = new File(dir, String.valueOf(taskId));
		file.mkdirs();
		file = new File(file, generateResultName(filename));
		
		return file.getPath();
	}
	
	public byte[] decodeBase64(byte[] data, int len) {
		byte[] buffer = data;
		if (data.length > len) {
			buffer = Arrays.copyOf(data, len);
		}
		return Base64.decodeBase64(buffer);
	}
	
	public static boolean isAllPartCompleted(int total, String partSuccess){
		String parts[] = partSuccess.split(ChunkUtil.PART_SPLIT_REGEX);
		if(parts.length >= total){
			return true;
		} else {
			return false;
		}
	}

	public boolean containsPartNo(String partSuccess, String partNo) {
		String[] partSuccessArray = partSuccess.split(PART_SPLIT_REGEX);
		List<String> temp = Arrays.asList(partSuccessArray);
		List<String> listSuccess = new ArrayList<String>(temp);
		
		return listSuccess.contains(partNo);
	}
	
	public String[] orderParts(String[] parts) {
		List<String> temp = Arrays.asList(parts);
		List<String> partList = new ArrayList<String>(temp);
		
		Collections.sort(partList, new Comparator<String>() {;
		
			@Override
			public int compare(String o1, String o2) {
				if (o1 == null || o1.isEmpty()) {
					return 0;
				}
				if (o2 == null || o2.isEmpty()) {
					return 0;
				}
				Integer angka1 = new Integer(o1);
				Integer angka2 = new Integer(o2);
				return angka1.compareTo(angka2);
			}
		});
		
		String[] result = new String[partList.size()];
		result = partList.toArray(result);
		
		return result;
	}
	
	public String orderParts(String partSuccess) {
		String[] partSuccessArray = partSuccess.split(PART_SPLIT_REGEX);
		String[] orderParts = orderParts(partSuccessArray);
		
		StringBuilder result = new StringBuilder();
		for(String idx: orderParts) {
			String text = idx.trim();
			if (text.length() > 0) {
				if (result.length() > 0) {
					result.append(",");
				}
				result.append(text);
			}
		}
		
		return result.toString();
	}
	
	public static void main(String[] args) {
		String text = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18";
		ChunkUtil chunkUtil = new ChunkUtil();
		String[] missingParts = new String[] {"1", "3", "12", "15", "6", "7", "8"};
		
		System.out.println(chunkUtil.orderParts("1,3,12,15,6,8,7"));
		String[] orderParts = chunkUtil.orderParts(missingParts);
		try {
			System.out.println(chunkUtil.generatePartSuccess(text, missingParts));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
}
