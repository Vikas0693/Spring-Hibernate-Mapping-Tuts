package com.database.hibernate.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.database.hibernate.models.User;

@Component
public class UserService {
	@Autowired
	public EntityManagerFactory factory;
	
	public List<User> getUserDetails(){
		Session s = factory.createEntityManager().unwrap(Session.class);
		List<User> u = s.createQuery("from User").list();
		s.close();
		return u;
	}
}
