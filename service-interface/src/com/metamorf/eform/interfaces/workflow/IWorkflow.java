package com.metamorf.eform.interfaces.workflow;

public interface IWorkflow extends IWorkflowTaskInfo {
    public abstract void suspend();

    public abstract void resume();
}
