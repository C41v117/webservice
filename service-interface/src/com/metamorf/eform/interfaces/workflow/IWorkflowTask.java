package com.metamorf.eform.interfaces.workflow;

import java.util.Map;

import org.activiti.engine.task.Task;

public interface IWorkflowTask extends IWorkflowTaskInfo {
    abstract void start();

    abstract void finish(Map<String, Object> completionVariables);

    abstract void suspend();

    abstract void release();

    abstract Map<String, Object> getProcessVariables();

    abstract Map<String, Object> getTaskLocalVariables();

    abstract boolean isActive();
    
    abstract Task getTask();
}
