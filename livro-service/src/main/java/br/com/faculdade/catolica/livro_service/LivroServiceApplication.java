package br.com.faculdade.catolica.livro_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LivroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivroServiceApplication.class, args);
	}

}
