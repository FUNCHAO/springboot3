package com.fc.SpringBoot.authority;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetailsService;
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled=true)//启用方法级别的权限认证
public class WeSecurityConfig extends WebSecurityConfigurerAdapter{
	
	//可以重写该方法来配置相关安全配置
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
//		http.authorizeRequests().antMatchers("/index").hasRole("ROLE_ADMIN");
		http.authorizeRequests()
		.antMatchers("/css/**", "/js/**","/images/**", "/webjars/**", "**/favicon.ico", "/login").permitAll()
		.antMatchers("/index").hasRole("ADMIN")
		.anyRequest().permitAll()
		.and().formLogin().loginPage("/login")//自定义的登录页面，登录地址为:"/login"的post请求会进行登录处理；"/login"貌似和登录的action保持一致
		.defaultSuccessUrl("/success")//认证通过访问地址
		.usernameParameter("name")//和js表单提交的name保持一致
        .passwordParameter("password")
//		.loginPage("/login")//点击任何页面都会进入这里（未登录）
		.failureUrl("/error").permitAll()
		.and().logout().logoutSuccessUrl("/success")//注销访问地址
		.permitAll().and().csrf().disable();
		//开启记住我,rememberMeParameter中参数和 check的name值一样
		http.rememberMe().rememberMeParameter("remeber");
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService());
//		auth.inMemoryAuthentication().withUser("abc").password("abc").roles("ADMIN");
//		auth.inMemoryAuthentication().withUser("abc6").password("abc").roles("ADMIN2");
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		//super.configure(web);
		web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/**/favicon.ico", "/image/**");
	}
	@Bean
	UserDetailsService customUserService(){
		return new CustomUserService();
	}
}
