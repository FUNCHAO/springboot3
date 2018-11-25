package com.fc.SpringBoot.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.fc.SpringBoot.entity.Teacher;

public class CustomerSpecs {
	
	public static Specification<Teacher> teacherAge26(){
		return new Specification<Teacher>(){
			//root获得需要查询的属性，CriteriaBuilder构造查询条件
			@Override
			public Predicate toPredicate(Root<Teacher> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				//查询年龄小于26的老师
//				return cb.lessThan(root.get("age"), 26);
				return cb.lessThan(root.get("age").as(Integer.class), 26);
			}
			
		};
	}
}
