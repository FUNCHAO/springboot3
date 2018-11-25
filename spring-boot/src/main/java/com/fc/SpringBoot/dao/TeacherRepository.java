package com.fc.SpringBoot.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.fc.SpringBoot.entity.Teacher;
//继承JpaRepository<Teacher, Integer>  泛型：类，主键类型
public interface TeacherRepository extends JpaRepository<Teacher, Integer>,JpaSpecificationExecutor<Teacher>{
	
	//根据年龄查询，查询命名语法要规范才能查出来
	 List<Teacher> findByAge(Integer age);
	 Page<Teacher> findAll(Pageable pageable);
	 long count();
	 List<Teacher>findByName(String name);
	 List<Teacher>findByNameLike(String name);
	 Teacher getByName(String name);
	 List<Teacher>findByNameAndAge(String name,Integer age);
	 /**
	  * 获取符合条件的前10条数据
	  * @param name
	  * @return
	  */
	 List<Teacher>getTop10ByAge(Integer age);
	 /**
	  * 修改
	  * @param 
	  * @return
	  */
	 @Transactional
	 @Modifying
	 @Query("update Teacher t set t.age=?1 where t.name=?2")
	 int updateAgeByName(Integer age,String name);

}
