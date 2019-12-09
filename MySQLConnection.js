var config = require("./config.json");
var mysql = require('mysql');
var connection = mysql.createConnection(config.db);

connection.connect(function(err) {
    if (err) {
        return console.error('error: ' + err.message);
    }

    console.log('Connected to the MySQL server.');
});

module.exports = connection;