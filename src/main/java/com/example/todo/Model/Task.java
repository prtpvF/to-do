package com.example.todo.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "task")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    @ManyToOne()
    @JoinColumn(name = "person_id")
    public Person owner;

    @Column(name = "time_of_crate")
    public Timestamp timeOfCreate;
    @Column(name = "time_of_expire")
    public Timestamp timeOfExpired;





}