package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import com.google.firebase.database.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TaskService {

    private final DatabaseReference dbRef =
            FirebaseDatabase.getInstance().getReference("tasks");

    // ✅ CREATE
    public void addTask(Task task) {
        String id = dbRef.push().getKey();
        task.setId(id);
        dbRef.child(id).setValueAsync(task);
    }

    // ✅ READ (ALL)
    public CompletableFuture<List<Task>> getAllTasks() {
        CompletableFuture<List<Task>> future = new CompletableFuture<>();

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<Task> list = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Task task = data.getValue(Task.class);
                    list.add(task);
                }
                future.complete(list);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                future.completeExceptionally(error.toException());
            }
        });

        return future;
    }

    // ✅ UPDATE
    public void updateTask(String id, Task task) {
        task.setId(id);
        dbRef.child(id).setValueAsync(task);
    }

    // ✅ DELETE
    public void deleteTask(String id) {
        dbRef.child(id).removeValueAsync();
    }
}