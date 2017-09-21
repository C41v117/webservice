package com.metamorf.eform.common.util;

import java.util.Date;
import java.util.Map;

import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.Task;

public class TaskHelper implements Task {
    
	private String id;
	private String processInstanceId;
	private String processDefinitionId;
	private String executionId;
	private String assignee;
	private String taskDefinitionKey;
	@Override
	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}
	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}
	@Override
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	@Override
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
	@Override
	public String getExecutionId() {
		return executionId;
	}
	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}
	@Override
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	@Override
	public String getCategory() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Date getCreateTime() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Date getDueDate() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getFormKey() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getOwner() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getParentTaskId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Map<String, Object> getProcessVariables() {
		// TODO Auto-generated method stub
		return null;
	}	
	@Override
	public Map<String, Object> getTaskLocalVariables() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getTenantId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void delegate(String arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public DelegationState getDelegationState() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isSuspended() {
		return false;
	}
	@Override
	public void setCategory(String arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setDelegationState(DelegationState arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setDescription(String arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setDueDate(Date arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setFormKey(String arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setName(String arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setOwner(String arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setParentTaskId(String arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setPriority(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setTenantId(String arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
