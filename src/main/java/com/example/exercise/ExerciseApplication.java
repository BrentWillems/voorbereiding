package com.example.exercise;

import io.swagger.annotations.ApiResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExerciseApplication.class, args);
	}

	@GetMapping("/")
	@ApiResponse(code = 303, message = "redirect")
	public ResponseEntity greeting() {
		return ResponseEntity
				.status(HttpStatus.SEE_OTHER)
				.header("Location", "/swagger-ui.html")
				.build();
	}

}
