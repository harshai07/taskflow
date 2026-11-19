package com.taskflow.task.dto;

import com.taskflow.task.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * What a client sends when creating or updating a task.
 * A Java record keeps the DTO immutable and compact.
 * Bean Validation annotations are enforced by @Valid in the controller.
 */
public record TaskRequest(

        @NotBlank(message = "title is required")
        @Size(max = 120, message = "title must be at most 120 characters")
        String title,

        @Size(max = 2000, message = "description must be at most 2000 characters")
        String description,

        TaskStatus status
) {
}
