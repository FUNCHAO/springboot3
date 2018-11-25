package com.fc.SpringBoot.dao;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface ICustomRespository<T,ID extends Serializable> extends PagingAndSortingRepository<T, ID>{
	void doSomething(ID id);
}
