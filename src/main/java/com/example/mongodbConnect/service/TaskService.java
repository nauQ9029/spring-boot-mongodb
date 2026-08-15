package com.example.mongodbConnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mongodbConnect.model.Task;
import com.example.mongodbConnect.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(String id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(String id, Task task) {
        Task existingTask = getTaskById(id);

        existingTask.setTitle(task.getTitle());
        existingTask.setCompleted(task.isCompleted());

        return taskRepository.save(existingTask);
    }

    public void deleteTask(String id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found");
        }

        taskRepository.deleteById(id);
    }

    public void createTaskItems() {
        System.out.println("Data creation started...");
        taskRepository.save(new Task("1", "bomboclat sample", false));
        System.out.println("Data creation complete...");
    }
}
