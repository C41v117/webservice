package com.metamorf.eform.common.file.mdb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.Table;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.file.UploadReader;

/**
 * 
 * @author anicka andry
 *  
 */
public  class UploadMDBReader<T> implements UploadReader<T> , InitializingBean {
	
	private String table;
	private MapTransformer<T> mapTransformer;

	public void setMapTransformer(MapTransformer<T> mapTransformer) {
		this.mapTransformer = mapTransformer;
	}

	public void setTable(String table) {
		this.table = table;
	}

	

	@Override
	public List<T> read(InputStream inputStream) throws SystemException, IOException {
		File mdbFile;
		mdbFile = File.createTempFile("uploadIPO", ".mdb");
		List<T> resultList = new LinkedList<T>();
		OutputStream out = new FileOutputStream(mdbFile);
		try {
			int len; byte buf[] = new byte[1024];
			while ((len = inputStream.read(buf)) > 0)
				out.write(buf, 0, len);
			Table resultTable = Database.open(mdbFile).getTable(table);
			for (Map<String, Object> row : resultTable) {
				resultList.add(mapTransformer.transform(row));
			}
		} finally {
			try{
				if(out!=null){
					out.close();
				}
			}finally{
				try{
					if(inputStream!=null){
						inputStream.close();
					}
				}finally{
					mdbFile.delete();
				}
			}
		}

		return resultList;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		if(table==null) throw new IllegalArgumentException(" table is null !");
		if(mapTransformer==null) throw new IllegalArgumentException(" mapTransformer is null !");
	}

}
