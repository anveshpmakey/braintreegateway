package com.anvesh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.anvesh")
public class BraintreeIntegrationAppIntializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
		return applicationBuilder.sources(BraintreeIntegrationAppIntializer.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(BraintreeIntegrationAppIntializer.class, args);
	}
}
