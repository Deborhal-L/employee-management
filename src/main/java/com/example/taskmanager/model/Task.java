package com.example.taskmanager.model;

public class Task {

    private String id;
    private String name;
    private String role;
    private String salary;
    private String status;

    public Task() {}

    public Task(String id, String name, String role, String salary, String status) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.salary = salary;
        this.status = status;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getSalary() { return salary; }
    public void setSalary(String salary) { this.salary = salary; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}