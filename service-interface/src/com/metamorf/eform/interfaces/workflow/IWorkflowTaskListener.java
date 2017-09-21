package com.metamorf.eform.interfaces.workflow;

import java.util.Map;

import org.activiti.engine.task.Task;
import org.joda.time.DateTime;

public interface IWorkflowTaskListener {
	Task taskEntered(DateTime enterTime, String processInstanceId, String taskDefinitionKey, Task task);
	
    void taskStarted(IWorkflowTask workflowTask, DateTime startTime);

    void taskFinished(IWorkflowTask workflowTask, DateTime finishTime, Map<String, Object> variables);

    void taskReleased(IWorkflowTask workflowTask, DateTime releaseTime);
}
