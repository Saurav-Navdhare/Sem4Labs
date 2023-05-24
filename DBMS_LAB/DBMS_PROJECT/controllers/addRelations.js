const db = require('../index');
const createRelations = require('../models/relations')
// execute the queries in the array

const addRelationsToDB = () =>{
    createRelations.forEach((query) => {
        db.query(query, (err, result) => {
            if (err) throw err;
        });
    });
}

module.exports = addRelationsToDB;