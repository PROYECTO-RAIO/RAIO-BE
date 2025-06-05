package com.raio_be.raio_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class RaioBeApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.configure().load();

		
		
		
		System.setProperty("SPRING_DATASOURCE_URL", dotenv.get("SPRING_DATASOURCE_URL"));
		System.setProperty("SPRING_DATASOURCE_USERNAME", dotenv.get("SPRING_DATASOURCE_USERNAME"));
		System.setProperty("SPRING_DATASOURCE_PASSWORD", dotenv.get("SPRING_DATASOURCE_PASSWORD"));

		
		SpringApplication.run(RaioBeApplication.class, args);
		System.out.println("Se ha iniciado con éxito.");
	}

	

}
