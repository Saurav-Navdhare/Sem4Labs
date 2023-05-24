let data = [
    {
        arrivalTime: 1,
        CPUBurstTime: 2,
        priority: 3,
    },
    {
        arrivalTime: 1,
        CPUBurstTime: 5,
        priority: 4,
    },
    {
        arrivalTime: 1,
        CPUBurstTime: 1,
        priority: 3,
    }
]


function processData(data){
    data.sort((a, b) => {
        if(a.arrivalTime!=b.arrivalTime){
            return a.arrivalTime - b.arrivalTime;
        }
        else if(a.priority!=b.priority){
            return a.priority - b.priority;
        }
        else{
            return a.CPUBurstTime - b.CPUBurstTime;
        }
    });
    let completionTime=data[0].arrivalTime;
    let tempList = [];
    return data;
}

console.log(processData(data));