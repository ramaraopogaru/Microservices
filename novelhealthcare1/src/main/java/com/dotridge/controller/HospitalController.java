package com.dotridge.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dotridge.bean.HospitalBean;
import com.dotridge.service.HospitalService;
import com.dotridge.util.ServiceConstants;

@Controller
@RequestMapping(value="/hospitalManagement")
public class HospitalController 
{
	@Autowired
	private HospitalService hospitalService;
	
	@RequestMapping(value="/getHomePage")
	public String getHomePage()
	{
		return "getHomeBoard";
	}
	
	@RequestMapping(value="/getAllHospitals")
	public String viewAllHospitals(Model model)
	{
		try
		{
			List<HospitalBean> uiHospitalList = hospitalService.getAllHospitals();
			int uiListSize = uiHospitalList.size();
			int recordsPerPage = Math.round((uiListSize / ServiceConstants.NUMBER_OF_RECORDS_PER_PAGE) + 1);
			List<Integer> pageBarList = new ArrayList<Integer>();
			for(int i = 0; i < recordsPerPage; i++)
			{
				pageBarList.add(i + 1);
			}
			System.out.println("recordsPerPage : " + recordsPerPage);
			System.out.println("pageBarList : " + pageBarList.size());
			model.addAttribute("pageBarList",pageBarList);
			model.addAttribute("uiHospitalList" ,uiHospitalList);
			//System.out.println(uiHospitalList);
			return "getHospitalBoard";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/getHospitalRegForm")
	public String getHospitalRegistrationForm(Model model)
	{
		HospitalBean hospitalBean = new HospitalBean();
		model.addAttribute("hospitalName",hospitalBean);
		return "addHospitalFormDef";
	}
	
	@RequestMapping(value="/addHospital", method=RequestMethod.POST)
	public String addHospital(@ModelAttribute("hospitalName") HospitalBean hospitalBean, Model model)
	{
		System.out.println(hospitalBean.toString());
		HospitalBean hospitalBean2 = hospitalService.createHospital(hospitalBean);
		if(hospitalBean2.getHospitalId() > 0)
		{
				List<HospitalBean> uiHospitalList = hospitalService.getAllHospitals();
				model.addAttribute("uiHospitalList" ,uiHospitalList);
				//System.out.println(uiHospitalList);
				return "getHospitalBoard";
		}
		else
		{
			System.out.println("Hospital Adding is failed");
			return "getHospitalBoard";
		}	
	}
	
	@RequestMapping(value="/editHospital")
	public String editHospital(HttpServletRequest request, Model model)
	{
		String hospitalId = request.getParameter("hospId");
		HospitalBean hospitalBean = hospitalService.getHospitalById(Integer.valueOf(hospitalId));
		model.addAttribute("hospitalBean",hospitalBean);
		System.out.println(hospitalBean.getHospitalId());
		return "editHospitalDef";
	}
	
	@RequestMapping(value="/updateHospital",method=RequestMethod.POST)
	public String updateHospital(@ModelAttribute("hospitalBean")HospitalBean hospitalBean, Model model)
	{
		System.out.println(hospitalBean.getHospitalId());
		System.out.println(hospitalBean.getHospitalName());
		System.out.println(hospitalBean.getState());
		HospitalBean hospitalBean2 = hospitalBean;
		if(hospitalBean2 != null && hospitalBean.getHospitalId() > 0)
		{
			hospitalService.updateHospital(hospitalBean2);
			List<HospitalBean> uiHospitalList = hospitalService.getAllHospitals();
			model.addAttribute("uiHospitalList" ,uiHospitalList);
			//System.out.println(uiHospitalList);
			return "getHospitalBoard";
		}
		else
		{
			System.out.println("Hospital Adding is failed");
			return "getHospitalBoard";
		}		
	}
	
	@RequestMapping(value="/deleteHospital")
	public String deleteHospital(HttpServletRequest request,Model model)
	{
		String hospitalId = request.getParameter("hospId");
		boolean flag = hospitalService.deleteHospital(Integer.valueOf(hospitalId));
		if(flag == true)
		{
			System.out.println("One row deleted successfully");
			List<HospitalBean> uiHospitalList = hospitalService.getAllHospitals();
			model.addAttribute("uiHospitalList",uiHospitalList);
			return "getHospitalBoard";
		}
		else
		{
			System.out.println("deletion failed");
			List<HospitalBean> uiHospitalList = hospitalService.getAllHospitals();
			model.addAttribute(uiHospitalList);
			return "getHospitalBoard";
		}
	}
	
	@RequestMapping(value="/searchHospitals")
	public String searchHospitals(HttpServletRequest request, Model model)
	{
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		if(searchKey != null && !searchKey.isEmpty() && searchValue != null && !searchValue.isEmpty())
		{
			if(searchKey.equalsIgnoreCase("HospitalName"))
			{
				List<HospitalBean> hospitalsList = hospitalService.getHospitalByName(searchValue);
				if(hospitalsList != null && !hospitalsList.isEmpty())
				{
					model.addAttribute("uiHospitalList",hospitalsList);
					return "getHospitalBoard";
				}
				System.out.println("Error");
				return "getHospitalBoard";
			}
			else if(searchKey.equalsIgnoreCase("Email"))
			{
				List<HospitalBean> hospitalsList = hospitalService.getHospitalByEmail(searchValue);
				if(hospitalsList != null && !hospitalsList.isEmpty())
				{
					model.addAttribute("uiHospitalList",hospitalsList);
					return "getHospitalBoard";
				}
				System.out.println("Error");
				return "getHospitalBoard";
			}
			else if(searchKey.equalsIgnoreCase("Address1"))
			{
				List<HospitalBean> hospitalsList = hospitalService.getHospitalByAddress1(searchValue);
				if(hospitalsList != null && !hospitalsList.isEmpty())
				{
					model.addAttribute("uiHospitalList",hospitalsList);
					return "getHospitalBoard";
				}
				System.out.println("Error");
				return "getHospitalBoard";
			}
			else if(searchKey.equalsIgnoreCase("Phone"))
			{
				List<HospitalBean> hospitalsList = hospitalService.getHospitalByPhone(Long.parseLong(searchValue));
				if(hospitalsList != null && !hospitalsList.isEmpty())
				{
					model.addAttribute("uiHospitalList",hospitalsList);
					return "getHospitalBoard";
				}
				System.out.println("Error");
				return "getHospitalBoard";
			}
			else if(searchKey.equalsIgnoreCase("IsActive"))
			{
				List<HospitalBean> hospitalsList = hospitalService.getHospitalByStatus(Boolean.valueOf(searchValue));
				if(hospitalsList != null && !hospitalsList.isEmpty())
				{
					model.addAttribute("uiHospitalList",hospitalsList);
					return "getHospitalBoard";
				}
				System.out.println("Error");
				return "getHospitalBoard";
			}
			System.out.println("Error");
			return "getHospitalBoard";
		}
		else
		{
			System.out.println("Error");
			return "getHospitalBoard";
		}
	}
	
	@RequestMapping(value="/getAllHospitalsByPagening")
	public String getAllHospitalsByPagening(HttpServletRequest request, Model model)
	{
		String currentPage = request.getParameter("currentPage");
		int currentPageInt = Integer.valueOf(currentPage);
		int noOfRecordsPerPage = ServiceConstants.NUMBER_OF_RECORDS_PER_PAGE;
		
		List<HospitalBean> hospitalsList = hospitalService.getAllHospitalsByPagining(currentPageInt, noOfRecordsPerPage);
		
		List<HospitalBean> uiHospitalList = hospitalService.getAllHospitals();
		int uiListSize = uiHospitalList.size();
		int recordsPerPage = Math.round((uiListSize / ServiceConstants.NUMBER_OF_RECORDS_PER_PAGE) + 1);
		List<Integer> pageBarList = new ArrayList<Integer>();
		for(int i = 0; i < recordsPerPage; i++)
		{
			pageBarList.add(i + 1);
		}
		model.addAttribute("pageBarList",pageBarList);
		model.addAttribute("loop.count", currentPage);
		
		if(hospitalsList != null && !hospitalsList.isEmpty())
		{
			model.addAttribute("uiHospitalList",hospitalsList);
			return "getHospitalBoard";
		}
		System.out.println("Error");
		return "getHospitalBoard";
	}
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public String errorPage(){
		System.out.println(" Exception Occured In ::"+getClass().getName());
		return "error";
	}
	
}
