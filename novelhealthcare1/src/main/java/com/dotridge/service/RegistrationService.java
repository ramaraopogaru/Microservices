package com.dotridge.service;

import com.dotridge.bean.UserProfileBean;
import com.dotridge.domain.UserProfile;

public interface RegistrationService 
{
	public UserProfile mapBeanToDomain(UserProfileBean userProfileBean);
	
	public UserProfileBean mapDomainToBean(UserProfile userProfile);
	
	public UserProfileBean createUser(UserProfileBean userProfileBean);
}
