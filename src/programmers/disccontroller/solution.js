function solution(jobs) {
    var answer = 0;
    jobs.sort(function(a, b) {
        if(a[0] === b[0]) {
            return a[1]-b[1];
        }
        return a[0]-b[0]; 
    });
    //console.log('jobs:',jobs.slice());
    var now = 0, sum = 0;
    var n = jobs.length;
    while(jobs.length != 0) {
        var candidate = [];
        //요청시간이 현재시간보다 짧은것 검색
        for(var i = 0; i < jobs.length; i++) {
            if(now >= jobs[i][0]) {
                candidate.push([jobs[i], i]);
            } else {
                break;
            }
        }
        if(candidate.length == 0) {
            now++;
            continue;
        }
        //작업시간이 짧은순으로
        candidate.sort(function(a,b) {
            return a[0][1] - b[0][1];
        });
        //console.log('candidate:',candidate);
        var job = candidate[0];
        sum += now-job[0][0]+job[0][1];
        now += job[0][1];
        jobs.splice(candidate[0][1], 1);
        //console.log('now:',now,'sum:',sum);
        //console.log(jobs.slice());
    }
    answer = parseInt(sum/n);
    return answer;
}