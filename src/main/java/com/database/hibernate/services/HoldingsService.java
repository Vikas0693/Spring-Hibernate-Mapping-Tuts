package com.database.hibernate.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HoldingsService {

	@Autowired
	EntityManagerFactory factory;
	
	public void saveHolding() {
		EntityManager em = factory.createEntityManager();
		
	}
}
