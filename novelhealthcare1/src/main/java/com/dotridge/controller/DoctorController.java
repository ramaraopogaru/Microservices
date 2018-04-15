package com.dotridge.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dotridge.bean.AdminBean;
import com.dotridge.bean.DoctorBean;
import com.dotridge.service.DoctorService;
import com.dotridge.util.ServiceConstants;

@Controller
@PreAuthorize("hasAnyRole('superadmin','admin','doctor')")
//@PreAuthorize("hasRole('admin')")
@RequestMapping(value="/doctorManagement")
public class DoctorController 
{
	@Autowired
	DoctorService doctorService;

	
	@RequestMapping(value="/getAllDoctors")
	public String getAllDoctors(Model model)
	{
		
		List<DoctorBean> doctorBeanList = doctorService.getAllDoctors();
		int uiListSize = doctorBeanList.size();
		int recordsPerPage = Math.round((uiListSize / ServiceConstants.NUMBER_OF_RECORDS_PER_PAGE) + 1);
		List<Integer> pageBarList = new ArrayList<Integer>();
		for(int i = 0; i < recordsPerPage; i++)
		{
			pageBarList.add(i + 1);
		}
		model.addAttribute("uiPageBarList",pageBarList);
		model.addAttribute("uiDoctorBeanList",doctorBeanList);
		return "getDoctorBoard";
	}
	
	@RequestMapping(value="/getDoctorRegForm")
	public String getDoctorRegForm(Model model)
	{
		DoctorBean doctorBean = new DoctorBean();
		model.addAttribute("doctorBean",doctorBean);
		return "addDoctorFormDef";
	}
	
	@RequestMapping(value="/addDoctor", method=RequestMethod.POST)
	public String addDoctor(@ModelAttribute("doctorBean") DoctorBean doctorBean,Model model)
	{
		DoctorBean doctorBean2 = doctorService.createDoctor(doctorBean);
		if(doctorBean2.getDoctorId() > 0)
		{
			List<DoctorBean> doctorBeanList = doctorService.getAllDoctors();
			model.addAttribute(doctorBeanList);
			return "getDoctorBoard";
		}
		else
		{
			System.out.println("Doctor registration failed");
			return "getDoctorBoard";
		}
	}
	@RequestMapping(value="/editDoctor")
	public String editDoctor(HttpServletRequest request, Model model)
	{
		String id = request.getParameter("doctorId");
		int doctorId = Integer.valueOf(id);
		DoctorBean doctorBean = doctorService.getDoctorById(doctorId);
		model.addAttribute("doctorBean",doctorBean);
		return "editDoctorDef";
	}
	
	@RequestMapping(value="/updateDoctor")
	public String updateDoctor(@ModelAttribute("doctorBean") DoctorBean doctorBean, Model model)
	{
		if(doctorBean != null && doctorBean.getDoctorId() > 0)
		{
			doctorService.updateDoctor(doctorBean);
			List<DoctorBean> doctorBeans = doctorService.getAllDoctors();
			model.addAttribute("uiDoctorBeanList",doctorBeans);
			return "getDoctorBoard";
		}
		else
		{
			System.out.println("Doctor Updating is failed");
			return "getDoctorBoard";
		}
	}
	
	@RequestMapping(value="/deleteDoctor")
	public String deleteDoctor(HttpServletRequest request, Model model)
	{
		String id = request.getParameter("doctorId");
		boolean flag = doctorService.deleteDoctor(Integer.valueOf(id));
		if(flag == true)
		{
			List<DoctorBean> doctorBeans = doctorService.getAllDoctors();
			model.addAttribute("uiDoctorBeanList",doctorBeans);
			return "getDoctorBoard";
		}
		else
		{
			System.out.println("Doctor deletion failed");
			List<DoctorBean> doctorBeans = doctorService.getAllDoctors();
			model.addAttribute("uiDoctorBeanList",doctorBeans);
			return "getDoctorBoard";
		}
	}
	
	@RequestMapping(value="/searchDoctors")
	public String searchDoctors(HttpServletRequest request, Model model)
	{
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		if(searchKey != null && !searchKey.isEmpty() && searchValue != null && !searchValue.isEmpty())
		{
			if(searchKey.equalsIgnoreCase("FirstName"))
			{
				List<DoctorBean> doctorBeansList = doctorService.getDoctorByName(searchValue);
				if(doctorBeansList != null && !doctorBeansList.isEmpty())
				{
					model.addAttribute("uiDoctorBeanList",doctorBeansList);
					return "getDoctorBoard";
				}
				System.out.println("Error");
				return "getDoctorBoard";
			}
			else if(searchKey.equalsIgnoreCase("Email"))
			{
				List<DoctorBean> doctorBeansList = doctorService.getDoctorByEmail(searchValue);
				if(doctorBeansList != null && !doctorBeansList.isEmpty())
				{
					model.addAttribute("uiDoctorBeanList",doctorBeansList);
					return "getDoctorBoard";
				}
				System.out.println("Error");
				return "getDoctorBoard";
			}
			else if(searchKey.equalsIgnoreCase("Phone"))
			{
				List<DoctorBean> doctorBeansList = doctorService.getDoctorByPhone(Long.parseLong(searchValue));
				if(doctorBeansList != null && !doctorBeansList.isEmpty())
				{
					model.addAttribute("uiDoctorBeanList",doctorBeansList);
					return "getDoctorBoard";
				}
				System.out.println("Error");
				return "getDoctorBoard";
			}
			else if(searchKey.equalsIgnoreCase("IsActive"))
			{
				List<DoctorBean> doctorBeansList = doctorService.getDoctorByStatus(Boolean.valueOf(searchValue));
				if(doctorBeansList != null && !doctorBeansList.isEmpty())
				{
					model.addAttribute("uiDoctorBeanList",doctorBeansList);
					return "getDoctorBoard";
				}
				System.out.println("Error");
				return "getDoctorBoard";
			}
			System.out.println("Error");
			return "getDoctorBoard";
		}
		else
		{
			System.out.println("Error");
			return "getDoctorBoard";
		}
	}
	
	
	@RequestMapping(value="/getAllDoctorsByPagening")
	public String getAllDoctorsByPagening(HttpServletRequest request, Model model)
	{
		String currentPage = request.getParameter("currentPage");
		int currentPageInt = Integer.valueOf(currentPage);
		int noOfRecordsPerPage = ServiceConstants.NUMBER_OF_RECORDS_PER_PAGE;
		
		List<DoctorBean> doctorBeansList = doctorService.getAllDoctorsByPagining(currentPageInt, noOfRecordsPerPage);
		
		List<DoctorBean> uiDoctorList = doctorService.getAllDoctors();
		int uiListSize = uiDoctorList.size();
		int recordsPerPage = Math.round((uiListSize / ServiceConstants.NUMBER_OF_RECORDS_PER_PAGE) + 1);
		List<Integer> pageBarList = new ArrayList<Integer>();
		for(int i = 0; i < recordsPerPage; i++)
		{
			pageBarList.add(i + 1);
		}
		model.addAttribute("pageBarList",pageBarList);
		model.addAttribute("loop.count", currentPage);
		
		if(doctorBeansList != null && !doctorBeansList.isEmpty())
		{
			model.addAttribute("uiDoctorBeanList",doctorBeansList);
			return "getDoctorBoard";
		}
		System.out.println("Error");
		return "getDoctorBoard";
	}
}
