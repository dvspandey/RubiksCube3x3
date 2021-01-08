// Elements Selection
var dashboard = document .querySelector('.dashboard');
var greet = document.querySelector('.md-width-dash-upper h3');
var face = document.querySelectorAll('.md-width-dash-faceContainer-inner-in .color-filler-rows-dash');
var faceText = document.querySelectorAll('.md-width-dash-faceContainer-inner-in h3');
var colorFillerContainer = document.querySelector('.color-filler-container');
var finalBtn = document.querySelector('.color-filler button');
var closebtn = document.getElementById('close');
var errorlog = document.querySelector('.error');
var closeErrorBtn = document.querySelector('.error h5');
var solvebtn = document.querySelector('.btn-lg');
var loaderscreen = document.querySelector('.loader');

// Variables to use
var color;
var faceName;
 
// Events 
for(var i =1;i<=6;i++){
    document.querySelector(`.color-picker-lower-box${i}`).addEventListener('click',selectColor);  //Selecting and adding event to each color
}

solvebtn.addEventListener('click',result);

closeErrorBtn.addEventListener('click',closeErrorlog);

for(var i =1;i<=9;i++){
    document.getElementById(`boxFiller${i}`).addEventListener('click',fillColor);  //Selecting and adding event to each filler
}

for(var i =0;i<=5;i++){
    face[i].addEventListener('click',openmodal,true);        //Event for opening modal
}
for(var i =0;i<=5;i++){
    faceText[i].addEventListener('click',openmodal);        //Event for opening modal
}
closebtn.addEventListener('click',closemodal);          //Event to close modal

finalBtn.addEventListener('click',finalColors);

// EventListerner
function selectColor(e){
    e.target.style.border = '3px solid rgba(17,187,223,1)';
    color = e.target.id;
    for(var i =1;i<=6;i++){
       if(document.querySelector(`.color-picker-lower-box${i}`).id!==color){
        document.querySelector(`.color-picker-lower-box${i}`).style.border = '2px solid black';
       }
    }
}
function fillColor(e){
    e.target.style.backgroundColor = color;
    if(color){
        e.target.style.border = '2px solid black';
    }
}


function closeErrorlog(){
    errorlog.style.display='none';
}

function finalColors(){
    // Checking that all the colors fullfilled
    var arr = [];
    for(var i =1;i<=9;i++){
       if(document.getElementById(`boxFiller${i}`).style.backgroundColor!==''){
            arr.push(document.getElementById(`boxFiller${i}`).style.backgroundColor);
       }
    }
    
    if(arr.length!==9){
        errorlog.style.display = 'block';
    }else{
        for(var i =1;i<=9;i++){
            document.getElementById(`boxFillerDash${faceName}${i}`).style.backgroundColor = document.getElementById(`boxFiller${i}`).style.backgroundColor;
        }
        closemodal();
        faceName = undefined;
    }
}

function closemodal(){
    colorFillerContainer.style.display='none';
    for(var i=1;i<=9;i++){
        document.getElementById(`boxFiller${i}`).style.backgroundColor='';
        document.getElementById(`boxFiller${i}`).style.border='2px solid #576574';
    }
    for(var i=1;i<=6;i++){
        document.querySelector(`.color-picker-lower-box${i}`).style.border='';
    }
    color = undefined;
    errorlog.style.display = 'none';
}
function openmodal(e){
    colorFillerContainer.style.display='block';
    if(e.target.id){
        faceName = e.target.parentElement.parentElement.id;
    }else{
        var arr = e.target.textContent.split(" ");
        faceName=arr[0];
    }
    
}

function result(){
    loaderscreen.style.display = 'block';
    dashboard.style.display = 'none'
    setTimeout(function(){
        location.href = 'result.html';
        loaderscreen.style.display = 'none';
    },4000);
}

// Greetings code

var username = localStorage.getItem('username');
greet.innerHTML = `Welcome ${username}`;