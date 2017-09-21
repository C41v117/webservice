package com.metamorf.eform.entity.audit;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.DB2Codec;

import com.metamorf.eform.common.core.IAuditTrailConstant;
import com.metamorf.eform.common.core.SystemParameter;
import com.metamorf.eform.common.util.DateTimeFunction;
import com.softtech.kismiss.enumer.HorizontalAlignment;
import com.softtech.kismiss.enumer.Orientation;
import com.softtech.kismiss.enumer.PaperType;
import com.softtech.kismiss.property.Detail;
import com.softtech.kismiss.property.Header;
import com.softtech.kismiss.property.Kismiss;
import com.softtech.kismiss.property.Property;


@Entity(name = "LOG_AUDIT_TRAIL")
@Kismiss(name = "VIEW_OWN_ACTIVITY", columnAutoSize = true, paperType= PaperType.A4, orientation=Orientation.Landscape, 
isTitleEveryPage=false, pageNumberAlignment=HorizontalAlignment.Center, noDataFound="Data tidak ditemukan")
@Header(columnHeaderHeight=25, isColumnHeaderBold=true, columnHeaderColor="#BDBBBC", lineWidth=0.5)
@Detail(lineWidth=0.5)
public class LogAuditTrail implements Serializable{
	
	private static final long serialVersionUID = 5694382991395130824L;

	public static final String ACCESS_TIME = "accessTime";

	// Constructors
	public static final String LOG_AUDIT_TRAIL_CODE="code";
	
