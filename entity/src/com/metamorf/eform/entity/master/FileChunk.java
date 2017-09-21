package com.metamorf.eform.entity.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.metamorf.eform.common.enumer.ImageType;

@Entity(name = "file_chunk")
public class FileChunk {
	
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String CUSTOMER_ID = "customerId";
	public static final String CUSTOMER_PHONE_NUMBER = "customerPhoneNumber";
	public static final String PART_TOTAL = "partTotal";
	public static final String PART_SUCCESS = "partSuccess";
	public static final String IS_COMPLETED = "isCompleted";
	public static final String TYPE = "type";
	public static final String LOB = "lob";
	public static final String INFO = "info";
	public static final String TASK_ID = "taskId";
	public static final String CIF_FROM_LOAN = "cifFromLoan";
	
	public static String[] MAINTENANCE_LIST_FIELDS = {
		ID, 
		CUSTOMER_ID, 
		CUSTOMER_PHONE_NUMBER, 
		NAME, 
		PART_TOTAL, 
		PART_SUCCESS, 
		IS_COMPLETED,
		TYPE,
		LOB,
		INFO,
		TASK_ID,
		CIF_FROM_LOAN
	};
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "CUSTOMER_ID")
	private Long customerId;
	
	@Column(name = "CUSTOMER_PHONE_NUMBER")
	private String customerPhoneNumber;
	
	@Column(name = "TYPE")
	private ImageType type;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "PART_TOTAL")
	private Integer partTotal;
	
	@Column(name = "PART_SUCCESS")
	private String partSuccess;

	@Column(name = "IS_COMPLETED")
	private Boolean isCompleted;
	
	@Column(name = "LOB")
	private Integer lob;
	
	@Column(name = "TASK_ID")
	private Long taskId;
	
	@Column(name = "APP_ID")
	private Integer appId;
	
	@Column(name = "INFO")
	private String info;
	
	@Column(name = "CIF_FROM_LOAN")
	private String cifFromLoan;
	
	public Integer getLob() {
		return lob;
	}

	public void setLob(Integer lob) {
		this.lob = lob;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPartTotal() {
		return partTotal;
	}

	public void setPartTotal(Integer partTotal) {
		this.partTotal = partTotal;
	}

	public String getPartSuccess() {
		return partSuccess;
	}

	public void setPartSuccess(String partSuccess) {
		this.partSuccess = partSuccess;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public ImageType getType() {
		return type;
	}

	public void setType(ImageType type) {
		this.type = type;
	}

	public Boolean getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public static FileChunk reset(FileChunk fileChunk){
		fileChunk.setIsCompleted(false);
		fileChunk.setPartSuccess("");
		fileChunk.setPartTotal(0);
		return fileChunk;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getCifFromLoan() {
		return cifFromLoan;
	}

	public void setCifFromLoan(String cifFromLoan) {
		this.cifFromLoan = cifFromLoan;
	}
}