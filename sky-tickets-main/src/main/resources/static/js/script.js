var host = "http://localhost:8080"
function load() {
    loadMovies();
    loadGreetings();
}

function loadGreetings() {
    fetch(host+'/greetings')
        .then(response => response.json())
        .then(greeting => {
            const moviesList = document.getElementById('greeting');
            moviesList.innerHTML = greeting.message;
        });
}



function loadMovies() {
    fetch(host+'/catalog')
        .then(response => response.json())
        .then(movies => {
            const moviesList = document.getElementById('moviesList');
            moviesList.innerHTML = '';
            movies.forEach(movie => {
                const movieItem = document.createElement('div');
                movieItem.className = 'col-md-4';
                movieItem.innerHTML = `
                    <div class="card mb-4 shadow-sm">
                        <img src="${movie.imageUrl}" class="card-img-top" alt="${movie.title}">
                        <div class="card-body">
                            <h5 class="card-title">${movie.title}</h5>
                            <p class="card-text">Sessão: ${movie.sessionTime}</p>
                            <button class="btn btn-primary" onclick="selectMovie(${movie.id}, '${movie.title}', '${movie.sessionTime}')">Selecionar</button>
                        </div>
                    </div>
                `;
                moviesList.appendChild(movieItem);
            });
        });
}

function selectMovie(id, title, sessionTime) {
    document.getElementById('movieId').value = id;
    document.getElementById('movieTitle').innerText = title;
    document.getElementById('sessionTime').value = sessionTime;
    document.getElementById('orderForm').style.display = 'block';
}

function createOrder() {
    const buyerName = document.getElementById('buyerName').value;
    const movieId = document.getElementById('movieId').value;
    const sessionTime = document.getElementById('sessionTime').value;
    const cardNumber = document.getElementById('cardNumber').value;

    fetch(host + '/order', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            buyerName: buyerName,
            movieId: movieId,
            sessionTime: sessionTime,
            cardNumber: cardNumber
        })
    })
    .then(response => {
        if (!response.ok) {
            return response.json().then(errorData => {
                throw new Error(errorData.message || 'Erro ao criar o pedido.');
            });
        }
        return response.json();
    })
    .then(order => {
        alert('Pedido criado com sucesso!');
        document.getElementById('orderForm').reset();
        document.getElementById('orderForm').style.display = 'none';
    })
    .catch(error => {
        alert(`Erro: ${error.message}`);
    });
}

