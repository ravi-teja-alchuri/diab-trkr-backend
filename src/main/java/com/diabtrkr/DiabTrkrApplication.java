package com.diabtrkr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class DiabTrkrApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiabTrkrApplication.class, args);
	}

}
