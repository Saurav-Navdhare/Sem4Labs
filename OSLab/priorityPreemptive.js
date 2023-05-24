function calculateTimeMetrics(processes) {
    // Sort the processes by arrival time
    if (!processes || !processes.length) {
        return [];
    }
    processes.sort((a, b) => {
        if (a.arrivalTime === b.arrivalTime) {
            return a.priority - b.priority;
        }
        return a.arrivalTime - b.arrivalTime
    });
    const newProcess = processes.map((process) => { process.temp = process.burstTime; return process })
    let final = [];
    let time = 0;
    let i = 0;
    let j = i+1;
    let waiting = [];
    while(i<newProcess.length-1 || j<newProcess.length){
        j = i+1;
        if(time < newProcess[i].arrivalTime){
            time = newProcess[i].arrivalTime;
        }
        if(time + newProcess[i].temp > newProcess[j].arrivalTime){
            newProcess[i].temp -= newProcess[j].arrivalTime - time;
            time = newProcess[j].arrivalTime;
            if(newProcess[i].priority <= newProcess[j].priority){
                waiting.push(newProcess[j++]);
            }
            else{
                waiting.push(newProcess[i]);
                i = j;
            }
        }
        else{
            time+=newProcess[i].temp;
            delete newProcess[i];
            newProcess[i].completionTime = time;
            final.push(newProcess[i])
            i = j;
            if(time <= newProcess[i].arrivalTime){
                waiting.sort((a,b)=>{
                    if(a.priority == b.priority){
                        return a.arrivalTime - b.arrivalTime;
                    }
                    return a.priority - b.priority;
                });
                while(waiting && (i!= newProcess.length && time < newProcess[i].arrivalTime)){
                    if(waiting[0].temp + time <= newProcess[i].arrivalTime){
                        time += waiting[0].arrivalTime;
                        waiting[0].completionTime = time;
                        delete waiting[0].temp;
                        final.push(waiting[0])
                        waiting.shift();
                    }
                    else{
                        waiting[0].temp -= newProcess[i].arrivalTime - time;
                        if(waiting[0].priority <= newProcess[i].priority){
                            waiting.push(newProcess[i]);
                            i++
                        }
                    }
                }
            }
        }
    }
    waiting.sort((a,b)=>{
        if(a.priority == b.priority){
            return a.arrivalTime - b.arrivalTime;
        }
        return a.priority - b.priority;
    })
    while(waiting){
        time += waiting[0].temp;
        delete waiting[0].temp;
        waiting[0].completionTime = time;
        final.push(waiting[0])
        waiting.shift();
    }
    return final;
}

const processes = [
    { processId: "P1", arrivalTime: 0, burstTime: 6, priority: 2 },
    { processId: "P2", arrivalTime: 2, burstTime: 8, priority: 2 },
    { processId: "P3", arrivalTime: 4, burstTime: 7, priority: 2 },
    { processId: "P4", arrivalTime: 6, burstTime: 3, priority: 2 }
];

console.log(calculateTimeMetrics(processes));