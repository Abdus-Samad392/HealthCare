package com.nit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="SlotRequest",uniqueConstraints = @UniqueConstraint(columnNames = {"app_id_col","pat_id_col"}))
public class SlotRequest {

	@Id
	@GenericGenerator(name="gen1",strategy = "increment")
	@GeneratedValue(generator = "gen1")
	@Column(name="slot_req_id_col")
	private Long id;
	@OneToOne
	@JoinColumn(name = "app_id_col")
	private Appointment app;
	@OneToOne
	@JoinColumn(name="pat_id_col")
	private Patient patient;
	@Column(name="status_col",nullable = true)
	private String status;
	
}
