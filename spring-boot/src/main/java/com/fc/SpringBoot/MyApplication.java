package com.fc.SpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
//括号内：springboot启动时会自动注入数据源和配置，在@SpringBootApplication中排除其注入..去掉之后会连接配置的数据库
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@SpringBootApplication
public class MyApplication {

	public static void main(String[] args) {
		 
		SpringApplication.run(MyApplication.class, args);

	}

}
