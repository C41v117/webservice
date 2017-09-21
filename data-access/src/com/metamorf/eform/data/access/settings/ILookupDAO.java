package com.metamorf.eform.data.access.settings;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.entity.settings.Lookup;

import java.util.List;
import java.util.Map;


public interface ILookupDAO {
	public abstract List<Lookup> findLookupByLookupGroup(String lookupGroup)
		throws SystemException;
	
	public abstract List<Lookup> findAllLookupByLookupGroup(String lookupGroup)
			throws SystemException;
	
	public abstract List<Lookup> findLookupByLookupGroupNotCode(String lookupGroup, String[] notCode)
			throws SystemException;
	
	public abstract PagingWrapper<Lookup> findAllLookupWithFilter(int startNo,
			int offset, List<SearchFilter> searchFilters, List<SearchOrder> searchOrders) throws SystemException ;
	
	public abstract Lookup findById(Long lookupId) throws SystemException ;
	
	public abstract void saveOrUpdate(Lookup lookup) throws SystemException ;
	
	public void setNoHighRiskByGroupName(String lookupGroup) throws SystemException;
	
	public void updateHighRisk(Long[] ids) throws SystemException;
	
	/**
	 * 
	 * @param code
	 * @param lookupGroup 
	 * @return
	 */
	Lookup findByCode(String code, String lookupGroup);
	
	/**
	 * 
	 * @param filters
	 * @param order
	 * @return
	 * @throws SystemException
	 */
	List<Lookup> findAll(List<SearchFilter> filters, List<SearchOrder> order) throws SystemException ;
	
	/**
	 * 
	 * @param ids
	 * @throws SystemException
	 */
	void delete(Long[] ids) throws SystemException;
	
	public abstract List<Lookup> findLookupByLookupGroupOrderBy(String lookupGroup,
			String orderBy) throws SystemException;
	
	/**
	 * 
	 * @param name
	 * @param lookupGroup 
	 * @return
	 */
	public abstract Lookup findByName(String name, String lookupGroup);

	public abstract Lookup findByNameIgnoreCase(String name, String lookupGroup);

	/**
	 * Find Lookup by Code, Lookup Group and Parent Lookup's Primary Key from Lookup and Lookup_Detail table joined
	 * @param code - Code from Lookup table
	 * @param lookupGroup - Lookup Group name from Lookup table
	 * @param lookupParentId - Parent Lookup's ID from Lookup_Detail table 
	 * @return Lookup entity
	 */
	public Lookup findLookup(String code, String lookupGroup, long lookupParentId);

	public List<Lookup> findAll(int startNo, int offset,
			List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException;

	public abstract Boolean isCodeExist(Long id, String newCode, String lookupGroup);

	public Boolean isAlternateEntryExist(Long id, Boolean isAlternateEntry, String lookupGroup);
	
	public abstract List<Lookup> findByLookupGroups(String[] lookupGroupFilters) throws SystemException;
	
	public abstract List<Lookup> findGreaterVersion(int version) throws SystemException;
	
	public Map<Long, String> getNameFromLookupByLookupGroupOrderBy(String lookupGroup, String orderBy) throws SystemException;
	
}
