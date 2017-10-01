package com.metamorf.eform.interfaces.user;

import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.entity.user.SendEmail;
import com.metamorf.eform.interfaces.core.IBaseService;

public interface ISendEmailService extends IBaseService<SendEmail>{
	public void sendEmail (SendEmail email) throws SystemException;
}