package com.example.mongodbConnect.model.dto;

import jakarta.validation.constraints.NotBlank;

public class TaskRecordDto {

    public record CreateTaskRequest(
            @NotBlank(message = "Task title must not be blank")
            String title,
            boolean completed
            ) {

    }

    public record UpdateTaskRequest(
            @NotBlank(message = "Task title must not be blank")
            String title,
            boolean completed
            ) {

    }

    public record TaskResponse(
            String id,
            String title,
            boolean completed
            ) {

    }
}
