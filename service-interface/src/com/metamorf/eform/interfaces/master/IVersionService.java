package com.metamorf.eform.interfaces.master;

import java.util.List;

import com.metamorf.eform.common.enumer.ModuleType;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.entity.master.Version;
import com.metamorf.eform.interfaces.core.IBaseService;

public interface IVersionService extends IBaseService<Version>{
	public List<Version> findAll() throws SystemException;
	public boolean isUpdateAvailable(ModuleType moduleType, int version) throws SystemException;
	public Version findByModuleType(ModuleType moduleType) throws SystemException;
}