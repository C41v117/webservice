package com.metamorf.eform.interfaces.workflow;

import com.metamorf.eform.interfaces.workflow.ITaskExecution;

public interface ITaskHandler {
    abstract void execute(ITaskExecution taskExec) throws Exception;
}
