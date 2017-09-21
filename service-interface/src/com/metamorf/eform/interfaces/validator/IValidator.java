package com.metamorf.eform.interfaces.validator;

import java.util.List;
import java.util.Locale;

import com.metamorf.eform.common.exception.ErrorHolder;
import com.metamorf.eform.common.exception.SystemException;

public interface IValidator<T> {

	public abstract List<ErrorHolder> validate(T anObject, Object... arguments);

	public abstract List<ErrorHolder> validate(T anObject);
	
	public SystemException validateFromAction(Locale locale, final T anObject);
	
	public SystemException validateFromAction(Locale locale, final T anObject, final Object... arguments);

}
