const express = require('express');
const app = express();
require('dotenv').config();
const mysql = require('mysql');

app.use(express.json());

const userRoutes = require('./routes/userRoutes');




const db = mysql.createConnection({
    host: process.env.DATABASE_HOST,
    user: process.env.DATABASE_USER,
    password:process.env.DATABASE_PASSWORD,
    database: process.env.DATABASE,
    multipleStatements: true
});

db.connect((err)=>{
    if(err)
        throw err;
    else{
        const Port = process.env.PORT || 3000;
        app.listen(Port,(err)=>{
            if(err) throw err;
            console.log(`Server started on port ${Port}`);
        })
    }
});

module.exports = db;

const addRelationsToDB = require('./controllers/addRelations');

addRelationsToDB();