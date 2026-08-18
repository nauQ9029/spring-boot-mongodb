package com.example.mongodbConnect.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.mongodbConnect.model.dto.TaskRecordDto;
import com.example.mongodbConnect.model.entity.Task;
import com.example.mongodbConnect.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskRecordDto.TaskResponse> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<TaskRecordDto.TaskResponse> getTasksByCompleted(boolean completed) {
        return taskRepository.findByCompleted(completed)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public TaskRecordDto.TaskResponse getTaskById(String id) {
        return taskRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
    }

    public TaskRecordDto.TaskResponse createTask(TaskRecordDto.CreateTaskRequest request) {
        Task task = new Task(request.title(), request.completed());
        Task saved = taskRepository.save(task);
        return mapToResponse(saved);
    }

    public TaskRecordDto.TaskResponse updateTask(String id, TaskRecordDto.UpdateTaskRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));

        task.setTitle(request.title());
        task.setCompleted(request.completed());

        Task updated = taskRepository.save(task);
        return mapToResponse(updated);
    }

    public TaskRecordDto.TaskResponse deleteTask(String id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));

        taskRepository.delete(task);
        return mapToResponse(task);
    }

    private TaskRecordDto.TaskResponse mapToResponse(Task task) {
        return new TaskRecordDto.TaskResponse(
                task.getId(),
                task.getTitle(),
                task.isCompleted()
        );
    }
}
