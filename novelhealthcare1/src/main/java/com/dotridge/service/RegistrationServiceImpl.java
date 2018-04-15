package com.dotridge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dotridge.bean.UserProfileBean;
import com.dotridge.dao.RegistrationDao;
import com.dotridge.dao.RoleDao;
import com.dotridge.domain.UserProfile;
import com.dotridge.util.CustomPasswordEncoder;
import com.dotridge.util.LDAPUtil;

@Service
public class RegistrationServiceImpl implements RegistrationService {
	@Autowired
	private RegistrationDao registrationDao;

	
	@Autowired
	RoleDao roleDao;

	/*@Autowired
	private PasswordEncoder passwordEncoder;*/

	public UserProfile mapBeanToDomain(UserProfileBean userProfileBean) {
		UserProfile user = new UserProfile();
		user.setProfileId(userProfileBean.getProfileId());
		user.setFirstName(userProfileBean.getFirstName());
		user.setLastName(userProfileBean.getLastName());
		user.setEmail(userProfileBean.getEmail());
		user.setUserId(userProfileBean.getUserId());
		//user.setPassword(CustomPasswordEncoder.encodePassword(userProfileBean.getPassword()));
		user.setPassword(userProfileBean.getPassword());
		user.setLoginToken(CustomPasswordEncoder.encodePassword(userProfileBean.getConfirmPasswoord()));
		user.setPhoneNumber(userProfileBean.getPhoneNumber());
		user.setRole(roleDao.getRoleByUniqueName(userProfileBean.getRole()));
		return user;
	}

	public UserProfileBean mapDomainToBean(UserProfile userProfile) {
		UserProfileBean userProfileBean = new UserProfileBean();
		//userProfileBean.setProfileId(userProfile.getProfileId());
		userProfileBean.setFirstName(userProfile.getFirstName());
		userProfileBean.setLastName(userProfile.getLastName());
		userProfileBean.setEmail(userProfile.getEmail());
		userProfileBean.setUserId(userProfile.getUserId());
		userProfileBean.setPassword(userProfile.getPassword());
		userProfileBean.setConfirmPasswoord(userProfile.getConfirmPasswoord());
		userProfileBean.setPhoneNumber(userProfile.getPhoneNumber());
		userProfileBean.setRole(userProfile.getRole().getRoleName());
		return userProfileBean;
	}

	public UserProfileBean createUser(UserProfileBean userProfileBean) 
	{
		
		//UserProfile user = ldapDao.createUser(mapBeanToDomain(userProfileBean), userProfileBean.getRole());
		UserProfile user = LDAPUtil.createUser(mapBeanToDomain(userProfileBean), userProfileBean.getRole());
		//UserProfile user = registrationDao.createUser(mapBeanToDomain(userProfileBean));
		return mapDomainToBean(user);
	}

}
