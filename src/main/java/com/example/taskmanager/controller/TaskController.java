package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public String addTask(@RequestBody Task task) {
        taskService.addTask(task);
        return "Task added successfully";
    }

    @GetMapping
    public CompletableFuture<List<Task>> getTasks() {
        return taskService.getAllTasks();
    }

    @PutMapping("/{id}")
    public String updateTask(@PathVariable String id, @RequestBody Task task) {
        taskService.updateTask(id, task);
        return "Employee updated";
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return "Employee deleted";
    }
}
