package com.nit.service;

import java.util.List;

import com.nit.entity.SlotRequest;

public interface ISlotRequestService {

	Long bookSlotRequest(SlotRequest req);
	List<SlotRequest> getAllSlotRequest();
	void updateSlotRequestStatus(Long id,String status);
	List<SlotRequest> getSlotStatusOfOnePatient(String name);
	SlotRequest getOneSlotRequest(Long id);
	long getSlotRequestCount();
	List<Object[]> getSlotStatusAndCount();
}
