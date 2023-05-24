const optimalPageRA = require("../models/optimalPageRA");

const optimalPageReplacementAlgorithm = (req, res) => {
    const {processes, frames} = req.body;
    let pageFaults = 0;
    let pageFaultSequence = [];
    let memory = [];
    
    for (let i = 0; i < processes.length; i++) {
      if (memory.includes(processes[i])) {
        // Page hit, do nothing
      } else if (memory.length < frames) {
        // Add page to memory if there is still space
        memory.push(processes[i]);
        pageFaults++;
      } else {
        // Find the page to replace with the one farthest in the future
        let indexToReplace = -1;
        let farthestPageUse = -1;
        for (let j = 0; j < memory.length; j++) {
          let found = false;
          for (let k = i + 1; k < processes.length; k++) {
            if (memory[j] === processes[k]) {
              found = true;
              if (k > farthestPageUse) {
                farthestPageUse = k;
                indexToReplace = j;
              }
              break;
            }
          }
          if (!found) {
            indexToReplace = j;
            break;
          }
        }
        // Replace page in memory with the new page
        memory[indexToReplace] = processes[i];
        pageFaults++;
      }
      // Add the current memory state to the page fault sequence
      pageFaultSequence.push(memory.slice());
    }
    const data = {
        processes,
        frames,
        pageFaults,
        pageFaultSequence
    }
    const optimalPageRAInstance = new optimalPageRA(data);
    optimalPageRAInstance.save((err, result) => {
        if (err) {
            return res.status(422).json({error: err});
        }
        res.json({data});
    });

  }


module.exports = { optimalPageReplacementAlgorithm };