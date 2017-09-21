package com.metamorf.eform.data.access.master;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.data.util.SearchOrder;
import com.metamorf.eform.common.enumer.ModuleType;
import com.metamorf.eform.common.exception.ErrorHolder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.common.paging.PagingWrapper;
import com.metamorf.eform.data.access.core.AbstractHibernate4DAO;
import com.metamorf.eform.entity.master.Version;

public class VersionDAO extends AbstractHibernate4DAO<Version,Long> implements IVersionDAO {
	
	public void delete(Long[] objectPKs) throws SystemException {
		// TODO Auto-generated method stub
	}
	
	public List<Version> findAll(){
		return super.findAll();
	}

	public Version findVersionById(Long id) throws SystemException {
		return super.findByPK(id);
	}
	
	public Version findVersionByModule(ModuleType module) throws SystemException {
		Version version;
		try{
			version = (Version) getSession().createCriteria(Version.class)
					.add(Restrictions.eq(Version.MODULE_TYPE, module))
					.uniqueResult();
		}catch(HibernateException e){
			throw new SystemException(new ErrorHolder("error.version.not.found"));
		}
		return version;
	}

	public void saveOrUpdate(Version anObject) throws SystemException {
		//since Version will not be created from Front End, so there is no need for insert
		 /*if(anObject.getParameterPK()==null)
			 super.create(anObject);
	     else {*/
	    	 super.update(anObject);
	     /*}*/
	}
	
	/*use only for unit testing*/
	public void saveOrUpdateForUnitTesting(Version anObject) throws SystemException {
		super.update(anObject);
	}

	public PagingWrapper<Version> findAllVersionWithFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findFetchedPropertyWithPagingWrapper(startNo, offset, null, Version.MAINTENANCE_LIST_FIELDS, filter, order, null, false);
		/*return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);*/
	}

	@Override
	public List<Version> findAll(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findFetchedProperty(startNo, offset, null, Version.MAINTENANCE_LIST_FIELDS, filter, order, null, false);
	}
	
	@Override
	public boolean isUpdateAvailable(ModuleType moduleType, int version) throws SystemException{
		List<Criterion> criterias = new ArrayList<Criterion>();
		criterias.add(Restrictions.eq(Version.MODULE_TYPE, moduleType));
		
		List<Version> versions = findByCriterion(criterias, null, 0, 1, null);
		if (versions.size() > 0) {
			Version versionData = versions.get(0);
			return (versionData.getVersion() > version) ? true: false;
		}

		return false;
	}
	
}
