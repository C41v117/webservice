package com.metamorf.eform.data.access.mytask;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.metamorf.eform.common.core.SystemConstant;
import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.data.access.core.AbstractHibernate4DAO;
import com.metamorf.eform.entity.mytask.MasterDataApproval;

public class MasterDataApprovalDAO extends AbstractHibernate4DAO<MasterDataApproval, Long> implements IMasterDataApprovalDAO {

	@Override
	public void saveOrUpdate(MasterDataApproval anObject) throws SystemException{
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		anObject.setJsonApprovalData(gson.toJson(anObject.getApprovalData()));
		if(anObject.getId()==null){
			super.create(anObject);
		}else{
			super.update(anObject);
		}
	}
	
	@Override
	public MasterDataApproval findById(Long id){
		return super.findByPK(id);
	}

	@Override
	public List<MasterDataApproval> findAll(int startNo, int offset,
			List<SearchFilter> filters, List<SearchOrder> orders)
			throws SystemException {
		return super.findFetchedProperty(startNo, offset, null, MasterDataApproval.MAINTENANCE_LIST_FIELDS, filters, orders, null, false);
	}

	@Override
	public void deleteObject(MasterDataApproval anObject) throws SystemException {
		
	}

	@Override
	public PagingWrapper<MasterDataApproval> findAllByFilter(int startNo,
			int offset, List<SearchFilter> searchFilters, List<SearchOrder> searchOrders)
			throws SystemException {
		return super.findFetchedPropertyWithPagingWrapper(startNo, offset, null, MasterDataApproval.MAINTENANCE_LIST_FIELDS, searchFilters, searchOrders, null, false);
	}
	
	public List<MasterDataApproval> findAll(List<SearchFilter> searchFilters, List<SearchOrder> searchOrders) throws SystemException {
		return super.findAll(searchFilters, searchOrders, null);
	}
	
	@Override
	public Boolean isExistByCriteria(List<SearchFilter> searchFilters) throws SystemException{
		List<Criterion> criterias = new ArrayList<Criterion>();
		for (SearchFilter searchFilter : searchFilters) {
			criterias.add(buildCriterion(searchFilter));
		}
		return super.getRowCount(criterias)>0?Boolean.TRUE:Boolean.FALSE;
	}
	
	@Override
	public Long countPendingTask(List<SearchFilter> searchFilters) throws SystemException{
		List<Criterion> criterias = new ArrayList<Criterion>();
		for (SearchFilter searchFilter : searchFilters) {
			criterias.add(buildCriterion(searchFilter));
		}
		return super.getRowCount(criterias);
	}

	@Override
	public Boolean isAvailableInApproval(String moduleId, String masterDataId, String name) throws SystemException {
		List<Criterion> criterias = new ArrayList<Criterion>();
		criterias.add(Restrictions.eq(MasterDataApproval.MODULE_ID, moduleId));
		if(StringUtils.trimToNull(masterDataId)!=null){
			criterias.add(Restrictions.eq(MasterDataApproval.MASTER_DATA_ID, masterDataId));
		}
		criterias.add(Restrictions.in(MasterDataApproval.STATUS, 
				new Object[] {SystemConstant.MyTaskApprovalStatus.PENDING, SystemConstant.MyTaskApprovalStatus.REQUEST_REPAIR, SystemConstant.MyTaskApprovalStatus.NEED_OVERRIDE}));
		if(StringUtils.trimToNull(name)!=null){
			criterias.add(Restrictions.eq(MasterDataApproval.NAME, name));
		}
		return getRowCount(criterias)>0?Boolean.FALSE:Boolean.TRUE;
	}
	
}
