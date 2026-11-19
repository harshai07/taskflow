package com.taskflow.task.dto;

import com.taskflow.task.Task;
import com.taskflow.task.TaskStatus;

import java.time.Instant;

/**
 * What the API returns. Keeping this separate from the Task model means
 * internal fields never leak to clients, and the API stays stable even
 * if the model changes. Map here instead of returning the entity directly.
 */
public record TaskResponse(
        Long id,
        String title,
        String description,
        TaskStatus status,
        Instant createdAt
) {
    public static TaskResponse from(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getCreatedAt()
        );
    }
}
