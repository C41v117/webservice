package com.metamorf.eform.interfaces.settings;

import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.entity.settings.AppParameter;
import com.metamorf.eform.interfaces.core.IBaseService;

import java.util.List;

public interface IAppParameterService extends IBaseService<AppParameter>{
	public List<AppParameter> findAll() throws SystemException;

	public abstract List<AppParameter> findAllCron() throws SystemException;

	void saveOrUpdate(List<AppParameter> appParameters) throws Exception;
}