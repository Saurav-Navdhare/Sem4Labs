// Test case 1
let p1 = [
    {
      arrival_time: 0,
      burst_time: 6,
      priority: 0
    },
    {
      arrival_time: 2,
      burst_time: 2,
      priority: 15
    },
    {
      arrival_time: 6,
      burst_time: 4,
      priority: 7
    },
    {
      arrival_time: 7,
      burst_time: 2,
      priority: 6
    },
    {
      arrival_time: 4,
      burst_time: 3,
      priority: 10
    },
  ];

console.log(priorityScheduling(p1, 5));

function priorityScheduling(p) {
    let n = p.length;
    if(n==0) return [];
    let current_time = 0;
    let completed = 0;
    let is_completed = Array(100).fill(0);
    let prev = 0;
    let burst_remaining = p.map((x) => x.burst_time);
    let total_turnaround_time = 0;
    let total_waiting_time = 0;
    let total_response_time = 0;
    let total_idle_time = 0;
    while (completed !== n) {
      let index = -1;
      let max = Number.MAX_SAFE_INTEGER;
      for (let i = 0; i < n; i++) {
        if (p[i].arrival_time <= current_time && is_completed[i] === 0) {
          if (p[i].priority < max) {
            max = p[i].priority;
            index = i;
          }
          if (p[i].priority === max) {
            if (p[i].arrival_time < p[index].arrival_time) {
              max = p[i].priority;
              index = i;
            }
          }
        }
      }
      if (index !== -1) {
        if (burst_remaining[index] === p[index].burst_time) {
          p[index].start_time = current_time;
          total_idle_time += p[index].start_time - prev;
        }
        burst_remaining[index] -= 1;
        current_time++;
        prev = current_time;
        if (burst_remaining[index] === 0) {
          p[index].completion_time = current_time;
          p[index].turnaround_time =
            p[index].completion_time - p[index].arrival_time;
          p[index].waiting_time = p[index].turnaround_time - p[index].burst_time;
          p[index].response_time = p[index].start_time - p[index].arrival_time;
          total_turnaround_time += p[index].turnaround_time;
          total_waiting_time += p[index].waiting_time;
          total_response_time += p[index].response_time;
          is_completed[index] = 1;
          completed++;
        }
      } else {
        current_time++;
      }
    }
    return p;
  }
  