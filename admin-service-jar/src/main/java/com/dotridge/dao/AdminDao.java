package com.dotridge.dao;

import java.util.List;

import com.dotridge.domain.Admin;

public interface AdminDao 
{
	public Admin createAdmin(Admin adminDomain);
	
	public Admin updateAdmin(Admin adminDomain);
	
	public Admin getAdminById(int id);
	
	public List<Admin> getAdminByName(String firstName);
	
	public List<Admin> getAdminByEmail(String email);
	
	public List<Admin> getAdminByPhone(long phoneNumber);
	
	public List<Admin> getAdminByStatus(Boolean status);
	
	public List<Admin> getAllAdmins();
	
	public boolean deleteAdmin(int id);
	
	public List<Admin> getAllAdminsByPagining(int currentPage, int numberOfRecords);
}
