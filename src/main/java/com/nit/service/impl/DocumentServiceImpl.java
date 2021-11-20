package com.nit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.entity.Document;
import com.nit.repo.DocumentRepository;
import com.nit.service.IDocumentService;

@Service
public class DocumentServiceImpl implements IDocumentService {

	@Autowired
	private DocumentRepository repo;
	
	@Override
	public List<Object[]> getDocumentIdAndName() {
		
		return repo.getDocumentIdAndName();
	}

	@Override
	public Long saveDocumentData(Document doc) {
		
		return repo.save(doc).getId();
	}

	@Override
	public Document getDocumentById(Long id) {
		
		return repo.findById(id).get();
	}

	@Override
	public void deleteDocumentById(Long id) {
		repo.deleteById(id);
		
	}
}
