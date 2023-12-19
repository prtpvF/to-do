package com.example.todo.Services;

import com.example.todo.Exception.ApiRequestException;
import com.example.todo.Model.Person;
import com.example.todo.Model.Task;
import com.example.todo.Repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {
    private final TaskRepository taskRepository;
    private final PersonService personService;

    public List<Task> allPersonTask(Principal principal) {
        Optional<Person> person = personService.findByUsername(principal.getName());
        List<Task> personTasks = person.get().getTasks();
        return personTasks;
    }


    public HttpStatus addTask(Principal principal, Task task) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Optional<Person> person = personService.findByUsername(principal.getName());
        person.get().setTasks(List.of(task));

        task.setOwner(person.get());
        task.setTimeOfCreate(now);

        if (task.getTimeOfCreate().toInstant().isAfter(task.getTimeOfExpired().toInstant())) {
            log.debug("неверно введенное время");
            return HttpStatus.BAD_REQUEST;
        }
        taskRepository.save(task);
        return HttpStatus.OK;

    }

    public void deleteTask(int id) {
        taskRepository.deleteById(id);
    }

    public void changeTask(Task updatedTask, int id) {
        Task originalTask = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("task not found"));
        updatedTask.setId(originalTask.getId());
        taskRepository.save(updatedTask);
    }

    public Task getTask(int id){
        Task task = taskRepository.findById(id).orElseThrow(()-> new ApiRequestException("задание не найдено"));
        return task;
    }


}