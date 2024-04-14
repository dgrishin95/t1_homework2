package com.mysite.t1_homework2.controller;

import com.mysite.t1_homework2.dto.TaskRequest;
import com.mysite.t1_homework2.model.Task;
import com.mysite.t1_homework2.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;


    @Test
    public void testGetAllTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(Task.builder().title("Task 1").description("Description 1").dueDate(new Date()).completed(true).build());
        tasks.add(Task.builder().title("Task 2").description("Description 2").dueDate(new Date()).completed(false).build());

        when(taskService.getAllTasks()).thenReturn(tasks);

        ResponseEntity<List<Task>> responseEntity = taskController.getAllTasks();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tasks, responseEntity.getBody());
    }

    @Test
    public void testGetTask() {
        Long taskId = 1L;
        Task task = Task.builder().title("Task 1").description("Description 1").dueDate(new Date()).completed(true).build();

        when(taskService.getTaskById(taskId)).thenReturn(task);

        ResponseEntity<Task> responseEntity = taskController.getTask(taskId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(task, responseEntity.getBody());
    }

    @Test
    public void testAddTask() {
        TaskRequest taskRequest = new TaskRequest("New Task", "New Description", new Date(), false);

        ResponseEntity<String> responseEntity = taskController.addTask(taskRequest);

        verify(taskService, times(1)).createTask(taskRequest);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Task added successfully", responseEntity.getBody());
    }

    @Test
    public void testUpdateTask() {
        Long taskId = 1L;
        TaskRequest taskRequest = new TaskRequest("Updated Task", "Updated Description", new Date(), true);

        ResponseEntity<String> responseEntity = taskController.updateTask(taskId, taskRequest);

        verify(taskService, times(1)).updateTask(taskId, taskRequest);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Task updated successfully", responseEntity.getBody());
    }

    @Test
    public void testDeleteTask() {
        Long taskId = 1L;

        ResponseEntity<String> responseEntity = taskController.deleteTask(taskId);

        verify(taskService, times(1)).deleteTaskDyId(taskId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Task deleted successfully", responseEntity.getBody());
    }
}
