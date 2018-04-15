package com.dotridge.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dotridge.bean.AdminBean;
import com.dotridge.dao.AdminDao;
import com.dotridge.dao.HospitalDao;
import com.dotridge.domain.Admin;

@Service
public class AdminServiceImpl implements AdminService 
{
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private HospitalDao hospitalDao;

	public Admin mapBeanToDomain(AdminBean adminBean) 
	{
		Date date = new Date();
		
		Admin admin = new Admin();
		admin.setHospital(hospitalDao.getHospitalByUniqueName(adminBean.getHospitalName()));
		admin.setAdminId(adminBean.getAdminId());
		admin.setFirstName(adminBean.getFirstName());
		admin.setMiddleName(adminBean.getMiddleName());
		admin.setLastName(adminBean.getLastName());
		admin.setEmail(adminBean.getEmail());
		admin.setPassword(adminBean.getPassword());
		admin.setPhone(adminBean.getPhone());
		admin.setProfileDocuments(adminBean.getProfileDocuments());
		admin.setStatus(adminBean.isStatus());
		admin.setCreatedBy("Yugandhar");
		admin.setCreatedDate(date);
		admin.setModifiedBy("Yugandhar");
		admin.setModifiedDate(date);
		return admin;
	}

	public AdminBean mapDomainToBean(Admin admin) 
	{
		AdminBean adminBean = new AdminBean();
		adminBean.setHospitalName(admin.getHospital().getHospitalName());
		adminBean.setAdminId(admin.getAdminId());
		adminBean.setFirstName(admin.getFirstName());
		adminBean.setMiddleName(admin.getMiddleName());
		adminBean.setLastName(admin.getLastName());
		adminBean.setEmail(admin.getEmail());
		adminBean.setPassword(admin.getPassword());
		adminBean.setPhone(admin.getPhone());
		adminBean.setProfileDocuments(admin.getProfileDocuments());
		adminBean.setStatus(admin.isStatus());
		return adminBean;
	}

	public AdminBean createAdmin(AdminBean adminBean) 
	{
		Admin adminDomain = mapBeanToDomain(adminBean);
		System.out.println(adminDomain.toString());
		AdminBean adminBean2 = mapDomainToBean(adminDao.createAdmin(adminDomain));
		return adminBean2;
	}

	public AdminBean updateAdmin(AdminBean adminBean) 
	{
		Admin admin = adminDao.updateAdmin(mapBeanToDomain(adminBean));
		AdminBean adminBean2 = mapDomainToBean(admin);
		return adminBean2;
	}

	public AdminBean getAdminById(int id) 
	{
		Admin admin = adminDao.getAdminById(id);
		AdminBean adminBean = mapDomainToBean(admin);
		return adminBean;
	}

	public List<AdminBean> getAdminByName(String adminName) 
	{
		List<Admin> admins = adminDao.getAdminByName(adminName);
		List<AdminBean> adminBeans = null;
		if(admins != null && !admins.isEmpty())
		{
			adminBeans = new ArrayList<AdminBean>();
			for(Admin admin : admins)
			{
				adminBeans.add(mapDomainToBean(admin));
			}
			return adminBeans;
		}
		return null;
	}

	public List<AdminBean> getAdminByEmail(String email) 
	{
		List<Admin> admins = adminDao.getAdminByEmail(email);
		List<AdminBean> adminBeans = null;
		if(admins != null && !admins.isEmpty())
		{
			adminBeans = new ArrayList<AdminBean>();
			for(Admin admin : admins)
			{
				adminBeans.add(mapDomainToBean(admin));
			}
			return adminBeans;
		}
		return null;
	}

	public List<AdminBean> getAdminByPhone(long phoneNumber) 
	{
		List<Admin> admins = adminDao.getAdminByPhone(phoneNumber);
		List<AdminBean> adminBeans = null;
		if(admins != null && !admins.isEmpty())
		{
			adminBeans = new ArrayList<AdminBean>();
			for(Admin admin : admins)
			{
				adminBeans.add(mapDomainToBean(admin));
			}
			return adminBeans;
		}
		return null;
	}

	public List<AdminBean> getAdminByStatus(Boolean status) 
	{
		List<Admin> admins = adminDao.getAdminByStatus(status);
		List<AdminBean> adminBeans = null;
		if(admins != null && !admins.isEmpty())
		{
			adminBeans = new ArrayList<AdminBean>();
			for(Admin admin : admins)
			{
				adminBeans.add(mapDomainToBean(admin));
			}
			return adminBeans;
		}
		return null;
	}

	public List<AdminBean> getAllAdmins() 
	{
		List<Admin> adminsList = adminDao.getAllAdmins();
		List<AdminBean> adminBeansList = null;
		if(adminsList != null && !adminsList.isEmpty())
		{
			adminBeansList = new ArrayList<AdminBean>();
			for(Admin adminDomain : adminsList)
			{
				AdminBean adminBean = mapDomainToBean(adminDomain);
				adminBeansList.add(adminBean);
			}
			return adminBeansList;
		}
		else
			throw new RuntimeException("No Hospital Available");
	}

	public boolean deleteAdmin(int id) 
	{
		boolean flag = adminDao.deleteAdmin(id);
		return flag;
	}

	public List<AdminBean> getAllAdminsByPagining(int currentPage, int numberOfRecords) 
	{
		List<Admin> adminsList = adminDao.getAllAdminsByPagining(currentPage, numberOfRecords);
		List<AdminBean> uiAdminList = null;
		if(adminsList != null && !adminsList.isEmpty())
		{
			uiAdminList = new ArrayList<AdminBean>();
			for(Admin adminDomain : adminsList)
			{
				AdminBean adminBean = mapDomainToBean(adminDomain);
				uiAdminList.add(adminBean);
			}
			return uiAdminList;
		}
		else
			throw new RuntimeException("No Hospital Available");
	}
}