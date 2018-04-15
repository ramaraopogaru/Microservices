package com.dotridge.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Module implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int moduleId;
	@Column(unique=true,nullable=false)
	private String moduleName;
	@OneToMany(mappedBy="module",targetEntity=ModuleToPermission.class)
	private List<ModuleToPermission> moduleToPermission;
	
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public List<ModuleToPermission> getModuleToPermission() {
		return moduleToPermission;
	}
	public void setModuleToPermission(List<ModuleToPermission> moduleToPermission) {
		this.moduleToPermission = moduleToPermission;
	}
	@Override
	public String toString() {
		return "Module [moduleId=" + moduleId + ", moduleName=" + moduleName + ", moduleToPermission="
				+ moduleToPermission + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + moduleId;
		result = prime * result + ((moduleName == null) ? 0 : moduleName.hashCode());
		result = prime * result + ((moduleToPermission == null) ? 0 : moduleToPermission.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Module other = (Module) obj;
		if (moduleId != other.moduleId)
			return false;
		if (moduleName == null) {
			if (other.moduleName != null)
				return false;
		} else if (!moduleName.equals(other.moduleName))
			return false;
		if (moduleToPermission == null) {
			if (other.moduleToPermission != null)
				return false;
		} else if (!moduleToPermission.equals(other.moduleToPermission))
			return false;
		return true;
	}
}
