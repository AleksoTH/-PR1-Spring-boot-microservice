package com.github.AleksoTH.BackendDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.github.AleksoTH.BackendDemo.RestAPI.SwaggerLoader;

@SpringBootApplication
@Import(SwaggerLoader.class)
public class BackendDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendDemoApplication.class, args);
	}

}
