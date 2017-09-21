package com.metamorf.eform.entity.rest;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"ReferenceNo",
"TaskId",
"ActionCode"
})
public class RequestModelMainSaving {

@JsonProperty("ReferenceNo")
private String referenceNo;
@JsonProperty("TaskId")
private Integer taskId;
@JsonProperty("ActionCode")
private String actionCode;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("ReferenceNo")
public String getReferenceNo() {
return referenceNo;
}

@JsonProperty("ReferenceNo")
public void setReferenceNo(String referenceNo) {
this.referenceNo = referenceNo;
}

@JsonProperty("TaskId")
public Integer getTaskId() {
return taskId;
}

@JsonProperty("TaskId")
public void setTaskId(Integer taskId) {
this.taskId = taskId;
}

@JsonProperty("ActionCode")
public String getActionCode() {
return actionCode;
}

@JsonProperty("ActionCode")
public void setActionCode(String actionCode) {
this.actionCode = actionCode;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}
