package com.dotridge.service;

import java.util.List;

import com.dotridge.bean.RoleBean;
import com.dotridge.domain.Role;

public interface RoleService 
{
	public RoleBean mapDomainToBean(Role role);
	
	public Role mapBeanToDomain(RoleBean roleBean);
	
	public List<RoleBean> getRoles();
}
