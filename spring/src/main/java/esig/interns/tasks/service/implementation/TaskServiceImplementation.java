package esig.interns.tasks.service.implementation;

import esig.interns.tasks.model.Task;
import esig.interns.tasks.repo.TaskRepo;
import esig.interns.tasks.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Collection;

import static java.lang.Boolean.TRUE;
import static org.springframework.data.domain.PageRequest.of;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j

public class TaskServiceImplementation implements TaskService {
    private final TaskRepo taskRepo;

    @Override
    public Task create(Task task) {
        log.info("Saving new task: {}", task.getTitle());
        return taskRepo.save(task);
    }

    @Override
    public Collection<Task> list(int limit) {
        log.info("Fetching all tasks...");
        return taskRepo.findAll(of(0, limit)).toList();
    }

    @Override
    public Task get(Long id) {
        log.info("Fetching task by id: {}", id);
        return taskRepo.findById(id).get();
    }

    @Override
    public Task update(Task task) {
        log.info("Updating task: {}", task.getTitle());
        return taskRepo.save(task);
    }

    @Override
    public Boolean done(Long id) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting task by ID: {}", id);
        taskRepo.deleteById(id);
        return TRUE;
    }
}
