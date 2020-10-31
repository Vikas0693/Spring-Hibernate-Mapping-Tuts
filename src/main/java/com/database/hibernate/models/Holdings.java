package com.database.hibernate.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Holdings {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column
	private Long id;
	private Double units,buyNav; 
	
	@ManyToOne
	@JoinColumn(name="USERID123")//UPPERCASE will be converted to lowercase in db column name
	User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getUnits() {
		return units;
	}

	public void setUnits(Double units) {
		this.units = units;
	}

	public Double getBuyNav() {
		return buyNav;
	}

	public void setBuyNav(Double buyNav) {
		this.buyNav = buyNav;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
