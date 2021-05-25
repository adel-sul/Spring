package com.cybertek;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringOrmProjectManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringOrmProjectManagementApplication.class, args);
	}

	/*
		ModelMapper is a 3d party library. But since @SpringBootApplication is covering @ComponentScan, @EnableAutoConfiguration
		and @Configuration (which is used for a class with 3d party beans) we can create @Bean here
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
