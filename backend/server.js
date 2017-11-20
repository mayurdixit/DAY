var express = require('express');
var bodyParser = require('body-parser');
var mysql = require('mysql');
var app = express();
var userAPI = require('./api/user_api.js');

//Use Middlewares
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: false}));

// Set Static Path
app.use('/', express.static(__dirname));
app.use('/images', express.static('images'));

//
var routes = require('./api/user_api')(app);

port = process.env.PORT || 3000;

app.listen(port, function() {
    console.log("listning to port " + port);
});