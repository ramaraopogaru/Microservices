package com.dotridge.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dotridge.bean.AdminBean;
import com.dotridge.bean.HospitalBean;
import com.dotridge.service.AdminService;
import com.dotridge.service.HospitalService;
import com.dotridge.util.ServiceConstants;

@Controller
@RequestMapping(value="/adminManagement")
public class AdminController
{
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private HospitalService hospitalService;
	
	@RequestMapping(value="/getAllUsers")
	public String viewAllAdmins(Model model)
	{
		
		try
		{
			List<AdminBean> adminBeansList = adminService.getAllAdmins();
			int uiListSize = adminBeansList.size();
			int recordsPerPage = Math.round((uiListSize / ServiceConstants.NUMBER_OF_RECORDS_PER_PAGE) + 1);
			List<Integer> pageBarList = new ArrayList<Integer>();
			for(int i = 0; i < recordsPerPage; i++)
			{
				pageBarList.add(i + 1);
			}
			//System.out.println("recordsPerPage : " + recordsPerPage);
			//System.out.println("pageBarList : " + pageBarList.size());
			model.addAttribute("pageBarList",pageBarList);
			model.addAttribute("uiAdminsList",adminBeansList);
			return "getUsersBoard";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/getAdminRegForm")
	public String getHospitalRegistrationForm(Model model)
	{
		AdminBean adminBean = new AdminBean();
		List<HospitalBean> hospitalBeanList = hospitalService.getAllHospitals();
		Set<String> hospitalNameList = new HashSet<String>();
		for(HospitalBean hospitalBeans : hospitalBeanList)
		{
			hospitalNameList.add(hospitalBeans.getHospitalName());
		}
		model.addAttribute("hospitalNameList",hospitalNameList);
		model.addAttribute("adminBean",adminBean);
		return "addAdminFormDef";
	}
	
	@RequestMapping(value="/addAdmin", method=RequestMethod.POST)
	public String addAdmin(@ModelAttribute("adminBean") AdminBean adminBean, Model model)
	{
		//System.out.println(adminBean.toString());
		AdminBean adminBean2 = adminService.createAdmin(adminBean);
		if(adminBean2.getAdminId() > 0)
		{
				List<AdminBean> uiAdminsList = adminService.getAllAdmins();
				model.addAttribute("uiAdminsList" ,uiAdminsList);
				//System.out.println(uiHospitalList);
				return "getUsersBoard";
		}
		else
		{
			System.out.println("Hospital Adding is failed");
			return "getUsersBoard";
		}	
	}
	
	@RequestMapping(value="/editAdmin")
	public String editAdmin(HttpServletRequest request, Model model)
	{
		String id = request.getParameter("adminId");
		int adminId = Integer.valueOf(id);
		AdminBean adminBean = adminService.getAdminById(adminId);
		List<HospitalBean> hospitalBeans = hospitalService.getAllHospitals(); 
		Set<String> hospitalNameList = new HashSet<String>();
		for(HospitalBean hospitalBean : hospitalBeans)
		{
			hospitalNameList.add(hospitalBean.getHospitalName());
		}
		model.addAttribute("hospitalNameList",hospitalNameList);
		model.addAttribute("adminBean",adminBean);
		return "editAdminDef";
	}
	
	@RequestMapping(value="/updateAdmin", method=RequestMethod.POST)
	public String updateAdmin(@ModelAttribute("adminBean")AdminBean adminBean, Model model)
	{
		if(adminBean != null && adminBean.getAdminId() > 0)
		{
			adminService.updateAdmin(adminBean);
			List<AdminBean> adminBeans = adminService.getAllAdmins();
			model.addAttribute("uiAdminsList",adminBeans);
			return "getUsersBoard";
		}
		else
		{
			System.out.println("Admin Updating is failed");
			return "getUsersBoard";
		}
	}
	
	@RequestMapping(value="/deleteAdmin")
	public String deleteAdmin(HttpServletRequest request,Model model)
	{
		String id = request.getParameter("adminId");
		boolean flag = adminService.deleteAdmin(Integer.valueOf(id));
		if(flag == true)
		{
			List<AdminBean> adminBeans = adminService.getAllAdmins();
			model.addAttribute("uiAdminsList",adminBeans);
			return "getUsersBoard";
		}
		else
		{
			System.out.println("Admin deletion failed");
			List<AdminBean> adminBeans = adminService.getAllAdmins();
			model.addAttribute("uiAdminsList",adminBeans);
			return "getUsersBoard";
		}
	}
	
	@RequestMapping(value="/searchAdmins")
	public String searchAdmins(HttpServletRequest request, Model model)
	{
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		if(searchKey != null && !searchKey.isEmpty() && searchValue != null && !searchValue.isEmpty())
		{
			if(searchKey.equalsIgnoreCase("FirstName"))
			{
				List<AdminBean> adminssList = adminService.getAdminByName(searchValue);
				if(adminssList != null && !adminssList.isEmpty())
				{
					model.addAttribute("uiAdminsList",adminssList);
					return "getUsersBoard";
				}
				System.out.println("Error");
				return "getUsersBoard";
			}
			else if(searchKey.equalsIgnoreCase("Email"))
			{
				List<AdminBean> adminsList = adminService.getAdminByEmail(searchValue);
				if(adminsList != null && !adminsList.isEmpty())
				{
					model.addAttribute("uiAdminsList",adminsList);
					return "getUsersBoard";
				}
				System.out.println("Error");
				return "getUsersBoard";
			}
			else if(searchKey.equalsIgnoreCase("Phone"))
			{
				List<AdminBean> adminsList = adminService.getAdminByPhone(Long.parseLong(searchValue));
				if(adminsList != null && !adminsList.isEmpty())
				{
					model.addAttribute("uiAdminsList",adminsList);
					return "getUsersBoard";
				}
				System.out.println("Error");
				return "getUsersBoard";
			}
			else if(searchKey.equalsIgnoreCase("IsActive"))
			{
				List<AdminBean> adminsList = adminService.getAdminByStatus(Boolean.valueOf(searchValue));
				if(adminsList != null && !adminsList.isEmpty())
				{
					model.addAttribute("uiAdminsList",adminsList);
					return "getUsersBoard";
				}
				System.out.println("Error");
				return "getUsersBoard";
			}
			System.out.println("Error");
			return "getUsersBoard";
		}
		else
		{
			System.out.println("Error");
			return "getUsersBoard";
		}
	}
	
	@RequestMapping(value="/getAllAdminsByPagening")
	public String getAllAdminsByPagening(HttpServletRequest request, Model model)
	{
		String currentPage = request.getParameter("currentPage");
		int currentPageInt = Integer.valueOf(currentPage);
		int noOfRecordsPerPage = ServiceConstants.NUMBER_OF_RECORDS_PER_PAGE;
		
		List<AdminBean> adminsList = adminService.getAllAdminsByPagining(currentPageInt, noOfRecordsPerPage);
		
		List<AdminBean> uiAdminList = adminService.getAllAdmins();
		int uiListSize = uiAdminList.size();
		int recordsPerPage = Math.round((uiListSize / ServiceConstants.NUMBER_OF_RECORDS_PER_PAGE) + 1);
		List<Integer> pageBarList = new ArrayList<Integer>();
		for(int i = 0; i < recordsPerPage; i++)
		{
			pageBarList.add(i + 1);
		}
		model.addAttribute("pageBarList",pageBarList);
		model.addAttribute("loop.count", currentPage);
		
		if(adminsList != null && !adminsList.isEmpty())
		{
			model.addAttribute("uiAdminsList",adminsList);
			return "getUsersBoard";
		}
		System.out.println("Error");
		return "getUsersBoard";
	}
}
