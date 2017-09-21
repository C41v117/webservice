package com.metamorf.eform.data.access.master;

import java.util.List;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.enumer.ModuleType;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.entity.master.Version;

public interface IVersionDAO {
	
	public abstract void delete(Long[] objectPKs) throws SystemException;
	
	public Version findVersionByModule(ModuleType module) throws SystemException;
	
	public abstract PagingWrapper<Version> findAllVersionWithFilter(int startNo,
			int offset,List<SearchFilter> filter,List<SearchOrder> order) throws SystemException;

	public abstract Version findVersionById(Long id)
			throws SystemException;
	
	public abstract List<Version> findAll() throws SystemException;
	
	public abstract List<Version> findAll(int startNo,
			int offset,List<SearchFilter> filter,List<SearchOrder> order)
			throws SystemException;

	public abstract void saveOrUpdate(Version anObject)
			throws SystemException;
	
	public boolean isUpdateAvailable(ModuleType moduleType, int version) throws SystemException;
}