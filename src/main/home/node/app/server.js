const express = require("express");
const MySQLConnection = require("MySQLConnection");
const mainActivityRoute = require("../../../java/com/sourcey/materiallogindemo/MainActivity.java");
var app = express();

app.use("/", mainActivityRoute);
app.listen(3000);
