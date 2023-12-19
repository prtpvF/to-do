import React, { useState, useEffect } from 'react';
import axios from 'axios';

export default function ViewAllTasks(){
    const [tasks, setTasks] = useState([]);
    const token = document.cookie.split('; ').find(row => row.startsWith('jwt_token=')).split('=')[1];

    useEffect(()=> {
        axios.get("http://localhost:8080/tasks", {
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
            .then((response) => {
                setTasks(response.data);
            })
            .catch((error) => {
                console.error('Ошибка:', error);
            });
    },[])

    return (
        <div>
            <table>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Time of Expiry</th>
                </tr>
                </thead>
                <tbody>
                {tasks.map((task, index) => {
                    let date = new Date(task.timeOfExpired);
                    let formattedDate = date.getDate() + '.' + (date.getMonth() + 1) + '.' + date.getFullYear();
                    let time = date.getHours().toString().padStart(2, '0') + ':' + date.getMinutes().toString().padStart(2, '0');
                    return (
                        <tr key={index}>
                            <td>{task.name}</td>
                            <td>{task.description}</td>
                            <td>{formattedDate+' '+ time}</td>
                        </tr>
                    );
                })}
                </tbody>
            </table>
        </div>
    );
}
