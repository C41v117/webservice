package com.metamorf.eform.entity.settings;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.metamorf.eform.common.core.SystemConstant;

//@Entity
//@Table(name = "LOOKUP_GROUP", uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class LookupGroup  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4125719693547202686L;
	public static final String NAME = "name";
	public static final String DESCR = "description";
	public static final String IS_UPDATABLE = "updatable";
	public static final String IS_VIEWABLE = "viewable";
	public static final String LOB = "lob";

	@Id
	@Column(name = "NAME", unique = true, length = 50)
	private String name;
	
	@Column(name = "GROUP_DESCR", length = 100)
	private String description;
	
	@Column(name = "IS_UPDATABLE", precision = 22, scale = 0)
	private boolean updatable;
	
	@Column(name = "IS_VIEWABLE", precision = 22, scale = 0)
	private boolean viewable;
	
	@Column(name="HAS_ELEMENT")
	private boolean hasElement;
	
	@Column(name="CHILD_LOOKUP_GROUP")
	private String childLookupGroup;
	
	@Column(name="CHILD_LOOKUP_VIEW")
	private String childLookupView;
	
	@Transient
	private List<Lookup> elements;
	
	@Transient
	private String updatableDescription;

	// Constructors

	/** default constructor */
	public LookupGroup() {
	}

	/** full constructor */
	public LookupGroup(String lookupGroup, String groupDescr) {
		this.name = lookupGroup;
		this.description = groupDescr;
}

	public String getName() {
		return this.name;
	}

	public void setName(String lookupGroup) {
		this.name = lookupGroup;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String groupDescr) {
		this.description = groupDescr;
	}

    public boolean isUpdatable() {
		return updatable;
	}

	public void setUpdatable(boolean updatable) {
		this.updatable = updatable;
	}

	public boolean isViewable() {
		return viewable;
	}

	public void setViewable(boolean isViewable) {
		this.viewable = isViewable;
	}

	public boolean isHasElement() {
		return hasElement;
	}

	public void setHasElement(boolean hasElement) {
		this.hasElement = hasElement;
	}

	@Transient
    public List<Lookup> getElements() {
		return elements;
	}

	public void setElements(List<Lookup> elements) {
		this.elements = elements;
	}
	
	public String getChildLookupGroup() {
		return childLookupGroup;
	}

	public void setChildLookupGroup(String childLookupGroup) {
		this.childLookupGroup = childLookupGroup;
	}

	public String getChildLookupView() {
		return childLookupView;
	}

	public void setChildLookupView(String childLookupView) {
		this.childLookupView = childLookupView;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((elements == null) ? 0 : elements.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LookupGroup other = (LookupGroup) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
	    if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (elements == null) {
			if (other.elements != null)
				return false;
		} else if (!elements.equals(other.elements))
			return false;
		return true;
	}
	
	
	
	public Lookup getElementByCode(String code) {
        if (code == null) return null;
        for (Lookup lookup : elements) {
			if( lookup.getCode().equals(code)) return lookup;
		}
        return null;
    }
	
	public Lookup getByLookupPK(long lookupPK) {
		 for (Lookup lookup : elements) {
				if( lookup.getId().longValue() == lookupPK) return lookup;
			}
	        return null;
	}
	
	public Lookup getByLookupPK(Long lookupPK) {
	     return getByLookupPK(lookupPK.longValue());
	}

	public String getUpdatableDescription() {
		updatableDescription=isUpdatable()?"Yes":"No";
		return updatableDescription;
	}

	public void setUpdatableDescription(String updatableDescription) {
		this.updatableDescription = updatableDescription;
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
		//return SystemConstant.ProjectType.userLobStr[getLob()];
		return SystemConstant.ProjectType.userLobList.get(getLob());
	}
}