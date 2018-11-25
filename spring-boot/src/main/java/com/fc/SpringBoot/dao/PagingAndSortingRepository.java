package com.fc.SpringBoot.dao;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
@NoRepositoryBean
public interface PagingAndSortingRepository <T,ID extends Serializable>
extends CrudRepository<T,ID> {

Iterable<T> findAll(Sort sort);

Page<T> findAll(Pageable pageable);
}
