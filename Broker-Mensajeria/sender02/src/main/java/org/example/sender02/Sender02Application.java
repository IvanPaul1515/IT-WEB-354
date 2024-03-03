package org.example.sender02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Sender02Application {

	public static void main(String[] args) {
		SpringApplication.run(Sender02Application.class, args);
	}

}
