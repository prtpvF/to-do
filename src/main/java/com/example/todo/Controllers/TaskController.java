package com.example.todo.Controllers;

import com.example.todo.Model.Task;
import com.example.todo.Services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
