const CPUScheduling = require("../models/CPUScheduling");
function priorityScheduling(req, res) {
  /**
   * Implements priority scheduling algorithm for CPU scheduling.
   *
   * @param {Object} req - The HTTP request object containing the process information in the request body.
   *  - `body`: an object containing the following properties:
   *    - `processes`: an array of objects representing the processes, with the following properties:
   *      - `pid`: a string representing the process ID.
   *      - `arrivalTime`: a number representing the arrival time of the process.
   *      - `burstTime`: a number representing the burst time of the process.
   *      - `priority`: a number representing the priority of the process.
   *
   * @param {Object} res - The HTTP response object.
   *
   * @returns {Object} - An object containing the following properties:
   *  - `processes`: an array of objects representing the processes, with the following properties:
   *    - `pid`: a string representing the process ID.
   *    - `arrivalTime`: a number representing the arrival time of the process.
   *    - `burstTime`: a number representing the burst time of the process.
   *    - `priority`: a number representing the priority of the process.
   *    - `startTime`: a number representing the start time of the process.
   *    - `completionTime`: a number representing the completion time of the process.
   *    - `turnAroundTime`: a number representing the turnaround time of the process.
   *    - `waitingTime`: a number representing the waiting time of the process.
   *    - `responseTime`: a number representing the response time of the process.
   *  - `avgTAT`: a number representing the average turn around time of the processes.
   *  - `avgWT`: a number representing the average waiting time of the processes.
   *  - `ganttChart`: an array of objects representing the gantt chart, with each object containing a single property representing either an idle time or a process ID,
   *                  and its value representing the duration for which that process ran.
   *  - `date`: a Date object representing the current date and time.
   *
   * @throws {Error} - If the request body is empty, returns an empty array.
   */

  let { processes } = req.body;
  let currentTime = 0, completed = 0, avgTAT = 0, avgWT = 0;
  let n = processes.length;
  if (n == 0) return [];
  let processesCopy = processes;
  try {
    processes = processes.map((process) => {
      if (isNaN(process.pid) || isNaN(process.burstTime || isNaN(process.arrivalTime) || isNaN(process.priority))) throw new Error("Invalid Input");
      process.pid = parseInt(process.pid);
      process.burstTime = parseInt(process.burstTime);
      process.arrivalTime = parseInt(process.arrivalTime);
      process.priority = parseInt(process.priority);
      return process;
    });


    let isCompleted = Array(n).fill(0);
    let burstRemaining = processes.map((x) => x.burstTime);
    let ganttChart = [];

    while (completed !== n) {
      let index = -1;
      let max = Number.MAX_SAFE_INTEGER;
      for (let i = 0; i < n; i++) {
        if (processes[i].arrivalTime <= currentTime && isCompleted[i] === 0) {
          if (processes[i].priority < max) {
            max = processes[i].priority;
            index = i;
          }
          if (processes[i].priority === max) {
            if (processes[i].arrivalTime < processes[index].arrivalTime) {
              max = processes[i].priority;
              index = i;
            }
          }
        }
      }
      if (index !== -1) {
        if (burstRemaining[index] === processes[index].burstTime) {
          processes[index].startTime = currentTime;
        }
        ganttChart.push(processes[index].pid);
        burstRemaining[index] -= 1;
        currentTime++;
        prev = currentTime;
        if (burstRemaining[index] === 0) {
          processes[index].completionTime = currentTime;
          processes[index].turnAroundTime =
            processes[index].completionTime - processes[index].arrivalTime;
          processes[index].waitingTime = processes[index].turnAroundTime - processes[index].burstTime;
          processes[index].responseTime = processes[index].startTime - processes[index].arrivalTime;
          avgTAT += processes[index].turnAroundTime;
          avgWT += processes[index].waitingTime;

          isCompleted[index] = 1;
          completed++;
        }
      } else {
        currentTime++;
        ganttChart.push("-1");
      }
    }

    const counts = [];
    let currentCount = 1;

    for (let i = 0; i < ganttChart.length; i++) {  // Create an array of objects representing the gantt chart. i.e. array of {PID/IDLE: duration}.
      if (ganttChart[i] !== ganttChart[i + 1]) {
        if (ganttChart[i] == "-1")
          counts.push({ [`IDLE`]: currentCount });
        else
          counts.push({ [`PID ${ganttChart[i]}`]: currentCount });
        currentCount = 1;
      } else {
        currentCount++;
      }
    }
    avgTAT /= processes.length;
    avgWT /= processes.length;  // Calculate the average turn around time and average waiting time.
    let data = {
      processes,
      avgTAT,
      avgWT,
      ganttChart: counts,
      date: new Date()   // Get the current date and time to store in mongoDB.
    }
    let newCPUScheduling = new CPUScheduling(data);  // Create a new document in the CPUScheduling collection.
    newCPUScheduling.save(); // Save the document to the database.
    res.json(data);   // Return the data object.
  } catch (err) {
    console.log(err);
    return res.status(500).json({ processes: processesCopy });
  }
}

module.exports = { priorityScheduling }; // Export the priorityScheduling function.