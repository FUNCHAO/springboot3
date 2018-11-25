package com.fc.SpringBoot.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class CustomRespository<T,ID extends Serializable> extends SimpleJpaRepository<T, ID> implements ICustomRespository<T, ID> {

	private final EntityManager entityManager;
	public CustomRespository(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
		this.entityManager=entityManager;
	}
	@Override
	public void doSomething(ID id) {
		// TODO Auto-generated method stub
		
	}


}
