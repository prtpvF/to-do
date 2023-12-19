package com.example.todo.Controllers;

import com.example.todo.Model.Task;
import com.example.todo.Services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/tasks")
    public List<Task> tasks(Principal principal){
        return taskService.allPersonTask(principal);
    }

    @PostMapping("/add/task")
    public HttpStatus addTask(Principal principal, @RequestBody Task task){
         taskService.addTask(principal,task);
         return HttpStatus.OK;
    }

    @DeleteMapping("/delete")
    public HttpStatus deleteTask(@RequestBody int id){
        taskService.deleteTask(id);
        return HttpStatus.OK;
    }

    @PostMapping
    public HttpStatus editTask( @RequestBody int id, @RequestBody Task task){
        taskService.changeTask(task,id);
        return HttpStatus.OK;
    }
}
