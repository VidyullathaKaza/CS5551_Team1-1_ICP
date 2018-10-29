var express = require('express');
var app = express();
var path = require('path');

// set port
var port = process.env.PORT || 8080

app.use(express.static(__dirname + '/'));

// routes
app.get("/", function(req, res) {
    res.sendFile(path.join(__dirname + "/Home.html"));
})

// Server must listen for requests and send a response
app.listen(port, function() {
    console.log("app running");
})