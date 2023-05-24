const db = require("../index");
const bcrypt = require("bcryptjs");

const addUser = (req, res) => {
    const { first_name, last_name, city, address, postal_code, phone_no, username, password } = req.body;
    if(!first_name || !last_name || !city || !address || !postal_code || !phone_no || !username || !password){
        return res.status(400).json({message: "Please enter all fields"});
    }
    db.query("SELECT * FROM user_details WHERE username = ?", [username], (err, result) => {
        if(err) throw err;
        if(result.length > 0){
            return res.status(400).json({message: "Username already taken"});
        }
        const user_id = new Date();
        const hashed_password = bcrypt.hashSync(password, 10);
        db.query(`
            INSERT INTO user_details (first_name, last_name, phone_no, username) VALUES (${first_name}, ${last_name}, ${phone_no}, ${username});
            INSERT INTO user_cred (username, password, user_id) VALUES (${username}, ${hashed_password}, ${user_id});
            INSERT INTO user_addr (user_id, city, address, postal_codes) VALUES (${user_id}, ${city}, ${address}, ${postal_code});
        `, (err, result) => {
            if(err) throw err;
            return res.status(200).json({message: "User added successfully"});
        });
    });
}

module.exports = {addUser};