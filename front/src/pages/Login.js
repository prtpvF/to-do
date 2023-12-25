import React, { useState } from 'react';
import axios from 'axios';

export default function Login(){

        const [username, setUsername] = useState('');
        const [password, setPassword] = useState('');
        const [errorMessage, setErrorMessage] = useState(null);

        const handleLogin = async () => {
            try {
                // Запрос к бэкенду для получения токена
                const response = await axios.post('http://localhost:8080/token', {
                    username,
                    password,
                });

                // Полученный токен
                const token = response.data.token;

                // Установка токена в куки на 7 дней (или любое другое удобное вам время)
                document.cookie = `jwt_token=${token}; max-age=${7 * 24 * 60 * 60}`;

                // Дополнительные действия, если нужно
                console.log('JWT Token:', token);
                alert('JWT Token has been stored in the cookie!');
            } catch (error) {
                setErrorMessage(error.response.data.message);

            }
        };

        return (
            <div>
                <h2>Login</h2>
                <div>
                    <label>Username:</label>
                    <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
                </div>
                <div>
                    <label>Password:</label>
                    <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
                </div>
                <button onClick={handleLogin}>Login</button>
                {errorMessage && <p style={{ color: 'red' }}>{errorMessage}</p>}
            </div>
        );
}
