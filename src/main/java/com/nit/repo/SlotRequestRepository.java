package com.nit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nit.entity.SlotRequest;

public interface SlotRequestRepository extends JpaRepository<SlotRequest, Long> {
	
	@Modifying
	@Query("update SlotRequest set status=:status where id=:id")
	void updateSlotRequestStatus(Long id,String status);
	
	@Query("select sr from SlotRequest sr inner join sr.patient pat where pat.patientName=:name")
	List<SlotRequest> findSlotStatusDataOfOnePatient(String name);
	
	@Query("select status,count(status) from SlotRequest group by status")
	List<Object[]> getSlotStatusAndCountGroupByStatus();

}
