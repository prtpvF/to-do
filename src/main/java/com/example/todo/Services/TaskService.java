package com.example.todo.Services;

import com.example.todo.Model.Task;
import com.example.todo.Repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    public List<Task> allTask() {
        return taskRepository.findAll();
    }

    public Task getTask(int id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("task not found"));
    }

    public void addTask(Task task) {
        taskRepository.save(task);
    }

    public void deleteTask(int id) {
        taskRepository.deleteById(id);
    }

    public void changeTask(Task updatedTask, int id) {
        Task originalTask = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("task not found"));
        updatedTask.setId(originalTask.getId());
        taskRepository.save(updatedTask);
    }


}