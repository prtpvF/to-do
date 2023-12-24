import React, { useState, useEffect } from 'react';
import {Link, useParams} from 'react-router-dom';
import axios from 'axios';

export default function ViewTask() {
    const [task, setTask] = useState(null);
    const token = document.cookie.split('; ').find(row => row.startsWith('jwt_token=')).split('=')[1];
    const { id } = useParams();

    useEffect(() => {
        axios.get(`http://localhost:8080/get/${id}`, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
            .then((response) => {
                setTask(response.data); // Сохраните данные задания в состоянии
            })
            .catch((error) => {
                console.error('Ошибка:', error);
            });
    }, [id]);

    if (!task) {
        return <div>Loading...</div>;
    }
    console.log(task);
    return (
        <div>
            <h1>Task Details</h1>
            <p><strong>Name:</strong> {task.name}</p>
            <p><strong>Description:</strong> {task.description}</p>
            <Link to={`/edit/task/${task.id}`}>Edit Task</Link>
        </div>
    );
};

