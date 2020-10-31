
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
		verifyOneToManyMapping();
		verifyLazyFetching();
		
	}
	//this is correct way of doing mapping
	public void makeManyToOneAsOwiningSideOfRelation() {
		
		System.out.println("Make many to one as owning side of relation");
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = null;
		try
		{
			User u = em.find(User.class, 1);
			if(u!=null)
				return;
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

	public void verifyOneToManyMapping() {
		System.out.println("Verifying relationship b/w User and holdings");
		EntityManager em = factory.createEntityManager();
		int userId=1;
		long holdingId=1;
		try
		{
			User u = em.find(User.class, userId);
			Holdings h = em.find(Holdings.class, holdingId);
			
			if(u.getHoldings().get(0).getId().longValue() == h.getId().longValue()) {
				System.out.println("Holdings from Users List == Holdings for that User From Db");
			}
			else
			{
				System.out.println("User's holding  does not match with holding from Db");
			}
			
			if(h.getUser().getId() == u.getId())
				System.out.println("Holding's User id == Userid from Db");
			else
				System.err.println("Holding's User id != Userid from Db");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			em.close();
		}
	}
	
	//fetching user should not fetch HoldingsList of that user
	public void verifyLazyFetching() {
		System.out.println("Verifying Lazy Loading of Holdings List in User object.");
		EntityManager em = factory.createEntityManager();
		int userId=1;
		User u=null;
		try
		{
			u = em.find(User.class, userId);
			//commenting below will not fetch holdings, see hql logs.We will see that no query is fired to fetch holdings until we exlicitly do user.getHolding()
			//List<Holdings> list = u.getHoldings();
			//System.out.println(list.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			em.close();
		}
		//failed to lazily initailize collection will come if code is commented in above try/catch block
		List<Holdings> list = u.getHoldings();
		System.out.println(list.toString());
	}
	
}
