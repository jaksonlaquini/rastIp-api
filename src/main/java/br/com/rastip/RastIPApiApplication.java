package br.com.rastip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class RastIPApiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RastIPApiApplication.class, args);
	}
        
        @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RastIPApiApplication.class);
	}
}
