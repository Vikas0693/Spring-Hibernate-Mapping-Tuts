package com.database.hibernate;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class HibernateJpaSpringConnectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateJpaSpringConnectionApplication.class, args);
	}
	//@Autowired
	//static public EntityManagerFactory factory;
	/*@Bean
	public SessionFactory getSessionFactory() {
		if(factory.unwrap(SessionFactory.class) == null){
			throw new NullPointerException("factory is not a hibernate factory");
		}
		return factory.unwrap(SessionFactory.class);
	}*/
}
