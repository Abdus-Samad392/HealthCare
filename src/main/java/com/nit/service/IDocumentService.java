package com.nit.service;

import java.util.List;

import com.nit.entity.Document;

public interface IDocumentService {

	List<Object[]> getDocumentIdAndName(); 
	Long saveDocumentData(Document doc);
	Document getDocumentById(Long id);
	void deleteDocumentById(Long id);
}
