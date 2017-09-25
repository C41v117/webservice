package com.metamorf.eform.entity.settings;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.metamorf.eform.entity.mytask.MasterDataApproval;

//@Entity(name="APPROVAL_REASON")
public class ApprovalReason implements Serializable{
	/*TODO
	 * Add action so that we know this approval reason is inserted on what action
	 * */
	
	private static final long serialVersionUID = 1763472413297070905L;

	public static final String ID						= "id";
	//public static final String MASTER_DATA_APPROVAL_ID	= "masterDataApprovalId";
	public static final String MESSAGE					= "message";
	
	public static final String[] MAINTENANCE_LIST_FIELDS = {"id", "message"};

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MASTER_DATA_APPROVAL_ID", nullable=false)
	private MasterDataApproval masterDataApproval;

	@Column(name="MESSAGE", length=500)
	private String message;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public MasterDataApproval getMasterDataApproval() {
		return masterDataApproval;
	}
	public void setMasterDataApproval(MasterDataApproval masterDataApproval) {
		this.masterDataApproval = masterDataApproval;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
