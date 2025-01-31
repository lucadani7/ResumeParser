package com.lucadani.resumeparser;

import org.springframework.boot.SpringApplication;

public class TestResumeparserApplication {

	public static void main(String[] args) {
		SpringApplication.from(ResumeparserApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
