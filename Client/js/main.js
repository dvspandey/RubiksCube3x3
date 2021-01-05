var input = document.querySelector('input[type="text"]');
var btn = document.querySelector('.btn-md');
var form = document.querySelector('form');
var nameofSeeker; 

//taking input feild
input.addEventListener('change',namechanger);


function namechanger(e){
    nameofSeeker = e.target.value;
}

btn.addEventListener('click',(e)=>{
    if(!nameofSeeker){      //checking that weather we get the name or not
        e.preventDefault();
    }else{
        localStorage.setItem('username',nameofSeeker);
    }
})