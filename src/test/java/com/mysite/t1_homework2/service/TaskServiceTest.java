package com.mysite.t1_homework2.service;

import com.mysite.t1_homework2.dto.TaskRequest;
import com.mysite.t1_homework2.exception.TaskNotFoundException;
import com.mysite.t1_homework2.model.Task;
import com.mysite.t1_homework2.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void testGetAllTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(Task.builder().title("Task 1").description("Description 1").dueDate(new Date()).completed(true).build());
        tasks.add(Task.builder().title("Task 2").description("Description 2").dueDate(new Date()).completed(false).build());

        Mockito.when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> result = taskService.getAllTasks();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetTaskById() {
        Task task = Task.builder().title("Task 1").description("Description 1").dueDate(new Date()).completed(true).build();

        Mockito.when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Task result = taskService.getTaskById(1L);

        assertEquals("Task 1", result.getTitle());
    }

    @Test
    public void testCreateTask() {
        TaskRequest createTaskRequest = new TaskRequest("New Task", "New Description", new Date(), false);

        taskService.createTask(createTaskRequest);

        Mockito.verify(taskRepository).save(Mockito.any(Task.class));
    }

    @Test
    public void testUpdateTask() {
        Task existingTask = new Task(1L, "Existing Task", "Existing Description", null, false);
        TaskRequest taskRequest = new TaskRequest("Updated Task", "Updated Description", null, true);

        Mockito.when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));

        taskService.updateTask(1L, taskRequest);

        Mockito.verify(taskRepository).save(existingTask);
        assertEquals("Updated Task", existingTask.getTitle());
    }

    @Test
    public void testDeleteTaskById() {
        Task existingTask = new Task(1L, "Existing Task", "Existing Description", null, false);

        Mockito.when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));

        taskService.deleteTaskDyId(1L);

        Mockito.verify(taskRepository).delete(existingTask);
    }

    @Test
    public void testGetTaskByIdNotFound() {
        Mockito.when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById(1L));
    }
}
