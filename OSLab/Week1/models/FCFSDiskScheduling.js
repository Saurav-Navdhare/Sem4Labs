/**

    A module that exports a Mongoose model schema for the First-Come, First-Serve (FCFS) Disk Scheduling Algorithm.
    @module FCFSSchema
    @requires mongoose
*/

const mongoose = require("mongoose");

/**
 
    A Mongoose schema for the FCFS Disk Scheduling Algorithm.
    @typedef {Object} FCFSDiskSchedulingSchema
    @property {Array} processes - An array of process numbers.
    @property {Number} head - The initial head position.
    @property {Number} diskSize - The size of the disk.
    @property {Number} seekTime - The time it takes for the head to move between processes.
*/

const FCFSDiskSchedulingSchema = new mongoose.Schema({
    processes: [
        {
            type: Number
        }
    ],
    head: Number,
    diskSize: Number,
    seekTime: Number,
});

/**
 
    A Mongoose model for the FCFS Disk Scheduling Algorithm.
    @typedef {Object} FCFSDiskSchedulingModel
    @property {Function} model - The Mongoose model constructor function.
*/

/**
 
    A Mongoose model for the FCFS Disk Scheduling Algorithm.
    @type {FCFSDiskSchedulingModel}
*/
module.exports = mongoose.model("FCFSDiskScheduling", FCFSDiskSchedulingSchema);