// Selecting Element

var greet = document.querySelector('.md-width-dash-upper h3');
var detailstep = document.querySelector('.detailStep');
var step = document.querySelectorAll('.step');

// Variable for use 

var detailopen = false;

// Adding event

for(var i = 0; i<=7;i++){
    step[i].addEventListener('click',opendetailstep,true);
}


// Event Listener 

function opendetailstep(e){
    if(detailopen===false){
        id = parseInt(e.target.id);
        e.target.parentNode.insertBefore(detailstep,step[id]);
        detailstep.style.display = 'block';
        detailopen = true
    }else{
        detailopen = false;
        detailstep.style.display = 'none';
    }
    
}


// Greetings 
var username = localStorage.getItem('username');
greet.innerHTML = `Welcome ${username}`

// Removing name of user when going back to home page 

window.addEventListener('unload',()=>{
    localStorage.removeItem('username');
})