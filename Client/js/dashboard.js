var greet = document.querySelector('.md-width-dash-upper h3');
console.log(greet);

var username = localStorage.getItem('username');
greet.innerHTML = `Welcome ${username}`;