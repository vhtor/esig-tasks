package esig.interns.tasks.resource;

import esig.interns.tasks.model.Response;
import esig.interns.tasks.model.Task;
import esig.interns.tasks.service.implementation.TaskServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskResource {
    private final TaskServiceImplementation taskService;

    @GetMapping("/list")
    public ResponseEntity<Response> getTasks() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("tasks", taskService.list(30)))
                        .message("Tasks retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addTask(@RequestBody @Valid Task task) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("task", taskService.create(task)))
                        .message("Task added successfully")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getTask(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("task", taskService.get(id)))
                        .message("Task retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<Response> deleteTask(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("deleted", taskService.delete(id)))
                        .message("Task deleted successfully")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}