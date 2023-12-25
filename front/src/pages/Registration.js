import React, { useState } from 'react';
import axios from 'axios';
export default function Registration(){
    const [person, setPerson] = useState({
        username:"",
        password:"",
        confirmPassword:"",
        email:""
    });
    const [errorMessage, setErrorMessage] = useState(null);

    const handleChange = (event) => {
        setPerson({...person, [event.target.name]: event.target.value});
    };

    const registration = async(event)=>{
        event.preventDefault();
        try{
            await axios.post('http://localhost:8080/registration', person);
        } catch (error){
            setErrorMessage(error.response.data.message);
        }
    };

    return (
        <div>
            <h2>Registration</h2>

            <form onSubmit={registration}>
                <div>
                    <label>Username:</label>
                    <input type="text" name="username" value={person.username} onChange={handleChange} />
                </div>
                <div>
                    <label>Password:</label>
                    <input type="password" name="password" value={person.password} onChange={handleChange} />
                </div>
                <div>
                    <label>Confirm password:</label>
                    <input type="password" name="confirmPassword" value={person.confirmPassword} onChange={handleChange} />
                </div>
                <div>
                    <label>Email:</label>
                    <input type="email" name="email" value={person.email} onChange={handleChange} />
                </div>
                <button type="submit">Register</button>
            </form>
            {errorMessage && <p style={{ color: 'red' }}>{errorMessage}</p>}
        </div>
    )
}
