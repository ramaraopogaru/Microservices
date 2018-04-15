package com.dotridge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

import com.dotridge.proxy.SearchSink;

@EnableBinding(SearchSink.class)
@SpringBootApplication
public class AdminServiceJarApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminServiceJarApplication.class, args);
	}
	
	@Bean
	public HibernateJpaSessionFactoryBean sessionFactory() {
		return new HibernateJpaSessionFactoryBean();
	}
	
	@ServiceActivator(inputChannel=SearchSink.QNAME)
	public void recieveFromQueue(String data) {
		System.out.println("processed information is:\t"+data);
	}
}
