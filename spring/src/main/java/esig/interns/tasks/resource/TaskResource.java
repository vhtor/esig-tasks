package esig.interns.tasks.resource;

import esig.interns.tasks.model.Response;
import esig.interns.tasks.service.implementation.TaskServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static java.time.LocalDateTime.now;
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
                        .data(Map.of("tasks", taskService.list(30)))
                        .message("Tasks retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}
