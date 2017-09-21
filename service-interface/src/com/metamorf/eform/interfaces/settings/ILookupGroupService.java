package com.metamorf.eform.interfaces.settings;

import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.entity.settings.LookupGroup;
import com.metamorf.eform.interfaces.core.IBaseService;

import java.util.List;

public interface ILookupGroupService extends IBaseService<LookupGroup>{
	
	public List<LookupGroup> findAll() throws SystemException;
	
	public LookupGroup findByName(String name) throws SystemException;
	
}
