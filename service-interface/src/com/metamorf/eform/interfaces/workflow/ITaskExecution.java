package com.metamorf.eform.interfaces.workflow;

import com.metamorf.eform.interfaces.workflow.exceptions.UndefinedVariableException;

import org.activiti.engine.RuntimeService;

public interface ITaskExecution extends IWorkflowTaskInfo {
    abstract Object getVariable(String key);

    abstract Object getLocalVariable(String key);

    abstract void setVariable(String key, Object value) throws UndefinedVariableException;

    abstract void setLocalVariable(String key, Object value);

    abstract IWorkflowService getWorkflowService();

    abstract RuntimeService getRuntimeService();

}