	public static final String LOG_AUDIT_TRAIL_DESCR="userName";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACCESS_TIME")
	private Date accessTime;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "FULL_NAME",length = 100)
	private String fullName;
	
	@Column(name = "ACTIVITY", length = 50)
	private String activity;
	
	@Column(name = "DESCRIPTION", length = 200)
	private String description;
	
	@Column(name = "CODE")
	private String code;
	
	@Column(name = "CLIENT_HOST", length = 45)
	private String clientHost;
	
	@Column(name = "STATUS", length = 50)
	private String status;
	
	@Column(name = "EVENT", length = 1500)
	private String event;
	
	@Column(name = "USER_ID")
	private Long userId;
	
	@Column(name = "COMPANY")
	private String company;
	
	@Column(name = "MASTER_DATA_APPROVAL_ID")
	private Long masterDataApprovalId;
	
	@Column(name = "MODULE_ID")
	private String moduleId;
	
	/** default constructor */
	public LogAuditTrail() {
	}
	
	public LogAuditTrail(Long id, Date accessTime, String userName,
			String fullName, String activity, String description, String code,
			String clientHost, String status, String event) {
		this.id = id;
		this.accessTime = accessTime;
		this.userName = userName;
		this.fullName = fullName;
		this.activity = activity;
		this.description = description;
		this.code = code;
		this.clientHost = clientHost;
		this.status = status;
		this.event = event;
	}

	public LogAuditTrail(Date accessTime, String userName,
			String fullName, String code,
			String clientHost, String status, String event, Long userId, String company) {
		this.accessTime = accessTime;
		this.userName = userName;
		this.fullName = fullName;
		this.activity = IAuditTrailConstant.activityMap.get(code);
		this.description = IAuditTrailConstant.activityDescriptionMap.get(code);
		this.code = code;
		this.clientHost = clientHost;
		this.status = status;
		this.event = event;
		this.userId = userId;
		this.company = company;
	}
	
	public LogAuditTrail(String userName,
			String fullName, String code,
			String clientHost, String status, String event, Long userId, String company) {
		this.accessTime = DateTimeFunction.getCurrentDate();
		this.userName = userName;
		this.fullName = fullName;
		this.activity = IAuditTrailConstant.activityMap.get(code);
		this.description = IAuditTrailConstant.activityDescriptionMap.get(code);
		this.code = code;
		this.clientHost = clientHost;
		this.status = status;
		this.event = event;
		this.userId = userId;
		this.company = company;
	}
	
	public LogAuditTrail(String userName,
			String fullName, String code,
			String clientHost, String status, String event, Long userId, String company, Long masterDataApprovalId, String moduleId) {
		this.accessTime = DateTimeFunction.getCurrentDate();
		this.userName = userName;
		this.fullName = fullName;
		this.activity = IAuditTrailConstant.activityMap.get(code);
		this.description = IAuditTrailConstant.activityDescriptionMap.get(code);
		this.code = code;
		this.clientHost = clientHost;
		this.status = status;
		this.event = event;
		this.userId = userId;
		this.company = company;
		this.masterDataApprovalId = masterDataApprovalId;
		this.moduleId = moduleId;
	}
	
	
	
	@Transient
	@Property(name="Access Time", position = 0 , width = 6)
	public String getDateReport() {
		SimpleDateFormat sdf = new SimpleDateFormat(SystemParameter.DATE_FORMAT); 
		return sdf.format(accessTime);
	}
	
	public Date getAccessTime() {
		return this.accessTime;
	}
	
	@Property(name="IP Address", position = 4 , width = 5)
	public String getClientHost() {
		return this.clientHost;
	}

	public String getCode() {
		return this.code;
	}
	
	@Property(name="Description", position = 6 , width = 8)
	public String getDescription() {
		return this.description;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@Property(name="Activity", position = 5 , width = 7)
	public String getActivity() {
		return this.activity;
	}
	
	@Property(name="Status", position = 1 , width = 4)
	public String getStatus() {
		return this.status;
	}
	
	@Property(name="User Id", position = 2 , width = 6)
	public String getUserName() {
		return userName;
	}

	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}

	public void setClientHost(String clientHost) {
		this.clientHost = clientHost;
	}

	
	public void setCode(String code) {
		this.code = code;
	}
	
	public void setDescription(String descr) {
		this.description = descr;
	}

	public void setId(Long pkLogAuditTrail) {
		this.id = pkLogAuditTrail;
	}

	public void setActivity(String logGroup) {
		this.activity = logGroup;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Property(name="Full Name", position = 3 , width = 8)
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	@Property(name="Event", position = 7 , width = 20)
	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Long getMasterDataApprovalId() {
		return masterDataApprovalId;
	}

	public void setMasterDataApprovalId(Long masterDataApprovalId) {
		this.masterDataApprovalId = masterDataApprovalId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
	
	public void escapeData(){
		if(this!=null){
			if(this.userName!=null){
				this.userName = ESAPI.encoder().encodeForSQL(new DB2Codec(), this.userName);
				this.userName = this.userName.substring(0, this.userName.length()>30?30:this.userName.length());
			}
			if(this.fullName!=null){
				this.fullName = ESAPI.encoder().encodeForSQL(new DB2Codec(), this.fullName);
				this.fullName = this.fullName.substring(0, this.fullName.length()>100?100:this.fullName.length());
			}
			if(this.event!=null){
				this.event = ESAPI.encoder().encodeForSQL(new DB2Codec(), this.event);
				this.event = this.event.substring(0, this.event.length()>1500?1500:this.event.length());
			}
		}
	}

	public static final String ID				= "id";
	public static final String DATE_TIME_ACCESS	= "accessTime";
	public static final String USER_NAME		= "userName";
	public static final String FULL_NAME		= "fullName";
	public static final String ACTIVITY			= "activity";
	public static final String DESCRIPTION		= "description";
	public static final String USER_ID			= "userId";
	public static final String COMPANY			= "company";
	public static final String CLIENT_HOST		= "clientHost";
	public static final String EVENT			= "event";
	public static final String MASTER_DATA_APPROVAL_ID	= "masterDataApprovalId";
	public static final String MODULE_ID		= "moduleId";
	public static final String STATUS			= "status";
	
	public static final String[] MAINTENANCE_LIST_FIELDS = {ID, DATE_TIME_ACCESS, STATUS, COMPANY, USER_NAME, FULL_NAME, ACTIVITY, DESCRIPTION, USER_ID, CLIENT_HOST, MASTER_DATA_APPROVAL_ID, EVENT, MODULE_ID};
	
}