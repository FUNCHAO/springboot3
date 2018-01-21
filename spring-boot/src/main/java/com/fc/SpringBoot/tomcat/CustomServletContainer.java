package com.fc.SpringBoot.tomcat;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
@Component
public  class CustomServletContainer implements EmbeddedServletContainerCustomizer {
	
	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(8080);
		//404页面必须放在static包下
		container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
	}

}
