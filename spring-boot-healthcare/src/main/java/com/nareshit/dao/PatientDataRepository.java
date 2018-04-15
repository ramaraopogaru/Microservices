package com.nareshit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nareshit.domain.Patient;

public interface PatientDataRepository extends CrudRepository<Patient, Integer> {

	@Query("from Patient p where p.lastName like %:pnamePattern%")
	public List<Patient> getAllPatientsByname(@Param("pnamePattern")String name);
}
