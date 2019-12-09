var config = require("./config.json");
var mysql = require('mysql');
var connection = mysql.createConnection(config.db);

connection.connect(function(err) {
    if (err) throw err;
});

module.exports = connection;