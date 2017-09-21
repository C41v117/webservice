package com.metamorf.eform.service.mytask;

import java.util.List;

import com.metamorf.eform.common.data.util.SearchFilter;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.interfaces.mytask.IMyTaskPendingService;

public class MyTaskPendingService implements IMyTaskPendingService{
	@Override
	public Long countPendingTask(Long nodeKey, List<SearchFilter> searchFilters)
			throws SystemException {
		return 0L;
	}

}
