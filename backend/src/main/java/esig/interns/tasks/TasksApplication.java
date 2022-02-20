package esig.interns.tasks;

import esig.interns.tasks.model.Task;
import esig.interns.tasks.repo.TaskRepo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.Arrays;

import static esig.interns.tasks.enumeration.Priority.*;

@SpringBootApplication
public class TasksApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasksApplication.class, args);
	}

	@Bean
	CommandLineRunner run(TaskRepo taskRepo) {
		return args -> {
			taskRepo.save(new Task(null, "Task teste 1", "Victor Hugo", "20-02-2022", 0, HIGH)); // Status false significa que a task não foi completada
			taskRepo.save(new Task(null, "Task teste 2", "Fernanda", "16-02-2022", 1, HIGH));
			taskRepo.save(new Task(null, "Task teste 3", "Severino", "15-02-2022", 1, LOW));
			taskRepo.save(new Task(null, "Task teste 4", "Josefa", "23-02-2022", 0, MEDIUM));
			taskRepo.save(new Task(null, "Task teste 5", "Tião", "28-02-2022", 0, LOW));
		};
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200", "http://localhost:8080", "http://localhost:9876"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With", "Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization", "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}
