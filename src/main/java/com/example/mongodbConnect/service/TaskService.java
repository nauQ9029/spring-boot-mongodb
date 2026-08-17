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

        System.out.println("Task update successfully");
        return taskRepository.save(existingTask);
    }

    public void deleteTask(String id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found");
        }

        taskRepository.deleteById(id);
        System.out.println("Task delete successfully");
    }

    public void createTaskItems() {
        System.out.println("Data creation started...");
        Task task = new Task();
        task.setId("1");
        task.setTitle("bomboclat sample");
        task.setCompleted(false);

        taskRepository.save(task);
        System.out.println("Data creation complete...");
    }
}
