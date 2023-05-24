/**

    A function that implements the First-Come, First-Serve (FCFS) Disk Scheduling Algorithm.
    @param {Object} req - The request object containing the processes and head parameters in the request body.
    @param {Object} res - The response object to be sent back to the client.
    @returns {Object} - A JSON response with either a success message and the data object or an error message and the error object.
*/


// Import the FCFSSchema model
const FCFSSchema = require('../models/FCFSDiskScheduling');

// Define a function named FCFSDiskScheduling
const FCFSDiskScheduling = (req, res) => {
  // Retrieve the processes and head parameters from the request body
  const { processes, head } = req.body;

  // Initialize the seekTime and data variables
  let seekTime = 0;
  let data = {
    processes,
    head
  }

  // Set the currentHead variable to the initial head value
  let currentHead = head;

  // Iterate through each process to calculate the seek time
  for (let i = 0; i < processes.length; i++) {
    // Calculate the absolute difference between the current head and the next process
    seekTime += Math.abs(currentHead - processes[i]);
    // Set the current head to the next process
    currentHead = processes[i];
  }

  // Add the seek time to the data object
  data.seekTime = seekTime;

  // Create a new FCFSSchema object with the data object
  const FCFSDiskScheduling = new FCFSSchema(data);

  // Try to save the FCFSSchema object to the database
  try {
    FCFSDiskScheduling.save().then((result) => {
      // If successful, return a JSON response with a success message and the data object
      res.status(200).json({ message: "FCFS Disk Scheduling Algorithm Executed Successfully", data });
    }).catch((err) => {
      // If an error occurs, return a JSON response with an error message and the error object
      res.status(500).json({ message: "Internal Server Error", error: err });
    });
  } catch (err) {
    // If an error occurs, log the error to the console and return a JSON response with an error
        console.log(err);
        res.status(500).json({message: "Internal Server Error", error: err});
    }
}

// Export the FCFSDiskScheduling function
module.exports = { FCFSDiskScheduling }