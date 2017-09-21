package com.metamorf.eform.common.util;

import com.metamorf.eform.common.exception.SystemException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;


public class CopyFunction {
	public static Object copyFields(final Object originalEntity, final Object newEntity, String... listFields) throws SystemException{
		Object toBeSaveEntity = null;
		try {
			toBeSaveEntity = BeanUtils.cloneBean(originalEntity);
			Class<?> newEntityClass = Class.forName(newEntity.getClass().getCanonicalName());
			for (String field : listFields) {
				Field copyField = ReflectionFunction.getField(newEntityClass,field);
				copyField.setAccessible(true);
				BeanUtils.copyProperty(toBeSaveEntity, field, copyField.get(newEntity));
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new SystemException(e);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw new SystemException(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new SystemException(e);
		} catch (SecurityException e) {
			e.printStackTrace();
			throw new SystemException(e);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			throw new SystemException(e);
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new SystemException(e);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new SystemException(e);
		}
		if(toBeSaveEntity!=null){
			return toBeSaveEntity;
		}else{
			return originalEntity;
		}
	}
}
