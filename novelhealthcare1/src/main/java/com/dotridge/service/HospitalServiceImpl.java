package com.dotridge.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dotridge.bean.HospitalBean;
import com.dotridge.dao.HospitalDao;
import com.dotridge.domain.Hospital;


@Service
public class HospitalServiceImpl implements HospitalService 
{
	@Autowired
	private HospitalDao hospitalDao;
	public Hospital mapBeanToDomain(HospitalBean hospitalBean) 
	{
		Date date = new Date();
		
		Hospital hospital = new Hospital();
		hospital.setHospitalId(hospitalBean.getHospitalId());
		hospital.setHospitalName(hospitalBean.getHospitalName());
		hospital.setAddress1(hospitalBean.getAddress1());
		hospital.setAddress2(hospitalBean.getAddress2());
		hospital.setCity(hospitalBean.getCity());
		hospital.setState(hospitalBean.getState());
		hospital.setZipCode(hospitalBean.getZipCode());
		hospital.setEmail(hospitalBean.getEmail());
		hospital.setPhone(hospitalBean.getPhone());
		hospital.setFax(hospitalBean.getFax());
		hospital.setLogo(hospitalBean.getLogo());
		hospital.setRegistrationDocument(hospitalBean.getRegistrationDocument());
		hospital.setStatus(hospitalBean.isStatus());
		hospital.setCreatedBy("Yugandhar");
		hospital.setCreatedDate(date);
		hospital.setModifiedBy("Yugandhar");
		hospital.setModifiedDate(date);
		return hospital;
	}

	public HospitalBean mapDomainToBean(Hospital hospitalDomain) 
	{
		HospitalBean hospitalBean = new HospitalBean();
		hospitalBean.setHospitalId(hospitalDomain.getHospitalId());
		hospitalBean.setHospitalName(hospitalDomain.getHospitalName());
		hospitalBean.setAddress1(hospitalDomain.getAddress1());
		hospitalBean.setAddress2(hospitalDomain.getAddress2());
		hospitalBean.setCity(hospitalDomain.getCity());
		hospitalBean.setState(hospitalDomain.getState());
		hospitalBean.setZipCode(hospitalDomain.getZipCode());
		hospitalBean.setEmail(hospitalDomain.getEmail());
		hospitalBean.setPhone(hospitalDomain.getPhone());
		hospitalBean.setFax(hospitalDomain.getFax());
		hospitalBean.setLogo(hospitalDomain.getLogo());
		hospitalBean.setRegistrationDocument(hospitalDomain.getRegistrationDocument());
		hospitalBean.setStatus(hospitalDomain.isStatus());
		return hospitalBean;
	}

	/*public HospitalBean createHospital(Hospital hospitalBean) 
	{
		Hospital hospital = hospitalDao.createHospital(mapBeanToDomain(hospitalBean));
		HospitalBean hospitalBean2 = mapDomainToBean(hospital);
		return hospitalBean2;
	}*/

	public HospitalBean updateHospital(HospitalBean hospitalBean) 
	{
		Hospital hospital = mapBeanToDomain(hospitalBean);
		HospitalBean hospitalBean2 = mapDomainToBean(hospitalDao.updateHospital(hospital));
		return hospitalBean2;
	}

	public HospitalBean getHospitalById(int id) 
	{
		Hospital hospital = hospitalDao.getHospitalById(id);
		HospitalBean hospitalBean = mapDomainToBean(hospital);
		return hospitalBean;
	}

	public List<HospitalBean> getHospitalByName(String hospitalName) 
	{
		List<Hospital> hospitalList = hospitalDao.getHospitalByName(hospitalName);
		List<HospitalBean> uiHospitalList = null;
		if(hospitalList != null && !hospitalList.isEmpty())
		{
			uiHospitalList = new ArrayList<HospitalBean>();
			for(Hospital hospitalDomain : hospitalList)
			{
				HospitalBean hospitalBean = mapDomainToBean(hospitalDomain);
				uiHospitalList.add(hospitalBean);
			}
			return uiHospitalList;
		}
		return null;
	}
	
