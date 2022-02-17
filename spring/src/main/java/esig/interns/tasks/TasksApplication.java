package esig.interns.tasks;

import esig.interns.tasks.enumeration.Priority;
import esig.interns.tasks.enumeration.Status;
import esig.interns.tasks.model.Task;
import esig.interns.tasks.repo.TaskRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TasksApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasksApplication.class, args);
	}

	// Testing
	@Bean
	CommandLineRunner run(TaskRepo taskRepo) {
		return args -> {
			taskRepo.save(new Task(null, 1, "Task teste 1", "Victor Hugo", "20-02-2022", Status.UNFINISHED, Priority.HIGH));
			taskRepo.save(new Task(null, 2, "Task teste 2", "Dalila linda&gostosa amo d++", "16-02-2022", Status.UNFINISHED, Priority.HIGH));
			taskRepo.save(new Task(null, 3, "Task teste 3", "Sivirino", "15-02-2022", Status.FINISHED, Priority.LOW));
			taskRepo.save(new Task(null, 4, "Task teste 4", "Jusefa", "23-02-2022", Status.FINISHED, Priority.MEDIUM));
		};
	}
}
