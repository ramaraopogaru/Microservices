package com.dotridge.dao;

import java.util.List;

import com.dotridge.domain.Role;

public interface RoleDao 
{
	public List<Role> getRoles();
	
	public Role getRoleByUniqueName(String role);
}
