package com.mysite.t1_homework2.controller;

import com.mysite.t1_homework2.dto.TaskRequest;
import com.mysite.t1_homework2.model.Task;
import com.mysite.t1_homework2.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PostMapping
    public ResponseEntity<String> addTask(@RequestBody @Valid TaskRequest createTaskRequest) {
        taskService.createTask(createTaskRequest);
        return new ResponseEntity<>("Task added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Long id, @RequestBody @Valid TaskRequest updateTaskRequest) {
        taskService.updateTask(id, updateTaskRequest);
        return new ResponseEntity<>("Task updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskService.deleteTaskDyId(id);
        return new ResponseEntity<>("Task deleted successfully", HttpStatus.OK);
    }
}
