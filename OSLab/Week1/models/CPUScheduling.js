/**
 * Mongoose schema for the CPU scheduling details of a set of processes.
 * 
 * @property {Array} processes - An array of objects that represent the details of each process.
 * @property {number} avgTAT - The average turnaround time of the processes.
 * @property {number} avgWT - The average waiting time of the processes.
 * @property {Date} date - The date the document was created. It is set to the current date by default.
 * @property {Array} ganttChart - An array of objects that represent the gantt chart.
 *                                Each object contains a single property representing either an idle time or a process ID,
 *                                and its value representing the duration for which that process ran.
 * 
 * @model CPUSchedulingSchema
 */

const mongoose = require("mongoose");  // Import mongoose.

const CPUSchedulingSchema = new mongoose.Schema({  // Create a new schema.
    processes: {
        type: Array,
        required: true
    },
    avgTAT: Number,
    avgWT: Number,
    date: {
        type: Date,
        default: Date.now
    },
    ganttChart: {
        type: Array,
    }
});

module.exports = mongoose.model("CPUScheduling", CPUSchedulingSchema);  // Export the schema as a model.