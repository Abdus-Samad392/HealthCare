package com.nit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nit.entity.Specialization;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {

	@Query("select count(specCode) from Specialization where specCode=:specCode")
	Integer getSpecCodeCount(String specCode);
	@Query("select count(specName) from Specialization where specName=:specName")
	Integer getSpecNameCount(String specName);
	@Query("select count(specCode) from Specialization where specCode=:specCode and id!=:id")
	Integer getSpecCodeCountWithId(String specCode,Long id);
	@Query("select count(specName) from Specialization where specName=:specName and id!=:id")
	Integer getSpecNameCountWithId(String specName,Long id);
	@Query("select id,specName from Specialization")
	List<Object[]> getSpecIdAndSpecName();

}
