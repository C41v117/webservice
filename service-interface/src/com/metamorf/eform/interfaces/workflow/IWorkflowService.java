package com.metamorf.eform.interfaces.workflow;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;

public interface IWorkflowService {

    /**
     * Returns list of tasks for specified taskKey, roles, and user id.
     * @param taskKey
     *        name of form, used as task id
     * @param roles
     *        List of roles for the specified user
     * @param userId
     *        User id
     * @return list of available tasks
     */
    public abstract List<IWorkflowTask> getAssignments(String taskKey, List<String> roles, String userId);

    /**
     * Returns list of available tasks for specified process, groups and user id.
     * 
     * @param processName
     *        Id of workflow
     * @param taskKey
     *        name of form, used as task id
     * @param roles
     *        List of roles for the specified user
     * @param userId
     *        User id
     * @return list of available tasks
     */
    public abstract List<IWorkflowTask> getAssignments(String processName, String taskKey, List<String> roles,
            String userId);

    /**
     * Returns top most task available and immediately start the task.
     * 
     * @param taskKey
     *        name of form, used as task id
     * @param roles
     * @param userId
     * @return task
     */
    public abstract IWorkflowTask acquireSingleTask(String taskKey, List<String> roles, String userId);

    /**
     * Returns top most task available for the specified process and immediately start the task.
     * 
     * @param processName
     * @param taskKey
     *        name of form, used as task id
     * @param roles
     * @param userId
     * @return
     */
    public abstract IWorkflowTask acquireSingleTask(String processName, String taskKey, List<String> roles,
            String userId, List<Long> ids);

    public abstract IWorkflowTask acquireSingleTask(String processName, String taskKey, List<String> roles, String userId, List<Long> ids, String workflowId);
    
    /**
     * Initiates an instance of workflow process.
     * 
     * @param processName
     *        process id of the workflow definition
     * @param processVariables
     * @return IWorkflow
     */
    public abstract IWorkflow startWorkflow(String processName, String businessKey, Map<String, Object> processVariables);

    /**
     * Returns task object represented by task Id.
     * 
     * @param processInstanceId
     *        referenced process Instance Id
     * @param taskId
     * @return task object
     */
    public abstract IWorkflowTask getTask(String processInstanceId, String taskId);

    /**
     * Returns Task object represented by business key.
     * 
     * @param businessKey
     * @return task object
     */
    public abstract IWorkflowTask getTaskByBusinessKey(String businessKey);

    /**
     * Signal running process.
     * 
     * @param processName
     *        the process name
     * @param workflowId
     *        the workflowId referred to
     * @param signalName
     *        the signal name defined in workflow
     */
    public abstract void signal(String processName, String workflowId, String signalName);  

    /**
     * Additional initialization that will be called on bean initialization.
     */
    public abstract void initialize();

    public abstract RuntimeService getRuntimeService();

    public abstract RepositoryService getRepositoryService();
    
    public void setTaskPriority(String businessKey, int priority, String workflowId);
    
    public void setWorkflowProcessVariables(String processInstanceId, Map<String, Object> processVariables);
    
    public IWorkflowTask acquireSingleTaskApproval(String processName, String taskKey, List<String> roles, String userId, List<Long> ids, boolean needVerifier);
    
    public IWorkflowTask getTask(String taskId);
    
    public abstract IWorkflowTask acquireSingleTaskEdd(String processName, String taskKey, List<String> roles, String userId,  String workflowId);
    
    public abstract IWorkflowTask acquireSingleTaskForCallback(String processName, String taskKey, List<String> roles,
            String userId, List<Long> ids, Date dateCallback);
    
    public abstract IWorkflowTask acquireSingleTaskCallback(String processName, String taskKey, List<String> roles, String userId, List<Long> ids, String workflowId, Date dateCallback);

	public IWorkflowTask acquireSingleTaskDeviasi(String processName, String taskKey, List<String> roles, String userId,
			String workflowId);
}
