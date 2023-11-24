function submitForm() {
    // Получаем значения из полей формы
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    // Создаем объект с данными для отправки
    const person = {
        username: username,
        password: password
    };

    // Опции запроса
    const requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(person),
    };

    // Отправляем POST-запрос на сервер
    fetch('http://localhost:8080/person/registration', requestOptions)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log(data); // Обработка ответа от сервера
        })
        .catch(error => {
            console.error('There was an error!', error);
        });
}
