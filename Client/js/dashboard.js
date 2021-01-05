// Elements Selection

var greet = document.querySelector('.md-width-dash-upper h3');
var face = document.querySelectorAll('.md-width-dash-faceContainer-inner-in img');
var faceText = document.querySelectorAll('.md-width-dash-faceContainer-inner-in h3');
var colorFillerContainer = document.querySelector('.color-filler-container');
var closebtn = document.getElementById('close');


// Variables to use
var color;
 
// Events 
for(var i =1;i<=6;i++){
    document.querySelector(`.color-picker-lower-box${i}`).addEventListener('click',selectColor);  //Selecting and adding event to each color
}

for(var i =1;i<=9;i++){
    document.getElementById(`boxFiller${i}`).addEventListener('click',fillColor);  //Selecting and adding event to each filler
}

for(var i =0;i<=5;i++){
    face[i].addEventListener('click',openmodal);        //Event for opening modal
}
for(var i =0;i<=5;i++){
    faceText[i].addEventListener('click',openmodal);        //Event for opening modal
}
closebtn.addEventListener('click',closemodal);          //Event to close modal


// EventListerner
function selectColor(e){
    e.target.style.border = '3px solid rgba(17,187,223,1)';
    color = e.target.id;
    for(var i =1;i<=6;i++){
       if(document.querySelector(`.color-picker-lower-box${i}`).id!==color){
        document.querySelector(`.color-picker-lower-box${i}`).style.border = 'none';
       }
    }
}
function fillColor(e){
    e.target.style.backgroundColor = color;
    
}
function closemodal(){
    colorFillerContainer.style.display='none';
    for(var i=1;i<=9;i++){
        document.getElementById(`boxFiller${i}`).style.backgroundColor='';
    }
    for(var i=1;i<=6;i++){
        document.querySelector(`.color-picker-lower-box${i}`).style.border='';
    }
}
function openmodal(){
    colorFillerContainer.style.display='block';
}

var username = localStorage.getItem('username');
greet.innerHTML = `Welcome ${username}`;