package com.mysite.t1_homework2.service;

import com.mysite.t1_homework2.dto.TaskRequest;
import com.mysite.t1_homework2.exception.TaskNotFoundException;
import com.mysite.t1_homework2.model.Task;
import com.mysite.t1_homework2.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }

    @Transactional
    public void createTask(TaskRequest createTaskRequest) {
        Task task = Task.builder()
                .title(createTaskRequest.getTitle())
                .description(createTaskRequest.getDescription())
                .dueDate(createTaskRequest.getDueDate())
                .completed(createTaskRequest.getCompleted())
                .build();
        taskRepository.save(task);
    }

    @Transactional
    public void updateTask(Long id, TaskRequest taskRequest) {
        Task taskToUpdate = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        taskToUpdate.setTitle(taskRequest.getTitle());
        taskToUpdate.setDescription(taskRequest.getDescription());
        taskToUpdate.setDueDate(taskRequest.getDueDate());
        taskToUpdate.setCompleted(taskRequest.getCompleted());
        taskRepository.save(taskToUpdate);

    }

    @Transactional
    public void deleteTaskDyId(Long id) {
        Task taskToDelete = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        taskRepository.delete(taskToDelete);
    }
}
