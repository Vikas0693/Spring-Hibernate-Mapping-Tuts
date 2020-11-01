package com.database.hibernate.runners;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import com.database.hibernate.models.Holdings;
import com.database.hibernate.models.User;


@Component
public class SecondLevelCacheRunner implements CommandLineRunner{

	@Autowired
	private EntityManagerFactory factory;
	
	public void run(String... args) throws Exception {
		//verifySecondLevelCacheAcrossSession();
		//verifySecondLevelCacheAcrossEntityManager();
	}
	
	private void verifySecondLevelCacheAcrossSession() {
		System.out.println("Verifying Second Level Cache.");
		
		try
		{
			SessionFactory sf = factory.unwrap(SessionFactory.class);
			Session hSession = sf.openSession();
			
			User u = hSession.get(User.class, 1);
			System.out.println("User id = "+u.getId()+" in first Session.");
			List<net.sf.ehcache.CacheManager> allCacheManagersList = net.sf.ehcache.CacheManager.ALL_CACHE_MANAGERS;
			int size = allCacheManagersList.get(0)
					.getCache("user").getSize();
			if(size > 0) {
				System.out.println("Second Level Cache Region is storing User");
			}
			else
				System.err.println("No User object found in Second level Region Manager.");
			hSession.close();
			
			hSession = sf.openSession();
			Transaction tx= hSession.beginTransaction();
			u = hSession.get(User.class, 1);
			System.out.println("User id = "+u.getId()+" in Second Session with email = "+u.getEmail());
			u.setEmail("UpdatedEmail123");
			tx.commit();
			hSession.close();
			
			hSession = sf.openSession();
			u = hSession.get(User.class, 1);
			System.out.println("User id = "+u.getId()+" in Third Session with email = "+u.getEmail());
			hSession.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}
	private void verifySecondLevelCacheAcrossEntityManager() {
		System.out.println("Verifying Second Level Cache Across Entity Managers.");
		
		try
		{
			EntityManager em = factory.createEntityManager();
			
			User u = em.find(User.class, 1);
			System.out.println("User id = "+u.getId()+" in first Session.");
			List<net.sf.ehcache.CacheManager> allCacheManagersList = net.sf.ehcache.CacheManager.ALL_CACHE_MANAGERS;
			int size = allCacheManagersList.get(0)
					.getCache("user").getSize();
			if(size > 0) {
				System.out.println("Second Level Cache Region is storing User");
			}
			else
				System.err.println("No User object found in Second level Region Manager.");
			em.close();
			em = factory.createEntityManager();
			u = em.find(User.class,1);
			System.out.println("User Fetched from EManager with id = "+u.getId());
			em.close();
			Thread.sleep(11000);
			SessionFactory sf = factory.unwrap(SessionFactory.class);
			Session hSession = sf.openSession();
			User u1 = hSession.get(User.class, 1);
			System.out.println("User fetched from Session with id = "+u1.getId());
			hSession.close();
			sf.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}

}
