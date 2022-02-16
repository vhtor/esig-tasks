package esig.interns.tasks.service;

import esig.interns.tasks.model.Task;
import java.util.Collection;

public interface TaskService {
    Task create(Task task);
    Collection<Task> list(int limit);
    Task get(Long id);
    Task update(Task task);
    Boolean done(Long id);
    Boolean delete(Long id);
}