	public List<HospitalBean> getHospitalByEmail(String email)
	{
		List<Hospital> hospitalDomainList = hospitalDao.getHospitalByEmail(email);
		List<HospitalBean> hospitalBeansList = null;
		
		if(hospitalDomainList != null && !hospitalDomainList.isEmpty())
		{
			hospitalBeansList = new ArrayList<HospitalBean>();
			for(Hospital hospitalDomain : hospitalDomainList)
			{
				HospitalBean hospitalBean = mapDomainToBean(hospitalDomain);
				hospitalBeansList.add(hospitalBean);
			}
			return hospitalBeansList;
		}
		return null;
	}
	
	public List<HospitalBean> getHospitalByAddress1(String address1)
	{
		List<Hospital> hospitalDomainList = hospitalDao.getHospitalByAddress1(address1);
		List<HospitalBean> hospitalBeansList = null;
		
		if(hospitalDomainList != null && !hospitalDomainList.isEmpty())
		{
			hospitalBeansList = new ArrayList<HospitalBean>();
			for(Hospital hospitalDomain : hospitalDomainList)
			{
				HospitalBean hospitalBean = mapDomainToBean(hospitalDomain);
				hospitalBeansList.add(hospitalBean);
			}
			return hospitalBeansList;
		}
		return null;
	}
	
	public List<HospitalBean> getHospitalByPhone(long phoneNumber)
	{
		List<Hospital> hospitalDomainList = hospitalDao.getHospitalByPhone(phoneNumber);
		List<HospitalBean> hospitalBeansList = null;
		if(hospitalDomainList != null && !hospitalDomainList.isEmpty())
		{
			hospitalBeansList = new ArrayList<HospitalBean>();
			for(Hospital hospitalDomain : hospitalDomainList)
			{
				HospitalBean hospitalBean = mapDomainToBean(hospitalDomain);
				hospitalBeansList.add(hospitalBean);
			}
			return hospitalBeansList;
		}
		return null;
	}
	
	public List<HospitalBean> getHospitalByStatus(Boolean status)
	{
		List<Hospital> hospitalDomainList = hospitalDao.getHospitalByStatus(status);
		List<HospitalBean> hospitalBeansList = null;
		if(hospitalDomainList != null && !hospitalDomainList.isEmpty())
		{
			hospitalBeansList = new ArrayList<HospitalBean>();
			for(Hospital hospitalDomain : hospitalDomainList)
			{
				HospitalBean hospitalBean = mapDomainToBean(hospitalDomain);
				hospitalBeansList.add(hospitalBean);
			}
			return hospitalBeansList;
		}
		return null;
	}

	public List<HospitalBean> getAllHospitals() 
	{
		List<Hospital> hospitalList = hospitalDao.getAllHospitals();
		List<HospitalBean> uiHospitalList = null;
		if(hospitalList != null && !hospitalList.isEmpty())
		{
			uiHospitalList = new ArrayList<HospitalBean>();
			for(Hospital hospitalDomain : hospitalList)
			{
				HospitalBean hospitalBean = mapDomainToBean(hospitalDomain);
				uiHospitalList.add(hospitalBean);
			}
			return uiHospitalList;
		}
		else
			throw new RuntimeException("No Hospital Available");
	}

	public boolean deleteHospital(int id) 
	{
		boolean flag = hospitalDao.deleteHospital(id);
		return flag;
	}

	public List<HospitalBean> getAllHospitalsByPagining(int currentPage, int numberOfRecords) 
	{
		List<Hospital> hospitalList = hospitalDao.getAllHospitalsByPagining(currentPage, numberOfRecords);
		List<HospitalBean> uiHospitalList = null;
		if(hospitalList != null && !hospitalList.isEmpty())
		{
			uiHospitalList = new ArrayList<HospitalBean>();
			for(Hospital hospitalDomain : hospitalList)
			{
				HospitalBean hospitalBean = mapDomainToBean(hospitalDomain);
				uiHospitalList.add(hospitalBean);
			}
			return uiHospitalList;
		}
		else
			throw new RuntimeException("No Hospital Available");
	}

	public HospitalBean createHospital(HospitalBean hospitalBean) 
	{
		Hospital hospital = mapBeanToDomain(hospitalBean);
		HospitalBean hospitalBean2 = mapDomainToBean(hospitalDao.createHospital(hospital));
		return hospitalBean2;
	}

}
