package br.com.cwi.apus.order;

import br.com.cwi.apus.order.domain.Product;
import br.com.cwi.apus.order.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@EnableScheduling
@SpringBootApplication
public class ApusOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApusOrderApplication.class, args);
	}

	@AllArgsConstructor
	@Component
	public class DataInitializer implements ApplicationRunner {

		private ProductRepository repository;

		@Override
		public void run(ApplicationArguments args) throws Exception {

			Product product = Product.builder()
					.description("Agua mineral")
					.volume(1)
					.quantity(200)
					.price(BigDecimal.ONE)
					.build();

			repository.save(product);
		}
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
