package com.nit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class Document {

	@Id
	@GeneratedValue
	@Column(name = "document_id_col")
	private Long id;
	@Column(name="document_name_col")
	private String documentName;
	@Lob
	@Column(name="document_data_col")
	private byte[] documentData;
	
}
