/**
 * Base File of Backend responsible to start the server.
 * Including all the dependencies first, like
 * * Express Framework
 * * Mongoose Package: to connect with Mongo Database
 * * Cors Package to avoid Cross Origin Resource Sharing Problems
 * Using Routes
 * Connecting to Database, if successfully connected then listen for request on specified port( if not specified then 3000)
*/
const express = require("express");
const app = express();
require("dotenv").config();
const mongoose = require("mongoose");
const ProjectRoutes = require("./routes/ProjectRoutes");
const cors = require("cors");

app.use(cors());
mongoose.set('strictQuery', true);

app.use(express.json()); // to support JSON-encoded bodies
app.use(express.urlencoded({ extended: true })); // to support URL-encoded bodies

app.get("/", (req, res)=>{
    res.send("Hello World");
})

app.use("/api/v1", ProjectRoutes); // using routes

const Port = process.env.PORT || 3000;

mongoose.connect(process.env.MONGO_URI, (err) => { // connect to MongoDB
    if (err) throw err;  // if error, throw error
    app.listen(Port, (err) => {  // if no error, start server
        if (err) throw err;  // if error, throw error
        console.log(`Server is running on port ${Port}`); // if no error, log message
    })
});