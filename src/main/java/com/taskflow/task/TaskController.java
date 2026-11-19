package com.taskflow.task;

import com.taskflow.task.dto.TaskRequest;
import com.taskflow.task.dto.TaskResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskResponse> list() {
        return taskService.findAll().stream()
                .map(TaskResponse::from)
                .toList();
    }

    @GetMapping("/{id}")
    public TaskResponse getOne(@PathVariable Long id) {
        return TaskResponse.from(taskService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(@Valid @RequestBody TaskRequest request,
                                               UriComponentsBuilder uriBuilder) {
        Task created = taskService.create(request);
        URI location = uriBuilder.path("/api/tasks/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(TaskResponse.from(created));
    }

    @PutMapping("/{id}")
    public TaskResponse update(@PathVariable Long id, @Valid @RequestBody TaskRequest request) {
        return TaskResponse.from(taskService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }
}
