package com.lucadani.resumeparser;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResumeparserApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.setProperty("DB_URL", String.format("jdbc:postgresql://%s:%s/%s",
				dotenv.get("DB_HOST"), dotenv.get("DB_PORT"), dotenv.get("DB_NAME")));
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

		SpringApplication.run(ResumeparserApplication.class, args);
	}

}
