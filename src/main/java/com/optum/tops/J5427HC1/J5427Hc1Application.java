 package com.optum.tops.J5427HC1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class J5427Hc1Application {
	
	final static String TOMCAT_PORT_KEY = "server.port";
	final static String TOMCAT_PORT_VAL = "1080";
	
	public static void main(String[] args) {
		
		System.setProperty(TOMCAT_PORT_KEY, TOMCAT_PORT_VAL);

		SpringApplication.run(J5427Hc1Application.class, args);
	}
}
