import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from "react-router-dom";
import data from "bootstrap/js/src/dom/data";

export default function AddTask() {
    let navigate = useNavigate();
    const [task, setTask] = useState({
        name: "",
        description: "",
        timeOfExpired: ""
    });
    const [errorMessage, setErrorMessage] = useState(null);
    const token = document.cookie.split('; ').find(row => row.startsWith('jwt_token=')).split('=')[1];
    const [principal, setPrincipal] = useState({
        token: token
    });

    const onInputChange = (e) => {
        setTask({ ...task, [e.target.name]: e.target.value });
    };

    const onSubmit = async (e) => {
        try {
            e.preventDefault();
            await axios.post("http://localhost:8080/add/task", task, {
                headers: {
                    Authorization: `Bearer ${principal.token}`
                }
            });
        } catch (error){
            setErrorMessage(error.response.data.message);

        }
    };

    return (
        <div>
        <form onSubmit={onSubmit}>
            <input type="text" name="name" value={task.name} onChange={onInputChange} placeholder="Name" />
            <input type="text" name="description" value={task.description} onChange={onInputChange} placeholder="Description" />
            <input type="datetime-local" name="timeOfExpired" value={task.timeOfExpired} onChange={onInputChange} placeholder="Time of Expiration" />
            <button type="submit">Submit</button>
        </form>
            {errorMessage && <p style={{ color: 'red' }}>{errorMessage}</p>}
        </div>
    );
}
