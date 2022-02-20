package esig.interns.tasks.repo;

import esig.interns.tasks.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Long> {
    Task findById(Integer id);
}
