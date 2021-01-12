const express = require('express');
const path = require('path');

// Init app 
const app = express();

// Using middleware to make public folder static 
app.use(express.static(path.join(__dirname ,'public')));

const PORT = process.env.PORT || 5000;


// listening at port 5000 as client side 

app.listen(PORT,()=>{
    console.log(`Client server is runing at port ${PORT}`);
})