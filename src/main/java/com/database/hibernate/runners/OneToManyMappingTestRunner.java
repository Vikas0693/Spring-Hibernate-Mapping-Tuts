
package com.database.hibernate.runners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.transaction.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.database.hibernate.models.Holdings;
import com.database.hibernate.models.User;

@Component
public class OneToManyMappingTestRunner implements CommandLineRunner{

	@Autowired
	private EntityManagerFactory factory;
	
	public void run(String... args) throws Exception {
		
		makeManyToOneAsOwiningSideOfRelation();
		
	}
	//this is correct way of doing mapping
	public void makeManyToOneAsOwiningSideOfRelation() {
		
		System.out.println("Make many to one as owning side of relation");
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = null;
		try
		{
			tx = em.getTransaction();
			tx.begin();
			User userVikas = new User();
			userVikas.setEmail("Vikas@gmail.com");
			userVikas.setFirstName("Vikas");
			userVikas.setLastName("Sharma");
			userVikas.setPassword("Password");
			em.persist(userVikas);
			
			User userAshok = new User();
			userAshok.setEmail("Ashok@gmail.com");
			userAshok.setFirstName("Ashok");
			userAshok.setLastName("Mirani");
			userAshok.setPassword("password-password");
			em.persist(userAshok);
			
			Holdings vikasH = new Holdings();
			vikasH.setBuyNav(1.0);
			vikasH.setUnits(100.0);
			vikasH.setUser(userVikas);
			em.persist(vikasH);
			
			Holdings ashokH = new Holdings();
			ashokH.setBuyNav(2.0);
			ashokH.setUnits(100.0);
			ashokH.setUser(userAshok);
			em.persist(ashokH);
			
			List<Holdings> list = new ArrayList<Holdings>(Arrays.asList(vikasH,ashokH));
			
			userVikas.setHoldings(list);
			em.persist(userVikas);
			tx.commit();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			tx.rollback();
		}
		finally
		{
			em.close();
		}
	}

	
}
