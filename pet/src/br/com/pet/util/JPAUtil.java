package br.com.pet.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static EntityManagerFactory  entityManagerFactory = Persistence.createEntityManagerFactory("pet"); 
	
	public EntityManager getEntityManager(){
		return entityManagerFactory.createEntityManager();
	}
	
		
}
