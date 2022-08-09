package com.samsung.evaluation.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SamsungEvaluationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SamsungEvaluationApiApplication.class, args);
	}
	
	@Bean
	public WebClient webClient(WebClient.Builder builder) {
		return builder
				.baseUrl("https://cellolatam.cellologistics.com.br/sds-devs-evaluation/evaluation")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods("GET", "POST", "PUT", "DELETE")
						.allowedOrigins("http://localhost:4200");
			}
		};
	}

}
