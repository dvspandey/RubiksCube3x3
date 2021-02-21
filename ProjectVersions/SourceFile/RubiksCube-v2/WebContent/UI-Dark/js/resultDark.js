// Selecting Element

var greet = document.querySelector('.md-width-dash-upper h3');
var detailstep = document.querySelector('.detailStep');
var insideDetailstep = document.querySelector('.detailStep h3');
var step = document.querySelectorAll('.step');
var loaderscreen = document.querySelector('.loader');
var dashboardResult = document.querySelector('.dashboard-result');

// Variable for use 

var idopen = {
    id:null,
    close:false
}
var data =JSON.parse(localStorage.getItem('data'));
console.log(data);
// Adding event

for(var i = 0; i<=7;i++){
    step[i].addEventListener('click',opendetailstep,true);
}


// Event Listener 

function opendetailstep(e){
   
    id = parseInt(e.target.id);
    if(id===idopen.id && idopen.close===false){
        detailstep.style.display = 'none';
        idopen.close = true;
    }
    else if(id===idopen.id && idopen.close===true){
        detailstep.style.display = 'block';
        idopen.close = false;
    }
    else{
        idopen.id = id;
        e.target.parentNode.insertBefore(detailstep,step[id]);
        detailstep.style.display = 'block';
    }
    
    // Putting rotations inside each step 

    var datastep = `s${e.target.id}`;

    if(data[0][datastep]){
        insideDetailstep.innerHTML = data[0][datastep].myRotations;
    }
    // checking that rotation is empty or not 

    if(data[0].cubeStatus === 'cube is valid and Already solved!'){
        insideDetailstep.innerHTML = "cube is valid and Already solved!"
    }else if(data[0][datastep].myRotations === ''){
        insideDetailstep.innerHTML = "No rotation to follow. Your Cube already solved !!"
    }
    
  
    
}

// Calling data from localStorage 


// Greetings 
var username = localStorage.getItem('username');
greet.innerHTML = `Welcome ${username}`


// Removing name of user when going back to home page 

window.addEventListener('unload',()=>{
    localStorage.removeItem('username');
    localStorage.removeItem('data');
});




