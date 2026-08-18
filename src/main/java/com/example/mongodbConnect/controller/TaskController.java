package com.example.mongodbConnect.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mongodbConnect.model.dto.TaskRecordDto;
import com.example.mongodbConnect.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskRecordDto.TaskResponse> getAllTasks() {
        try {
            List<TaskRecordDto.TaskResponse> tasks = taskService.getAllTasks();
            log.info("GET /api/tasks success. Total tasks returned: {}", tasks.size());
            return tasks;
        } catch (Exception e) {
            log.error("GET /api/tasks failed.", e);
            throw e;
        }
    }

    @GetMapping("/completed")
    public List<TaskRecordDto.TaskResponse> getTasksByCompleted(@RequestParam boolean completed) {
        try {
            List<TaskRecordDto.TaskResponse> tasks = taskService.getTasksByCompleted(completed);
            log.info("GET /api/tasks/completed?completed={} success. Total tasks returned: {}", completed, tasks.size());
            return tasks;
        } catch (Exception e) {
            log.error("GET /api/tasks/completed?completed={} failed.", completed, e);
            throw e;
        }
    }

    @GetMapping("/{id}")
    public TaskRecordDto.TaskResponse getTaskById(@PathVariable String id) {
        try {
            TaskRecordDto.TaskResponse task = taskService.getTaskById(id);
            log.info("GET /api/tasks/{} success. Task title: {}", id, task.title());
            return task;
        } catch (Exception e) {
            log.error("GET /api/tasks/{} failed.", id, e);
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<TaskRecordDto.TaskResponse> createTask(@Valid @RequestBody TaskRecordDto.CreateTaskRequest request) {
        try {
            log.info("POST /api/tasks request received. Title: {}, completed: {}", request.title(), request.completed());
            TaskRecordDto.TaskResponse response = taskService.createTask(request);
            log.info("POST /api/tasks success. Created task id: {}", response.id());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            log.error("POST /api/tasks failed for title={}", request.title(), e);
            throw e;
        }
    }

    @PutMapping("/{id}")
    public TaskRecordDto.TaskResponse updateTask(
            @PathVariable String id,
            @Valid @RequestBody TaskRecordDto.UpdateTaskRequest request
    ) {
        try {
            log.info("PUT /api/tasks/{} request received. New title: {}, completed: {}", id, request.title(), request.completed());
            TaskRecordDto.TaskResponse response = taskService.updateTask(id, request);
            log.info("PUT /api/tasks/{} success. Updated task id: {}", id, response.id());
            return response;
        } catch (Exception e) {
            log.error("PUT /api/tasks/{} failed.", id, e);
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public TaskRecordDto.TaskResponse deleteTask(@PathVariable String id) {
        try {
            TaskRecordDto.TaskResponse response = taskService.deleteTask(id);
            log.info("DELETE /api/tasks/{} success. Deleted task id: {}", id, response.id());
            return response;
        } catch (Exception e) {
            log.error("DELETE /api/tasks/{} failed.", id, e);
            throw e;
        }
    }
}
