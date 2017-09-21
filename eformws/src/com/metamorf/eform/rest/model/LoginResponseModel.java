package com.metamorf.eform.rest.model;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponseModel extends Response {
	private String deviceUid;

	@XmlElement(name = "username")
	@SerializedName("username")
	@JsonProperty("userName")
	private String username;
	
	@XmlElement(name = "user_id")
	@SerializedName("user_id")
	private Long userId;
	
	@XmlElement(name = "version")
	@SerializedName("version")
	private String version;
	
	@XmlElement(name = "token")
	@SerializedName("token")
	private String token;
	
	@XmlElement(name = "location_id")
	@SerializedName("location_id")
	private Long locationId;
	
	@XmlElement(name = "location_name")
	@SerializedName("location_name")
	private String locationName;
	
	@XmlElement(name = "agent_type")
	@SerializedName("agent_type")
	private String agentType;
	
	private MTFLoginModel jsonObject;
	
	public LoginResponseModel() {
		super();
	}
	
	public LoginResponseModel(String result, String message, String json,
			String username, Long userId, String version, String token, Long locationId, String locationName, String agentType) {
		super(result, message);
		this.username = username;
		this.userId = userId;
		this.version = version;
		this.token = token;
		this.locationId = locationId;
		this.locationName = locationName;
		this.agentType = agentType;
		this.json = json;
	}

	public LoginResponseModel(String result, String message) {
		super(result, message, null);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getAgentType() {
		return agentType;
	}

	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}

	public String getDeviceUid() {
		return deviceUid;
	}

	public void setDeviceUid(String deviceUid) {
		this.deviceUid = deviceUid;
	}

	public MTFLoginModel getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(MTFLoginModel jsonObject) {
		this.jsonObject = jsonObject;
	}

}