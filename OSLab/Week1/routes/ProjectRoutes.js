/**

    A module that exports a router with various routes for different scheduling algorithms.
    @module schedulingRouter
    @requires express
    @requires ../controllers/preemptivePriorityScheduling
    @requires ../controllers/FCFSDiskScheduling
    @requires ../controllers/optimalPageReplacementAlgorithm
*/

const express = require("express");
const router = express.Router();

const { priorityScheduling } = require("../controllers/preemptivePriorityScheduling");
const { FCFSDiskScheduling } = require("../controllers/FCFSDiskScheduling");
const { optimalPageReplacementAlgorithm } = require("../controllers/optimalPageReplacementAlgorithm.js");

/**
 
    A route that handles the preemptive priority scheduling algorithm.
    @name POST /schedule
    @function
    @memberof module:schedulingRouter
    @param {Object} req - The request object containing the processes and their properties in the request body.
    @param {Object} res - The response object to be sent back to the client.
*/

router.post("/schedule", priorityScheduling);

/**
 
    A route that handles the First-Come, First-Serve (FCFS) Disk Scheduling Algorithm.
    @name POST /diskScheduling
    @function
    @memberof module:schedulingRouter
    @param {Object} req - The request object containing the processes and head parameters in the request body.
    @param {Object} res - The response object to be sent back to the client.
*/

router.post("/diskScheduling", FCFSDiskScheduling);

/**
 
    A route that handles the optimal page replacement algorithm.
    @name POST /optimalPageRA
    @function
    @memberof module:schedulingRouter
    @param {Object} req - The request object containing the page reference string and the number of frames in the request body.
    @param {Object} res - The response object to be sent back to the client.
*/

router.post("/optimalPageRA", optimalPageReplacementAlgorithm);

/**
 
    A router that exports various routes for different scheduling algorithms.
    @type {Object}
*/
module.exports = router;