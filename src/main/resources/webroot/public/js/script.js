document.getElementById('helloBtn').addEventListener('click', () => {
    fetch('/App/hello?name=Pedro')
        .then(response => response.json())
        .then(data => {
            document.getElementById('output').innerText = "Hello Response: " + JSON.stringify(data);
        })
        .catch(error => console.error('Error:', error));
});

document.getElementById('piBtn').addEventListener('click', () => {
    fetch('/App/pi')
        .then(response => response.text())
        .then(data => {
            document.getElementById('output').innerText = "PI Response: " + data;
        })
        .catch(error => console.error('Error:', error));
});
