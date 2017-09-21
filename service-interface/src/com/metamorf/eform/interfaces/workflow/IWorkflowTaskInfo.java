package com.metamorf.eform.interfaces.workflow;

public interface IWorkflowTaskInfo {
    abstract String getBusinessKey();

    abstract String getMainProcessInstanceId();

    abstract String getCurrentProcessInstanceId();

    abstract String getProcessName();

    abstract String getTaskName();

    abstract String getExecutionId();

    abstract String getUserId();
}
