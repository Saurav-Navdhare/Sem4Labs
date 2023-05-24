let processes = [{
    PId: 1,
    AT: 0,
    BT: 3,
    P: 2
},
{
    PId: 2,
    AT: 2,
    BT: 6,
    P: 1
},
{
    PId: 3,
    AT: 4,
    BT: 4,
    P: 3
}];

function preemptivePriority(processes){
    if(!processes || !processes.length) return [];
    let result = [];
    let time = 0;
    let queue = [];
    let newProcess = processes.sort((a,b)=>{
        if(a.AT == b.AT){
            if(a.P == b.P){
                return a.PId - b.PId;
            }
            return a.P - b.P;
        }
        return a.AT - b.AT;
    });
    let i = 0, j = 1;
    while(i < newProcess.length - 1 && j!= newProcess.length){
        if(time+newProcess[i].temp <= newProcess[j].AT){
            time += newProcess[i].temp;
            result.push({
                PId: newProcess[i].PId,
                AT: newProcess[i].AT,
                BT: newProcess[i].BT,
                CT: time,
                TAT: time - newProcess[i].AT,
                WT: time - newProcess[i].AT - newProcess[i].BT
            });
            i++;
            j++;
            if(time <= newProcess[i].AT && queue.length){
                queue.sort((a,b)=>{
                    if(a.P == b.P){
                        return a.PId - b.PId;
                    }
                    return a.P - b.P;
                });
                if(queue[0].P <= newProcess[i].P){
                    queue.push(newProcess[i++]);
                }
            }
        }
        else{
            if(newProcess[i].P <= newProcess[j].P){
                queue.push(newProcess[j]);
                j++;
            }
            else{
                newProcess[i].temp -= newProcess[j].AT - time;
                time = newProcess[j].AT;
                queue.push(newProcess[i]);
                i = j;
                j++;
            }
        }
    }
}