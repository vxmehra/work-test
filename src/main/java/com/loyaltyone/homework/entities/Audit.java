package com.loyaltyone.homework.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnore;


@MappedSuperclass
public class Audit {

	@JsonIgnore
	@Column(name = "created_on", nullable=false, updatable=false)
	protected LocalDateTime createdOn;

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	@PrePersist
	public void prePersist() {
		this.createdOn = LocalDateTime.now();
	}
	
	
	
	
	
	
}
