package com.nit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nit.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

	@Query("SELECT id,documentName FROM Document")
	List<Object[]> getDocumentIdAndName();
}
