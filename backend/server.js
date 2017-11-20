var express = require('express');
var bodyParser = require('body-parser');
var mysql = require('mysql');

var app = express();

//Use Middlewares
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: false}));

// Set Static Path
app.use('/', express.static(__dirname));

port = process.env.PORT || 3000;

app.listen(port, function() {
    console.log("listning to port " + port);
});