package com.metamorf.eform.interfaces.settings;

import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.entity.settings.Lookup;
import com.metamorf.eform.interfaces.core.IBaseService;

import java.util.List;

public interface ILookupService extends IBaseService<Lookup> {

	/**
	 * 
	 * @param lookupGroup
	 * @return
	 * @throws SystemException
	 */
	List<Lookup> findByLookupGroup(String lookupGroup) throws SystemException;

	public List<Lookup> findAllByLookupGroup(String lookupGroup) throws SystemException;
	
	public void updateHighRisk(String lookupGroup, Long[] ids) throws SystemException;
	
	/**
	 * 
	 * @param code
	 * @return
	 * @throws SystemException
	 */
	Lookup findByCode(String code, String lookupGroup) throws SystemException;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws SystemException
	 */
	Lookup findById(Long id) throws SystemException;
	
	/**
	 * 
	 * @param fkLookupParent
	 * @return
	 * @throws SystemException
	 */
	List<Lookup> findLookupDetail(Long fkLookupParent) throws SystemException;
	
	/**
	 * 
	 * @param lookupParentId
	 * @param lookupDetail
	 * @throws SystemException
	 */
	void saveLookupDetail(Long lookupParentId, Lookup lookupDetail) throws SystemException;
	
	/**
	 * 
	 * @param parentLookupId
	 * @param lookupIds
	 * @throws SystemException
	 */
	void deleteLookupDetail(Long parentLookupId, Long[] lookupIds) throws SystemException;

	public abstract List<Lookup> findByLookupGroupOrderBy(String lookupGroup, String orderBy)
			throws SystemException;
	/**
	 * 
	 * @param name
	 * @return
	 * @throws SystemException
	 */
	public Lookup findByName(String name, String lookupGroup) throws SystemException;

	List<Lookup> findByLookupGroups(String[] lookupGroupFilters) throws SystemException;
	
	List<Lookup> findByLookupGroupNotCode(String lookupGroup, String[] notCode) throws SystemException;
	
	public List<Lookup> findGreaterVersion(int version) throws SystemException;

	void saveOrUpdate(List<Lookup> lookups) throws Exception;
}