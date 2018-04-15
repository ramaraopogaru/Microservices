package com.dotridge.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dotridge.bean.RoleBean;
import com.dotridge.dao.RoleDao;
import com.dotridge.domain.Role;

@Service
public class RoleServiceImpl implements RoleService
{
	@Autowired
	private RoleDao roleDao;
	
	public RoleBean mapDomainToBean(Role role)
	{
		RoleBean roleBean = new RoleBean();
		roleBean.setRoleName(role.getRoleName());
		return roleBean;
	}
	
	public Role mapBeanToDomain(RoleBean roleBean)
	{
		Role role = new Role();
		role.setRoleName(roleBean.getRoleName());
		return role;
	}
	
	public List<RoleBean> getRoles() 
	{
		List<Role> roles = roleDao.getRoles();
		List<RoleBean> roleBeans = new ArrayList<RoleBean>();
		if(roles != null && !roles.isEmpty())
		{
			for(Role role : roles)
			{
				roleBeans.add(mapDomainToBean(role));
			}
		}
		return roleBeans;
	}

}
