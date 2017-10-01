package com.metamorf.eform.web.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.metamorf.eform.common.enumer.ModuleType;
import com.metamorf.eform.entity.master.Version;
import com.metamorf.eform.interfaces.master.IVersionService;
import com.metamorf.eform.interfaces.settings.IAppParameterService;
import com.metamorf.eform.service.core.LoaderFactory;

public class SystemParameterReloadJob extends QuartzJobBean implements StatefulJob {
	Logger logger = LoggerFactory.getLogger(SystemParameterReloadJob.class);
	
	private static int versionHeld = -1;
	
	private IAppParameterService appParameterService;
	private IVersionService versionService;
	
	public IVersionService getVersionService() {
		return versionService;
	}

	public void setVersionService(IVersionService versionService) {
		this.versionService = versionService;
	}

	public IAppParameterService getAppParameterService() {
		return appParameterService;
	}

	public void setAppParameterService(IAppParameterService appParameterService) {
		this.appParameterService = appParameterService;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		logger.info("Start reload system parameter job....");
		Version version = versionService.findByModuleType(ModuleType.SYSTEM_PARAMETER);
		// don't load sysparam if there is no update
		if (version.getVersion() > versionHeld) {
			versionHeld = version.getVersion();
			LoaderFactory.initializeAppParameter(appParameterService);
		}
	}
	
}