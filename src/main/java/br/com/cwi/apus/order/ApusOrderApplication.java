package br.com.cwi.apus.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ApusOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApusOrderApplication.class, args);
	}
}
