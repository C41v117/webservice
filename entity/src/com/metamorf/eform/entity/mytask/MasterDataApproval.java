package com.metamorf.eform.entity.mytask;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.DynamicUpdate;

import com.metamorf.eform.common.core.SystemConstant;
import com.metamorf.eform.common.core.SystemConstant.ProjectType;
import com.metamorf.eform.entity.settings.ApprovalReason;

//@Entity(name="MASTER_DATA_APPROVAL")
@DynamicUpdate(value=true)
public class MasterDataApproval implements Serializable{

	private static final long serialVersionUID = -3965467048592486571L;

	public static final String ID				= "id";
	public static final String MODULE_ID		= "moduleId";
	public static final String MODULE_NAME		= "moduleName";
	public static final String MASTER_DATA_ID	= "masterDataId";
	public static final String NAME				= "name";
	public static final String ACTION			= "action";
	public static final String STATUS			= "status";
	public static final String LATEST_MAKER_DATE= "latestMakerDate";
	public static final String LATEST_MAKER		= "latestMaker";
	public static final String LOB				= "lob";

	public static final String[] MAINTENANCE_LIST_FIELDS = {
		ID, MODULE_ID, MODULE_NAME, MASTER_DATA_ID, NAME, ACTION, STATUS, LATEST_MAKER_DATE, LATEST_MAKER, LOB
	} ;

	public MasterDataApproval() {		
		setLob(ProjectType.BOTH);
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="MODULE_ID", nullable=false, length=10)
	private String moduleId;
	
	@Column(name="MODULE_NAME", nullable=false, length=50)
	private String moduleName;
	
	@Column(name="MASTER_DATA_ID", length=50)
	private String masterDataId;
	
	@Column(name="NAME", length=255)
	private String name;
	
	@Column(name="ACTION", length=20)
	private Integer action;
	
	@Column(name="STATUS")
	private Integer status;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name="LATEST_MAKER_DATE")
	private Date latestMakerDate;
	
	@Column(name="LATEST_MAKER", length=16)
	private String latestMaker;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name="LATEST_APPROVAL_DATE")
	private Date latestApprovalDate;
	
	@Column(name="LATEST_APPROVAL_ACTOR", length=16)
	private String latestApprovalActor;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="masterDataApproval")
	@Cascade({CascadeType.PERSIST,CascadeType.MERGE,CascadeType.SAVE_UPDATE})
	@OrderBy("id desc")
	private List<ApprovalReason> approvalReasons;
	
	@Transient
	private Map<String,Object> approvalData;
	
	@Column(name="JSON_APPROVAL_DATA")
	private String jsonApprovalData;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getMasterDataId() {
		return masterDataId;
	}

	public void setMasterDataId(String masterDataId) {
		this.masterDataId = masterDataId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAction() {
		return action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getStatusDescr() {
		return SystemConstant.MyTaskApprovalStatus.MyTaskApprovalStatusStr[getStatus()];
	}
	
	public String getActionDescr() {
		String descr = SystemConstant.MakerAction.ActionStr[getAction()];
		return descr;
	}

	public Map<String,Object> getApprovalData() {
		if(approvalData==null){
			approvalData = new LinkedHashMap<String, Object>();
		}
		return approvalData;
	}

	public void setApprovalData(Map<String,Object> approvalData) {
		this.approvalData = approvalData;
	}

	public Date getLatestMakerDate() {
		return latestMakerDate;
	}

	public void setLatestMakerDate(Date latestMakerDate) {
		this.latestMakerDate = latestMakerDate;
	}

	public String getLatestMaker() {
		return latestMaker;
	}

	public void setLatestMaker(String latestMaker) {
		this.latestMaker = latestMaker;
	}

	public Date getLatestApprovalDate() {
		return latestApprovalDate;
	}

	public void setLatestApprovalDate(Date latestApprovalDate) {
		this.latestApprovalDate = latestApprovalDate;
	}

	public String getLatestApprovalActor() {
		return latestApprovalActor;
	}

	public void setLatestApprovalActor(String latestApprover) {
		this.latestApprovalActor = latestApprover;
	}

	public List<ApprovalReason> getApprovalReasons() {
		if (approvalReasons == null) approvalReasons = new ArrayList<ApprovalReason>();
		return approvalReasons;
	}

	public void setApprovalReasons(List<ApprovalReason> approvalReasons) {
		this.approvalReasons = approvalReasons;
	}

	public String getJsonApprovalData() {
		return jsonApprovalData;
	}

	public void setJsonApprovalData(String jsonApprovalData) {
		this.jsonApprovalData = jsonApprovalData;
	}

	@Override
	public String toString() {
		return "MasterDataApproval [id=" + id + ", moduleId=" + moduleId
				+ ", moduleName=" + moduleName + ", name=" + name + ", action="
				+ action + ", status=" + status + ", latestMakerDate="
				+ latestMakerDate + ", latestMaker=" + latestMaker 
				+ ", approvalData="+ approvalData +"]";
	}	

	//Penambahan field pembeda SDB & Sinaya
	@Column
	private Integer lob;

	public Integer getLob() {
		return lob;
	}
	public void setLob(Integer lob) {
		this.lob = lob;
	}
	
	@Transient
	public String getLobDescr(){
		return SystemConstant.ProjectType.userLobList.get(getLob());
	}
}
