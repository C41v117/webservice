package com.metamorf.eform.interfaces.master;

import java.util.List;

import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.entity.settings.ApprovalReason;
import com.metamorf.eform.interfaces.core.IBaseService;

public interface IApprovalReasonService extends IBaseService<ApprovalReason>{
	public List<ApprovalReason> findAll() throws SystemException;
}