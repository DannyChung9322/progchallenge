package com.candidate.dannychung.progchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;

@SpringBootApplication(scanBasePackages = "com.candidate.dannychung")
public class ProgchallengeApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ProgchallengeApplication.class, args);
	}
}
