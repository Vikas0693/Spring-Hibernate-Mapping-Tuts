package com.database.hibernate.mytesting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.database.hibernate.controllers.UserController;

//@SpringBootTest
@RunWith(SpringRunner.class)
@DataJpaTest
public class TestingBidirectionalMapping {

	@Autowired
	private UserController uc;
	
	@Autowired
	TestEntityManager manager;
	
	@Test
	public void makeInconsistencyInOneToManyMapping() {
		assertThat(manager).isNotNull();
		assertThat(uc).isNotNull();
	}
}
