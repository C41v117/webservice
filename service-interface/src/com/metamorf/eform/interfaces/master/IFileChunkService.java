package com.metamorf.eform.interfaces.master;

import java.util.List;

import com.metamorf.eform.common.enumer.ImageType;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.entity.master.FileChunk;
import com.metamorf.eform.interfaces.core.IBaseService;

public interface IFileChunkService extends IBaseService<FileChunk>{
	public List<FileChunk> findAll() throws SystemException;
	public List<FileChunk> findNewerFileChunk(int version) throws SystemException;
	public FileChunk findByName(String name) throws SystemException;
	public List<FileChunk> findAllByPhoneNumber(String phoneNumber) throws SystemException;
	public void resetFileChunk(String fileName) throws SystemException;
	public void resetFileChunk(Long customerId, ImageType type) throws SystemException;
	/**
	 * must use parent CustomerId to get filechunk
	 * @param customerId
	 * @param phoneNo
	 * @param type
	 * @param newFileName
	 * @throws SystemException
	 */
	public void updateFileChunkName(Long customerId, ImageType type, String newFileName) throws SystemException;
	
	/**
	 * must use registration customer id to get filechunk
	 * @param customerId
	 * @param customer
	 * @throws SystemException
	 */
	public boolean checkAllCompletedFileStatus(Long customerId) throws SystemException;
	
	/**
	 * @param taskId
	 * @return
	 * @throws SystemException
	 */
	public boolean checkAllCompletedFileStatusByTaskId(Long taskId) throws SystemException;
	public boolean updateFileChunkForLoan(FileChunk fileChunk, String referenceNo) throws SystemException;
	void deleteByTaskId(Long taskId);
}