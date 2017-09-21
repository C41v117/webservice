package com.metamorf.eform.interfaces.mytask;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.exception.SystemException;

import java.util.List;

public interface IMyTaskPendingService {
	public abstract Long countPendingTask(Long nodeKey, List<SearchFilter> searchFilters)
			throws SystemException;
}
