package com.fc.SpringBoot.authority;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fc.SpringBoot.dao.TeacherRepository;
import com.fc.SpringBoot.entity.Teacher;
@Service
public class CustomUserService implements UserDetailsService{
	  @Autowired
	  private TeacherRepository teacherRepository;
		
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Teacher teacher = teacherRepository.getByName(userName);
		if(null==teacher)
		{
			throw new UsernameNotFoundException("用户："+userName+"不存在");
		}
		List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>(10);
		//权限如果前缀是ROLE_，security就会认为这是个角色信息。例如ROLE_ADMIN，那么ADMIN就是该用户的角色
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN1")); 
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN2")); 
		return new User(teacher.getName(), teacher.getPassword(), authorities);
	}

}
