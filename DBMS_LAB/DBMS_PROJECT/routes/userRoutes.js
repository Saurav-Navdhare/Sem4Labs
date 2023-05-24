const userControllers = require('../controllers/userControllers');

const express = require('express');
const router = express.Router();

router.post('/addUser', userControllers.addUser);

module.exports = router;