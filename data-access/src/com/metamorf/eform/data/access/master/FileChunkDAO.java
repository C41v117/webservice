package com.metamorf.eform.data.access.master;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.enumer.ImageType;
import com.metamorf.eform.common.exception.ErrorHolder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.data.access.core.AbstractHibernate4DAO;
import com.metamorf.eform.entity.master.FileChunk;

public class FileChunkDAO extends AbstractHibernate4DAO<FileChunk,Long> implements IFileChunkDAO {
	
	public void delete(Long[] objectPKs) throws SystemException {
		// TODO Auto-generated method stub
	}
	
	public List<FileChunk> findAll(){
		return super.findAll();
	}

	@Override
	public FileChunk findByName(final String fileName) throws SystemException{
		FileChunk fileChunk = null;
		try{
			fileChunk = (FileChunk) getSession()
					.createCriteria(FileChunk.class)
					.add(Restrictions.eq(FileChunk.NAME, fileName))
					.uniqueResult();
		}catch(HibernateException e){
			throw new SystemException(new ErrorHolder("error.fileChunk.not.found"));
		}
		
		return fileChunk;
	}
	
	@Override
	public FileChunk findByPhoneNoAndType(final String phoneNo, final ImageType type) throws SystemException{
		FileChunk fileChunk = null;
		try{
			fileChunk = (FileChunk) getSession()
					.createCriteria(FileChunk.class)
					.add(Restrictions.eq(FileChunk.CUSTOMER_PHONE_NUMBER, phoneNo))
					.add(Restrictions.eq(FileChunk.TYPE, type))
					.uniqueResult();
		}catch(HibernateException e){
			throw new SystemException(new ErrorHolder("error.fileChunk.not.found"));
		}
		
		return fileChunk;
	}
	
	@Override
	public FileChunk findByCustomerIdAndType(final Long customerId, final ImageType type) throws SystemException{
		FileChunk fileChunk = null;
		try{
			fileChunk = (FileChunk) getSession()
					.createCriteria(FileChunk.class)
					.add(Restrictions.eq(FileChunk.CUSTOMER_ID, customerId))
					.add(Restrictions.eq(FileChunk.TYPE, type))
					.uniqueResult();
		}catch(HibernateException e){
			throw new SystemException(new ErrorHolder("error.fileChunk.not.found"));
		}
		
		return fileChunk;
	}
	
	public FileChunk findFileChunkById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	public void saveOrUpdate(FileChunk anObject) throws SystemException {
		//since FileChunk will not be created from Front End, so there is no need for insert
		 /*if(anObject.getParameterPK()==null)
			 super.create(anObject);
	     else {*/
	    	 super.update(anObject);
	     /*}*/
	}
	
	/*use only for unit testing*/
	public void saveOrUpdateForUnitTesting(FileChunk anObject) throws SystemException {
		super.update(anObject);
	}

	public PagingWrapper<FileChunk> findAllFileChunkWithFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findFetchedPropertyWithPagingWrapper(startNo, offset, null, FileChunk.MAINTENANCE_LIST_FIELDS, filter, order, null, false);
		/*return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);*/
	}

	@Override
	public List<FileChunk> findAll(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findFetchedProperty(startNo, offset, null, FileChunk.MAINTENANCE_LIST_FIELDS, filter, order, null, false);
	}

	@Override
	public void deleteByTaskid(Long taskId) throws SystemException {
		String sqlDelete = "DELETE FILE_CHUNK WHERE TASK_ID = ?";
		Query query = getSession().createSQLQuery(sqlDelete).setParameter(0, taskId);
		query.executeUpdate();
	}
	
	
}
