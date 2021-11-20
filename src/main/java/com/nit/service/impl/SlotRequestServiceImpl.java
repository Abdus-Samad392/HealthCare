package com.nit.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.entity.SlotRequest;
import com.nit.repo.SlotRequestRepository;
import com.nit.service.ISlotRequestService;

@Service
public class SlotRequestServiceImpl implements ISlotRequestService {

	@Autowired
	private SlotRequestRepository repo;
	
	@Override
	public Long bookSlotRequest(SlotRequest req) {
		return repo.save(req).getId();
	}

	@Override
	public List<SlotRequest> getAllSlotRequest() {
		
		return repo.findAll();
	}
	
	@Transactional
	@Override
	public void updateSlotRequestStatus(Long id, String status) {
		repo.updateSlotRequestStatus(id, status);
		
	}
	
	@Override
	public List<SlotRequest> getSlotStatusOfOnePatient(String name) {
		
		return repo.findSlotStatusDataOfOnePatient(name);
	}
	
	@Override
	public SlotRequest getOneSlotRequest(Long id) {
		
		return repo.findById(id).get();
	}

	@Override
	public long getSlotRequestCount() {
		
		return repo.count();
	}
	
	@Override
	public List<Object[]> getSlotStatusAndCount() {
	
		return repo.getSlotStatusAndCountGroupByStatus();
	}

}
