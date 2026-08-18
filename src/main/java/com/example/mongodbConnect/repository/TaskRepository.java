package com.example.mongodbConnect.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.mongodbConnect.model.entity.Task;

public interface TaskRepository extends MongoRepository<Task, String> {

    @Query("{ 'title': ?0 }")
    Task findByTitle(String title);

    @Query("{ 'completed': ?0 }")
    List<Task> findByCompleted(boolean completed);

    public long countByCompleted(boolean completed);
}
