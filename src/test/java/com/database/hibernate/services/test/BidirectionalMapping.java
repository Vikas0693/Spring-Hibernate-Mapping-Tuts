package com.database.hibernate.services.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.database.hibernate.controllers.UserController;
import com.database.hibernate.services.HoldingsService;
import com.database.hibernate.services.UserService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BidirectionalMapping {

	@Autowired
	private UserController uc;
	
	@Test
	public void test() {
		
	}
	
	@Test
	public void holdingsLoaded() {
		assertThat(uc).isNotNull();
	}

}
