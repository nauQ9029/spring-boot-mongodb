package com.example.mongodbConnect.model;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "tasks")
public class Task {
    
    @Id
    private String id;

    private String title;

    private boolean completed;
    
    public Task(String id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }
}
