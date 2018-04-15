package com.dotridge.dao;

import java.util.List;

import com.dotridge.domain.Module;
import com.dotridge.domain.UserProfile;

public interface RegistrationDao 
{
	public UserProfile createUser(UserProfile user);

	UserProfile getUserByName(String username,String password);
	public List<Module> getAllModulesOfRole(int roleId);
}
