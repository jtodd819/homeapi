package com.api.homeapi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HomeapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeapiApplication.class, args);
	}

}
