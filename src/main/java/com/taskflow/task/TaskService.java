package com.taskflow.task;

import com.taskflow.common.ResourceNotFoundException;
import com.taskflow.task.dto.TaskRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Business logic lives here, not in the controller. The controller handles
 * HTTP; the service handles rules. This separation is what @WebMvcTest and
 * Mockito-based unit tests rely on in Phase 5.
 */
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    // Constructor injection: preferred over field injection because it makes
    // dependencies explicit, supports final fields, and is trivial to test.
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task " + id + " not found"));
    }

    public Task create(TaskRequest request) {
        Task task = new Task();
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(request.status() != null ? request.status() : TaskStatus.TODO);
        return taskRepository.save(task);
    }

    public Task update(Long id, TaskRequest request) {
        Task task = findById(id);
        task.setTitle(request.title());
        task.setDescription(request.description());
        if (request.status() != null) {
            task.setStatus(request.status());
        }
        return taskRepository.save(task);
    }

    public void delete(Long id) {
        if (!taskRepository.deleteById(id)) {
            throw new ResourceNotFoundException("Task " + id + " not found");
        }
    }
}
