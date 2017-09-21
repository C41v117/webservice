package com.metamorf.eform.data.access.master;

import java.util.List;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.enumer.ImageType;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.entity.master.FileChunk;

public interface IFileChunkDAO {
	
	public abstract void delete(Long[] objectPKs) throws SystemException;
	
	public abstract PagingWrapper<FileChunk> findAllFileChunkWithFilter(int startNo,
			int offset,List<SearchFilter> filter,List<SearchOrder> order) throws SystemException;

	public abstract FileChunk findFileChunkById(Long id)
			throws SystemException;
	
	public abstract FileChunk findByPhoneNoAndType(final String phoneNo, final ImageType type) throws SystemException;
	
	public abstract FileChunk findByCustomerIdAndType(final Long customerId, final ImageType type) throws SystemException;
	
	public abstract FileChunk findByName(final String fileName) throws SystemException;
	
	public abstract List<FileChunk> findAll() throws SystemException;
	
	public abstract List<FileChunk> findAll(int startNo,
			int offset,List<SearchFilter> filter,List<SearchOrder> order)
			throws SystemException;

	public abstract void saveOrUpdate(FileChunk anObject)
			throws SystemException;
	
	public abstract void deleteByTaskid(Long taskId) throws SystemException;
}