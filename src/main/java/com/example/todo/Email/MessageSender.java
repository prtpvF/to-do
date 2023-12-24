package com.example.todo.Email;

import com.example.todo.Model.Person;
import com.example.todo.Model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageSender {
    private final EmailSenderServices senderServices;

    public void send(Person person, String subject, String body){
        senderServices.sendEmail(person.getEmail(),subject,body);
    }

    public void sendMessageAboutTask(Optional<Person> person, Task task){
        senderServices.sendEmail(person.get().getEmail(), "добавление", "уважаемый пользвоатель, задача была успешно добавлена! " +
                "На ваш счет добвалено 10 очков автивности");
    }

    public void sendNotificationAboutTime(Person person, Task task){
        java.sql.Timestamp now = new Timestamp(System.currentTimeMillis());
        Duration duration = Duration.between(now.toInstant(), task.getTimeOfExpired().toInstant());
        long remainingTime = duration.toMinutes();
        String body = "До окончания задания: " + task.getName() + "осталось: " + remainingTime + " минут. " +
                "Вы уже выполнили его?";
        send(person, "напоминание", body);
    }

    public void sendAChangeTaskMessage(Person person, Task task){
        String body = "Уважаемый, " + person.getUsername() + ", вы только что изменили свою задачу!";
        send(person, "напоминание", body);
    }
}
