package com.lovnx;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class B_Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder(B_Application.class).web(true).run(args);
	}

}
