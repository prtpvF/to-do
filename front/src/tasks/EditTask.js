import React, { useState } from 'react';
import axios from 'axios';
import {useNavigate, useParams} from "react-router-dom";

export default function EditTask() {

    const [updatedTask, setUpdatedTask] = useState({
        name: "",
        description: "",
        timeOfExpired: ""
    });

    const {id} = useParams();


    const token = document.cookie.split('; ').find(row => row.startsWith('jwt_token=')).split('=')[1];


    const onInputChange = (e) => {
        setUpdatedTask({ ...updatedTask, [e.target.name]: e.target.value });
    };

    const onSubmit = async (e) => {
        e.preventDefault();
        await axios.post(`http://localhost:8080/edit/${id}`, updatedTask,  {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });
    };

    return (
        <form onSubmit={onSubmit}>
            <input type="text" name="name" value={updatedTask.name} onChange={onInputChange} placeholder="Name" />
            <input type="text" name="description" value={updatedTask.description} onChange={onInputChange} placeholder="Description" />
            <input type="datetime-local" name="timeOfExpired" value={updatedTask.timeOfExpired} onChange={onInputChange} placeholder="Time of Expiration" />
            <button type="submit">Submit</button>
        </form>
    );
}
