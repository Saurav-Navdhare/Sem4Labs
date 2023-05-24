let processes = [
    {
        "PId": 1,
        "AT": 0,
        "BT": 24,
        "P": 3
    },
    {
        "PId": 2,
        "AT": 0,
        "BT": 3,
        "P": 1
    },
    {
        "PId": 3,
        "AT": 0,
        "BT": 3,
        "P": 1
    }
];

function priorityScheduling(processes){
    let time = 0, completed = 0, n = processes.length, is_completed = new Array(n).fill(false), prev = 0;
    while(completed != n){
        let index = -1;
        let max = 50; // max priority
        for(let i = 0; i < n; i++){
            if(processes[i].AT <= time && !is_completed[i]){
                
            }
        }
    }
}