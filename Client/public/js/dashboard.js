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
var errorDisplay = document.querySelector('.errorDisplay');
var closeErrorDisplayBtn = document.querySelector('.errorDisplay .validationError h5');


// Variables to use
var color;
var errorSound = new Audio('../sound/error.wav');
var faceName;
var faceFilled = {
    Front:false,
    Back:false,
    Upper:false,
    Lower:false,
    Left:false,
    Right:false,
};
var arrFaceNames = ['Upper','Front','Right','Back','Left','Lower'];
var colorCount = {
    red:0,
    green:0,
    yellow:0,
    white:0,
    blue:0,
    orange:0,
};
 
// Events 
for(var i =1;i<=6;i++){
    document.querySelector(`.color-picker-lower-box${i}`).addEventListener('click',selectColor);  //Selecting and adding event to each color
}

closeErrorDisplayBtn.addEventListener('click',closeErrorDisplay);

solvebtn.addEventListener('click',result);

closeErrorBtn.addEventListener('click',closeErrorlog);

for(var i =1;i<=9;i++){
    if(i!==5){
        document.getElementById(`boxFiller${i}`).addEventListener('click',fillColor);    
    }
      //Selecting and adding event to each filler
}

for(var i =0;i<=5;i++){
    face[i].addEventListener('click',openmodal);        //Event for opening modal
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
        errorSound.play();
        errorlog.style.display = 'block';
    }else{
        for(var i =1;i<=9;i++){
            document.getElementById(`boxFillerDash${faceName}${i}`).style.backgroundColor = document.getElementById(`boxFiller${i}`).style.backgroundColor;
        };

        // setting face fullfilled to true

        faceFilled[`${faceName}`] = true;
        
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
        faceName = e.target.id;

        // filling colors into model which already filled
        for(var i=1;i<=9;i++){
            document.getElementById(`boxFiller${i}`).style.backgroundColor = document.getElementById(`boxFillerDash${faceName}${i}`).style.backgroundColor ;

            // checking that filled or not 

            if(document.getElementById(`boxFillerDash${faceName}${i}`).style.backgroundColor !== ''){
                document.getElementById(`boxFiller${i}`).style.border = '2px solid black';
            }
        } 
      }else{
        var arr = e.target.textContent.split(" ");
        faceName=arr[0];

        // filling colors into model which already filled

        for(var i=1;i<=9;i++){
            document.getElementById(`boxFiller${i}`).style.backgroundColor = document.getElementById(`boxFillerDash${faceName}${i}`).style.backgroundColor ;

            // checking that filled or not 

            if(document.getElementById(`boxFillerDash${faceName}${i}`).style.backgroundColor !== ''){
                document.getElementById(`boxFiller${i}`).style.border = '2px solid black';
            }
        }
    }
}

function result(){
    
    // reset color value to 0 

    for(nameofcolor in colorCount){
        if(colorCount[nameofcolor]!==0){
            colorCount[nameofcolor] = 0;
        }
    }
    
    // Counting the number of colors fullfilled 

    arrFaceNames.map((value)=>{
        for(var i=1;i<=9;i++){
            colorCount[`${document.getElementById(`boxFillerDash${value}${i}`).style.backgroundColor}`] = colorCount[`${document.getElementById(`boxFillerDash${value}${i}`).style.backgroundColor}`] + 1;
        }
    })

    if(colorCount.red > 9 || colorCount.green > 9 || colorCount.blue > 9 || colorCount.orange > 9 || colorCount.white > 9 || colorCount.yellow > 9){
        errorDisplay.style.display = `block`;
        errorSound.play();

        // finding the name of color which used more than 9 times
        
        let colorNames = '';
        
        for(color in colorCount){
            if(colorCount[color] > 9){
                colorNames = colorNames + ` ${color} ${colorCount[color]} times`;
            }
        }

        // Modify text of validation error 

        errorDisplay.childNodes[1].childNodes[1].textContent = `Total 9 times singal color can be filled but you have filled${colorNames}`;

    }else 
    if(faceFilled.Front !== true || faceFilled.Back !== true || faceFilled.Upper !== true || faceFilled.Lower !== true || faceFilled.Left !== true || faceFilled.Right !== true){
        errorDisplay.style.display = `block`;
        errorSound.play();
        errorDisplay.childNodes[1].childNodes[1].textContent = 'Please fill all the faces';
    }
    else{
        // creating variable which holds the data to be send 

        var data = {
            cube:[],
            rotation:''
        }

        arrFaceNames.map((value)=>{
            var facearr = [];
            var valuearr = [];

            // row 1 
            for(var i=1;i<=3;i++){
                valuearr.push((document.getElementById(`boxFillerDash${value}${i}`).style.backgroundColor)[0].toUpperCase())
            }
            facearr.push(valuearr);
            valuearr = [];

            // row 2 
            for(var i=4;i<=6;i++){
                valuearr.push((document.getElementById(`boxFillerDash${value}${i}`).style.backgroundColor)[0].toUpperCase())
            }
            facearr.push(valuearr);
            valuearr = [];

            // row 3 
            for(var i=7;i<=9;i++){
                valuearr.push((document.getElementById(`boxFillerDash${value}${i}`).style.backgroundColor)[0].toUpperCase())
            }
            facearr.push(valuearr);
            data.cube.push(facearr);
        })
        
        // Setting Headers to application/json 

        const config={
            headers:{
                "Content-Type":"application/json"
            }
        }

        // Stringify data object into json 
        const body = JSON.stringify(data);
        
        // start loading 
        loaderscreen.style.display = 'block';
        dashboard.style.display = 'none'

        // Sending request to server api 
        axios.post('https://localhost:8080/java-servlet-json-rubiks-cube/steps',body,config).then((res)=>{
            loaderscreen.style.display = 'none';
            location.href = 'result.js';
        })

    } 
}

function closeErrorDisplay(){
    errorDisplay.style.display = 'none';
}

// Greetings 

var username = localStorage.getItem('username');
greet.innerHTML = `Welcome ${username}`;

