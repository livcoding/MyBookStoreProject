package com.bookstore.dao;



import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.bookstore.entity.Users;

public class UserDAOTest {

	@Test	
	public void testCreateUsers() {
		
		Users user1 = new Users();
		user1.setEmail("LohnPaul@zepelin.net");
		user1.setFullName("Lohn Paul Jones");
		user1.setPassword("Lassword");
		
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("BookStore");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		
		UserDAO userDAO=new UserDAO(entityManager);
		user1 = userDAO.create(user1);
		entityManager.close();
		entityManagerFactory.close();
		
		assertTrue(user1.getUserId() > 0);
		
	}
	
	

}
